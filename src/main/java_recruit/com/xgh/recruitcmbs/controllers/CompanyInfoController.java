package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.MemberUser;
import com.xgh.recruitcmbs.entity.Position;
import com.xgh.recruitcmbs.entity.Zone;
import com.xgh.recruitcmbs.services.*;
import com.xgh.recruitcmbs.util.ShortMessageUtil;
import com.xgh.security.MD5Util;
import com.xgh.util.PinyinUtil;
import org.apache.log4j.Logger;
import com.xgh.recruitcmbs.util.ConstantUtil;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CQ on 2017/3/13.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/companyInfo/")
public class CompanyInfoController extends BaseController {


    private Logger logger = Logger.getLogger(CompanyInfoController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    @Autowired
    protected IShortMessageService shortMessageService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IPositionService positionService;


    /**
     * 获取公司信息
     */
    @RequestMapping(value = "get")
    public void get() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        Map<String, Object> companyMap = new HashMap<String, Object>();

        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            long id_ = Long.parseLong(id);
            CompanyInfo companyInfo = companyInfoServices.get(id_);
            dicMap.put("item", "companySize");
            dicMap.put("code", companyInfo.getCompanySize());
            String companySize = dictionaryService.getValue(dicMap);

            companyMap.put("companyId", companyInfo.getId());
            companyMap.put("companyName", companyInfo.getCompanyName());
            companyMap.put("logoRealPath", companyInfo.getLogoRealPath());
            companyMap.put("industryName", companyInfo.getIndustryName());
            companyMap.put("industryCode", companyInfo.getIndustryId());
            companyMap.put("companySize", companySize);
            companyMap.put("companySizeCode", companyInfo.getCompanySize());
            companyMap.put("gpsLongitude", companyInfo.getGpsLongitude());
            companyMap.put("gpsLatitude", companyInfo.getGpsLatitude());
            companyMap.put("companyUrl", companyInfo.getCompanyUrl());
            companyMap.put("address", companyInfo.getAddress());
            companyMap.put("mobile", companyInfo.getMobile());
            companyMap.put("zoneName", companyInfo.getZoneName());
            companyMap.put("email", companyInfo.getEmail());
            companyMap.put("weixinNum", companyInfo.getWeixinNum());
            companyMap.put("telephone", companyInfo.getTelephone());
            companyMap.put("intro", companyInfo.getIntro());
            companyMap.put("data4", companyInfo.getData4());
            companyMap.put("data5", companyInfo.getData5());
            companyMap.put("data6", companyInfo.getData6());
            companyMap.put("data7", companyInfo.getData7());
            companyMap.put("data8", companyInfo.getData8());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyId", companyInfo.getId());
            map.put("positionStatus", "1");

            List<Position> positionList = positionService.getPositionList(map);
            if (positionList != null && positionList.size() > 0) {
                companyMap.put("positionNum", positionList.size());
            } else {
                companyMap.put("positionNum", "0");
            }

            if (companyInfo != null) {
                resultMap = getResultMap("1", "企业信息返回成功!", companyMap);
            } else
                resultMap = getResultMap("0", "企业信息返回失败!");
        }
        outJson(resultMap);
    }

    /**
     * 查询企业发布的职位
     */
    @RequestMapping(value = "getCompanyPositions")
    public void getCompanyPositions() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();

        String id = request.getParameter("id");//企业id
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pagesize");

        map.put("companyId", id);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));


        if (id != null && !"".equals(id)) {

            List<Position> positionList = positionService.getByCompanyId(map);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (Position position : positionList) {

                Map<String, Object> positionMap = new HashMap<String, Object>();
                positionMap.put("positionId", position.getId());
                positionMap.put("jobName", position.getJobName());
                positionMap.put("recruitingNum", position.getRecruitingNum());

                if (null != position.getQualification() && !"".equals(position.getQualification())) {
                    dicMap.put("item", "edu");
                    dicMap.put("code", position.getQualification());
                    positionMap.put("qualification", dictionaryService.getValue(dicMap));
                } else {
                    positionMap.put("qualification", "学历不限");
                }

                positionMap.put("address", position.getZoneName());
                if (null != position.getSalaryMin() && !"".equals(position.getSalaryMin()) && null != position.getSalaryMax() && !"".equals(position.getSalaryMax())) {
                    positionMap.put("salary", position.getSalaryMin() + "-" + position.getSalaryMax());
                } else {
                    positionMap.put("salary", "面议");
                }
                list.add(positionMap);
            }
            if (positionList.size() > 0) {
                resultMap = getResultMap("1", "获取热招职位成功！", list);
            } else {
                resultMap = getResultMap("0", "暂无数据！");
            }
        } else {
            resultMap = getResultMap("0", "参数错误！");
        }
        outJson(resultMap);
    }


    /**
     * 企业注册
     */
    @RequestMapping(value = "register")
    public void register(@ModelAttribute CompanyInfo companyInfo) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String account = companyInfo.getAccount();//登录账号
        String mobile = companyInfo.getMobile();//绑定手机号
        String validationCode_ = request.getParameter("validationCode");//短信验证码
        String newPassword = request.getParameter("newPassword");//新密码
        String confirmPassword = request.getParameter("confirmPassword");//确认密码

        if ("".equals(account) || account == null) {

            resultMap = getResultMap("-1", "请输入登录账号!");
            outJson(resultMap);
            return;
        }
        if ("".equals(mobile) || mobile == null) {

            resultMap = getResultMap("-1", "请输入绑定手机号!");
            outJson(resultMap);
            return;
        }
        if ("".equals(validationCode_) || validationCode_ == null) {

            resultMap = getResultMap("-1", "请输入短信验证码!");
            outJson(resultMap);
            return;
        }
        if ("".equals(newPassword) || newPassword == null) {

            resultMap = getResultMap("-1", "请输入密码!");
            outJson(resultMap);
            return;
        }
        if ("".equals(confirmPassword) || confirmPassword == null) {

            resultMap = getResultMap("-1", "请输入确认密码!");
            outJson(resultMap);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            resultMap = getResultMap("-1", "前后密码不一致!");
            outJson(resultMap);
            return;
        }

        int flg = companyInfoServices.getCompanyInfoByAccount(account);
        if (flg > 0) {
            resultMap = getResultMap("-1", "该登录账号已经注册!");
            outJson(resultMap);
            return;
        }

        int flag = companyInfoServices.getCompanyInfoByMobile(mobile);
        if (flag > 0) {
            resultMap = getResultMap("-1", "该绑定手机号已经注册!");
            outJson(resultMap);
            return;
        }
        companyInfo.setPassword(newPassword);
        outJson(companyInfoServices.register(request, companyInfo, validationCode_));

    }

    /**
     * 判断该账号是否已经注册
     */
    @RequestMapping(value = "isAlreadyRegister")
    public void isAlreadyRegister() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String account = request.getParameter("account");
        if (!"".equals(account)) {
            System.out.println();
            int flag = companyInfoServices.getCompanyInfoByAccount(account);
            if (flag > 0) {
                resultMap = getResultMap("-1", "该账户已经被注册,请选用其他账号!");
            } else {
                resultMap = getResultMap("1", "该账户未被注册,请放心使用!");
            }
        }

        outJson(resultMap);
    }

    /**
     * 判断该绑定账号是否已经注册
     */
    @RequestMapping(value = "mobileIsAlreadyRegister")
    public void mobileIsAlreadyRegister() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String account = request.getParameter("mobile");
        if (!"".equals(account)) {
            int flag = companyInfoServices.getCompanyInfoByMobile(account);
            if (flag > 0) {
                resultMap = getResultMap("-1", "该绑定账户已经被注册,请选用其他账号!");
            } else {
                resultMap = getResultMap("1", "该绑定账户未被注册,请放心使用!");
            }
        }
        outJson(resultMap);
    }

    /**
     * 企业登录
     */
    @RequestMapping(value = "login")
    public void login(@RequestParam String account, @RequestParam String password) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        map.put("password", password);

        CompanyInfo companyInfo = companyInfoServices.login(map);
        if (companyInfo != null) {

            if (companyInfo.getPassword().equals(MD5Util.getMD5(password))) {
                resultMap = getResultMap("1", "登录成功!", companyInfo);
            } else {
                resultMap = getResultMap("-1", "密码错误!");
            }
        } else {
            resultMap = getResultMap("-1", "此账号不存在!");
        }

        outJson(resultMap);
    }

    /**
     * 绑定企业手机号
     */
    @RequestMapping(value = "boundPhoneNum")
    public void boundPhoneNum() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String phoneNum = request.getParameter("phoneNum");
        String validationCode = request.getParameter("validationCode");
        String sessionCode = (String) request.getSession().getAttribute("validationCode");

        int count = companyInfoServices.getCompanyInfoByAccount(phoneNum);
        if (count > 0) {
            resultMap = getResultMap("0", "此号码已经被绑定！");
        } else {
            if (null == sessionCode || "".equals(sessionCode)) {
                resultMap = getResultMap("0", "验证码已过期！");
            } else {
                if (!validationCode.equals(sessionCode)) {
                    resultMap = getResultMap("0", "验证码错误！");
                } else {
                    CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(id));
                    if (companyInfo != null) {
                        companyInfo.setMobile(phoneNum);
                        companyInfo.setUpdateDate(new Date());
                        int flag = companyInfoServices.update(companyInfo);
                        if (flag > 0) {
                            resultMap = getResultMap("1", "绑定成功！");
                        } else {
                            resultMap = getResultMap("0", "绑定失败！");
                        }
                    } else {
                        resultMap = getResultMap("0", "参数错误！");
                    }
                }
            }
        }
        outJson(resultMap);
    }

    /**
     * 完善企业信息
     *
     * @param request
     * @param companyInfo
     */
    @RequestMapping(value = "perfectCompanyInfo")
    public void perfectCompanyInfo(HttpServletRequest request, @ModelAttribute CompanyInfo companyInfo) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        CompanyInfo companyInfo1 = companyInfoServices.get(companyInfo.getId());
        if (companyInfo.getCompanyName() != null && !"".equals(companyInfo.getCompanyName())) {
            companyInfo1.setCompanyName(companyInfo.getCompanyName());
        }
        if (companyInfo.getAddress() != null && !"".equals(companyInfo.getAddress())) {

            companyInfo1.setAddress(companyInfo.getAddress());
            companyInfo1.setGpsLongitude(companyInfo.getGpsLongitude());
            companyInfo1.setGpsLatitude(companyInfo.getGpsLatitude());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("zoneName", companyInfo.getData4());
            List<Zone> zoneList = zoneService.getZones(map);
            Zone zone = zoneList.get(0);

            companyInfo1.setZoneId(zone.getId());
            companyInfo1.setData4(companyInfo.getData4());
            companyInfo1.setData5(companyInfo.getData5());

            Zone zone_ = zoneService.get(Long.parseLong(companyInfo.getData5()));//区域所在名称
            String areaName = zone_.getName();//所在区域名称
            String cityId = zone_.getPid();
            zone_ = zoneService.get(Long.parseLong(cityId));
            String cityName = zone_.getName();
            String provinceId = zone_.getPid();
            zone_ = zoneService.get(Long.parseLong(provinceId));
            String provinceName = zone_.getName();
            companyInfo1.setZoneName(provinceName + cityName + areaName);

            companyInfo1.setData6(provinceName);
            companyInfo1.setData7(cityName);
            companyInfo1.setData8(areaName);

        }
        if (companyInfo.getWeixinNum() != null && !"".equals(companyInfo.getWeixinNum())) {
            companyInfo1.setWeixinNum(companyInfo.getWeixinNum());
        }
        if (companyInfo.getEmail() != null && !"".equals(companyInfo.getEmail())) {
            companyInfo1.setEmail(companyInfo.getEmail());
        }
        if (companyInfo.getTelephone() != null && !"".equals(companyInfo.getTelephone())) {
            companyInfo1.setTelephone(companyInfo.getTelephone());
        }
        if (companyInfo.getIntro() != null && !"".equals(companyInfo.getIntro())) {
            companyInfo1.setIntro(companyInfo.getIntro());
        }
        if (companyInfo.getCompanyUrl() != null && !"".equals(companyInfo.getCompanyUrl())) {
            companyInfo1.setCompanyUrl(companyInfo.getCompanyUrl());
        }
        companyInfo1.setCompanySize(companyInfo.getCompanySize());
        companyInfo1.setIndustryId(companyInfo.getIndustryId());
        companyInfo1.setShortName(PinyinUtil.getPinYinHeadChar(companyInfo.getCompanyName()));

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator fileNames = multipartRequest.getFileNames();//可以上传一张也可以上传多张图片

        try {
            for (int i = 0; fileNames.hasNext(); ++i) {
                String name = (String) fileNames.next();
                MultipartFile myfile = multipartRequest.getFile(name);
                if (myfile.isEmpty()) {
                    logger.info("文件未上传");
                } else {
                    String OriginalFileName = myfile.getOriginalFilename();
                    String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                    String relative_path = formatdate.format(new Date());
                    String serverPath = ConstantUtil.SERVER_URL;
                    String realPath = ConstantUtil.SAVE_PATH + "/" + "company" + "/" + relative_path;
                    companyInfo1.setLogoPath("/" + "company" + "/" + relative_path + saveName);
                    companyInfo1.setLogoRealPath(serverPath + "/" + "company" + "/" + relative_path + saveName);
                    File filePath = new File(realPath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        companyInfo1.setIndustryName(companyInfo.getIndustryName());

        companyInfo1.setUpdateDate(date);
        int flag = companyInfoServices.update(companyInfo1);
        if (flag > 0) {
            resultMap = getResultMap("1", "保存成功！", companyInfo1);
        } else {
            resultMap = getResultMap("0", "保存失败，请重试！");
        }
        outJson(resultMap);
    }

    /**
     * 上传企业资质
     *
     * @param request
     */
    @RequestMapping(value = "uploadCredentials")
    public void uploadCredentials(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(companyId));
        if (companyInfo != null) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator fileNames = multipartRequest.getFileNames();//可以上传一张也可以上传多张图片
            try {
                for (int i = 0; fileNames.hasNext(); ++i) {
                    String name = (String) fileNames.next();
                    MultipartFile myfile = multipartRequest.getFile(name);
                    if (myfile.isEmpty()) {
                        logger.info("文件未上传");
                    } else {
                        String OriginalFileName = myfile.getOriginalFilename();
                        String saveName = DateUtil.getSystemTime().getTime() + "" + i + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                        String relative_path = formatdate.format(new Date());
                        String serverPath = ConstantUtil.SERVER_URL;
                        String realPath = ConstantUtil.SAVE_PATH + "/" + "company" + "/" + relative_path;
                        companyInfo.setCredentialsPath("/" + "company" + "/" + relative_path + saveName);
                        companyInfo.setCredentialsRealPath(serverPath + "/" + "company" + "/" + relative_path + saveName);
                        File filePath = new File(realPath);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Date date = new Date();
            companyInfo.setUpdateDate(date);
            int flag = companyInfoServices.update(companyInfo);
            if (flag > 0) {
                resultMap = getResultMap("1", "保存成功！");
            } else {
                resultMap = getResultMap("0", "保存失败，请重试！");
            }

        } else {
            resultMap = getResultMap("0", "参数错误！");
        }
        outJson(resultMap);
    }


    /**
     * 根据区域id拼接区域名称
     *
     * @param zoneId
     * @return
     */
    public String getZoneName(long zoneId) {
        Zone zone = zoneService.get(zoneId);
        String zoneName = "";
        if (Integer.parseInt(zone.getLevel()) == 5) {
            Zone zone4 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone3 = zoneService.getZoneByCode(zone4.getPcode());
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName() + zone4.getName() + zone.getName();
        } else if (Integer.parseInt(zone.getLevel()) == 4) {
            Zone zone3 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName() + zone.getName();
        } else if (Integer.parseInt(zone.getLevel()) == 3) {
            Zone zone2 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone2.getName() + zone.getName();
        } else if (Integer.parseInt(zone.getLevel()) == 2) {
            Zone zone1 = zoneService.getZoneByCode(zone.getPcode());
            zoneName = zone1.getName() + zone.getName();
        } else if (Integer.parseInt(zone.getLevel()) == 1) {
            zoneName = zone.getName();
        }
        return zoneName;
    }


    /**
     * 修改密码
     */
    @RequestMapping(value = "modifyPassword")
    public void modifyPassword(@RequestParam String password, @RequestParam String newPassword, @RequestParam String confirmPassword) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");//会员id

        CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(memberId));
        if (companyInfo != null) {

            if (companyInfo.getPassword().equals(MD5Util.getMD5(password))) {
                if (!"".equals(newPassword)) {

                    if (!"".equals(confirmPassword)) {

                        if (newPassword.equals(confirmPassword)) {

                            companyInfo.setCreateDate(new Date());
                            companyInfo.setPassword(MD5Util.getMD5(confirmPassword));
                            int flag = companyInfoServices.update(companyInfo);
                            if (flag > 0) {
                                resultMap = getResultMap("1", "密码修改成功!");
                            }
                        } else {
                            resultMap = getResultMap("-1", "新密码和确认密码不匹配!");
                        }
                    } else {
                        resultMap = getResultMap("-1", "确认密码不能为空!");
                    }
                } else {
                    resultMap = getResultMap("-1", "新密码不能为空!");
                }
            } else {
                resultMap = getResultMap("-1", "原密码错误!");
            }

        }

        outJson(resultMap);
    }

    /**
     * 判断企业手原来的手机号获得的验证码是否正确
     */
    @RequestMapping(value = "checkCompanyMessageIsRight")
    public void checkCompanyMessageIsRight() {

        Map<String, Object> resultMap = new HashMap<String, Object>();


        String phoneNum = request.getParameter("phoneNum");//企业手机号
        String validationCode_ = request.getParameter("validationCode");//短信验证码

        String validationCode = request.getSession().getAttribute("validationCode") + "";
        String mobile = request.getSession().getAttribute("mobile") + "";//session里面缓存的企业手机号

        if (!"".equals(validationCode)) {
            System.out.println(".............");
            if (!validationCode.equals(validationCode_)) {
                resultMap = getResultMap("-1", "短信验证码不匹配!");
                outJson(resultMap);
                return;
            }
        }

        if (!"".equals(mobile)) {
            if (!mobile.equals(phoneNum)) {
                resultMap = getResultMap("-1", "手机号码不匹配!");
                outJson(resultMap);
                return;
            }
        }

        if (!"".equals(validationCode) && !"".equals(mobile)) {
            if (validationCode.equals(validationCode_) && mobile.equals(phoneNum)) {

                resultMap = getResultMap("1", "手机号和验证码匹配正确");
                outJson(resultMap);
            }
        }


    }


    /**
     * 解除绑定手机号前,判断原来的手机号接收的验证码是否正确
     */
    @RequestMapping(value = "terminateBoundCompanyNum")
    public void terminateBoundCompanyNum() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String companyId = request.getParameter("id");//企业id
        String phoneNewNum = request.getParameter("phoneNewNum");//最新绑定的企业手机号
        String validationCode = request.getParameter("validationCode");//验证码

        CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(companyId));

        String validationCode_ = request.getSession().getAttribute("validationCode") + "";
        String mobile = request.getSession().getAttribute("mobile") + "";


        if (!validationCode_.equals(validationCode)) {
            resultMap = getResultMap("-1", "短信验证码不正确!");
            outJson(resultMap);
            return;
        }

        if (phoneNewNum.equals(companyInfo.getMobile())) {
            resultMap = getResultMap("-1", "最新绑定的手机号不能和原手机号一样!");
            outJson(resultMap);
            return;
        }

        if (!mobile.equals(phoneNewNum)) {
            resultMap = getResultMap("-1", "绑定手机号和发送短信验证码的手机号不匹配!");
            outJson(resultMap);
            return;
        } else {
            companyInfo.setMobile(phoneNewNum);
        }

        companyInfo.setUpdateDate(new Date());
        int flag = companyInfoServices.update(companyInfo);
        if (flag > 0) {
            resultMap = getResultMap("1", "新手机号绑定成功!");
        }
        outJson(resultMap);
    }


}
