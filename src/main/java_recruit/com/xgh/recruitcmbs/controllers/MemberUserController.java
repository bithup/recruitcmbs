package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.DictionaryDaoImpl;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.services.*;
import com.xgh.recruitcmbs.util.ConstantUtil;
import com.xgh.recruitcmbs.util.JedisUtil;
import com.xgh.security.MD5Util;
import com.xgh.util.DateUtil;
import com.xgh.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BSX on 2017/3/23.
 */
@Controller
@Scope("prototype")
@RequestMapping("/recruitcmbs/memberUser/")
public class MemberUserController extends BaseController {

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IFileDataService fileDataService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IResumeService resumeService;

    @Autowired
    protected ITotalMemberUserService totalMemberUserService;

    @Autowired
    protected IJobObjectiveService jobObjectiveService;

    @Autowired
    protected IEduExperienceService eduExperienceService;

    @Autowired
    protected IWorkExperienceService workExperienceService;

    @Autowired
    protected JedisUtil jedisUtil;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 求职者注册
     */
    @RequestMapping(value = "register")
    public void register() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String validationCode = request.getParameter("validationCode");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        if (null == account || "".equals(account)) {
            resultMap = getResultMap("0", "手机号不能为空！");
        } else if (null == validationCode || "".equals(validationCode)) {
            resultMap = getResultMap("0", "请输入验证码！");
        } else if (null == password || "".equals(password)) {
            resultMap = getResultMap("0", "密码不能为空！");
        } else if (null == checkPassword || "".equals(checkPassword)) {
            resultMap = getResultMap("0", "确认密码不能为空！");
        } else if (!password.equals(checkPassword)) {
            resultMap = getResultMap("0", "密码和确认密码不一致！");
        } else {
/*            TotalMemebrUser totalMemebrUser = totalMemberUserService.login(account);
            if (totalMemebrUser != null) {
                resultMap = getResultMap("0", "此号码已经被注册!");
                outJson(resultMap);
                return;
            }*/

            int count = memberUserService.isAlreadyRegister(account).size();
            if (count > 0) {
                resultMap = getResultMap("0", "此号码已经被注册！");
            } else {
                String mobile_ = (String) request.getSession().getAttribute("mobile");
                String validationCode_ = (String) request.getSession().getAttribute("validationCode");
                if (null == validationCode_ || "".equals(validationCode_)) {
                    resultMap = getResultMap("0", "验证码已过期");
                } else if (!account.equals(mobile_)) {
                    resultMap = getResultMap("0", "手机号不一致！");
                } else if (!validationCode.equals(validationCode_)) {
                    resultMap = getResultMap("0", "验证码错误！");
                } else {

                    MemberUser memberUser = new MemberUser();
                    memberUser.setAccount(account);
                    memberUser.setPassword(MD5Util.getMD5(password));
                    int flag = memberUserService.add(memberUser);
                    if (flag > 0) {
                        Resume resume = resumeService.getByMemberId(memberUser.getId());

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("memberUser", memberUser);
                        memberUser.setData3(String.valueOf(resume.getId()));
                        resultMap = getResultMap("1", "注册成功！", memberUser);
                    } else {
                        resultMap = getResultMap("0", "注册失败！");
                    }
                }
            }
        }
        outJson(resultMap);
    }


    /**
     * 求职者注册时验证手机号是否已经注册
     */
    @RequestMapping(value = "isAlreadyRegister")
    public void isAlreadyRegister() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        List<MemberUser> list = memberUserService.isAlreadyRegister(account);
        if (list != null && list.size() > 0) {
            resultMap = getResultMap("0", "此号码已经被注册！");
        } else {
            resultMap = getResultMap("1", "此号码未被注册，可使用！");
        }
        outJson(resultMap);
    }


    /**
     * 求职者登录
     */
    @RequestMapping(value = "login")
    public void login() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String password = request.getParameter("password");


/*        TotalMemebrUser totalMemebrUser = totalMemberUserService.login(account);


        String password_ = MD5Util.getMD5(password);

        List<MemberUser> memberUserList = memberUserService.isAlreadyRegister(account);

        if (totalMemebrUser != null && password_.equals(totalMemebrUser.getPassword())) {
            if (memberUserList != null && memberUserList.size() > 0) {

                MemberUser memberUser = memberUserList.get(0);

                memberUser.setHeadPath(ConstantUtil.SERVER_URL + memberUser.getHeadPath());
                Resume resume = resumeService.getByMemberId(memberUser.getId());
                memberUser.setData3(String.valueOf(resume.getId()));

                resultMap = getResultMap("1", "登录成功", memberUser);

            } else {

                MemberUser memberUser = new MemberUser();
                memberUser.setAccount(account);
                memberUser.setPassword(MD5Util.getMD5(password));
                memberUser.setIsOpen(1);
                memberUser.setStatus(1);
                memberUser.setCreateDate(new Date());
                memberUser.setUpdateDate(new Date());
                int flag = memberUserService.add(memberUser);
                if (flag > 0) {
                    memberUser.setHeadPath(ConstantUtil.SERVER_URL + memberUser.getHeadPath());
                    Resume resume = resumeService.getByMemberId(memberUser.getId());
                    memberUser.setData3(String.valueOf(resume.getId()));

                    resultMap = getResultMap("1", "登录成功", memberUser);
                }

            }

        } else {
            resultMap = getResultMap("0", "此号码尚未注册,请先注册!");
        }*/


        List<MemberUser> list = memberUserService.isAlreadyRegister(account);
        if (list.size() == 0) {
            resultMap = getResultMap("0", "此号码尚未注册，请先注册！");
        } else if (list.size() == 1) {
            MemberUser memberUser = list.get(0);
            String password_ = memberUser.getPassword();
            if (MD5Util.getMD5(password).equals(password_)) {
                //个人信息状态
                String email = memberUser.getEmail();

                Map<String, Object> map = new HashMap<String, Object>();
                //说明个人信息已经完善
                if (email != null) {
                    map.put("memberInfoStatus", "1");
                } else {
                    map.put("memberInfoStatus", "0");
                }

              /*memberUser.setHeadPath(ConstantUtil.SERVER_URL + memberUser.getHeadPath());*/
                Resume resume = resumeService.getByMemberId(memberUser.getId());
                memberUser.setData3(String.valueOf(resume.getId()));//简历id


                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("resumeId", resume.getId());

                //工作经历
                List<WorkExperience> workExperience = workExperienceService.getByResumeId(paramMap);
                if (workExperience != null && workExperience.size() > 0) {
                    map.put("workExperienceId", workExperience.get(0).getId());
                    map.put("workExperienceStatus", "1");
                } else {
                    map.put("workExperienceStatus", "0");
                }

                //教育经历
                List<EduExperience> eduExperiences = eduExperienceService.getByResumeId(paramMap);
                if (eduExperiences != null && eduExperiences.size() > 0) {
                    map.put("eduExperiencesId", eduExperiences.get(0).getId());
                    map.put("eduExperiencesStatus", "1");
                } else {
                    map.put("eduExperiencesStatus", "0");
                }

                //求职意向状态
                JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
                String jobName = jobObjective.getJobName();
                if (jobName != null) {
                    map.put("jobObjectiveId", jobObjective.getId());
                    map.put("jobObjectiveStatus", "1");
                } else {
                    map.put("jobObjectiveStatus", "0");
                }
                map.put("resumeId", resume.getId());
                map.put("memberId", memberUser.getId());
                resultMap = getResultMap("1", "登录成功！", map);
            } else {
                resultMap = getResultMap("0", "密码错误！");
            }
        } else {
            resultMap = getResultMap("0", "账号错误！");
        }
        outJson(resultMap);
    }

    /**
     * 企业点击转正的时候，让企业设置支付密码
     */
    @RequestMapping(value = "setPayPassword")
    public void setPayPassword() {

        Map<String, Object> resultMap = new HashMap<String, Object>();





    }


    /**
     * 求职者忘记密码
     */
    @RequestMapping(value = "forgetPassword")
    public void forgetPassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String account = request.getParameter("account");
        String validationCode = request.getParameter("validationCode");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");

        if (null == account || "".equals(account)) {
            resultMap = getResultMap("0", "手机号不能为空！");
        } else if (null == validationCode || "".equals(validationCode)) {
            resultMap = getResultMap("0", "请输入验证码！");
        } else if (null == password || "".equals(password)) {
            resultMap = getResultMap("0", "密码不能为空！");
        } else if (null == checkPassword || "".equals(checkPassword)) {
            resultMap = getResultMap("0", "确认密码不能为空！");
        } else if (!password.equals(checkPassword)) {
            resultMap = getResultMap("0", "密码和确认密码不一致！");
        } else {
            List<MemberUser> list = memberUserService.isAlreadyRegister(account);
            int count = list.size();
            if (count == 0) {
                resultMap = getResultMap("0", "此号码尚未注册，请先注册或确认号码正确！");
            } else {
                String mobile_ = (String) request.getSession().getAttribute("mobile");
                String validationCode_ = (String) request.getSession().getAttribute("validationCode");
                if (null == validationCode_ || "".equals(validationCode_)) {
                    resultMap = getResultMap("0", "验证码已过期");
                } else if (!account.equals(mobile_)) {
                    resultMap = getResultMap("0", "手机号不一致！");
                } else if (!validationCode.equals(validationCode_)) {
                    resultMap = getResultMap("0", "验证码错误！");
                } else {
                    MemberUser memberUser = list.get(0);
                    memberUser.setPassword(MD5Util.getMD5(password));
                    memberUser.setUpdateDate(new Date());
                    int flag = memberUserService.update(memberUser);
                    if (flag > 0) {
                        resultMap = getResultMap("1", "操作成功！");
                    } else {
                        resultMap = getResultMap("0", "操作失败！");
                    }
                }
            }
        }
        outJson(resultMap);
    }


    /**
     * 求职者修改密码
     */
    @RequestMapping(value = "modifyPassword")
    public void modifyPassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));
        if (null == memberUser) {
            resultMap = getResultMap("0", "用户不存在");
        } else {
            if (oldPassword != null || !"".equals(oldPassword)) {
                String oldPassword_ = MD5Util.getMD5(oldPassword);
                if (!oldPassword_.equals(memberUser.getPassword())) {
                    resultMap = getResultMap("0", "原密码不正确!");
                    outJson(resultMap);
                    return;
                }
            }

            if (null == password || "".equals(password)) {
                resultMap = getResultMap("0", "密码不能为空！");
            } else if (null == checkPassword || "".equals(checkPassword)) {
                resultMap = getResultMap("0", "确认密码不能为空！");
            } else if (!password.equals(checkPassword)) {
                resultMap = getResultMap("0", "密码和确认密码不一致！");
            } else {
                memberUser.setUpdateDate(new Date());
                memberUser.setPassword(MD5Util.getMD5(password));
                int flag = memberUserService.update(memberUser);
                if (flag > 0) {
                    resultMap = getResultMap("1", "修改密码成功！");
                } else {
                    resultMap = getResultMap("0", "修改密码失败，请重试!");
                }
            }
        }
        outJson(resultMap);
    }

    /**
     * 添加个人信息
     */
    @RequestMapping(value = "addMemberUserInfo")
    public void addMemberUserInfo(@ModelAttribute MemberUser memberUser) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        long memberId = memberUser.getId();

        MemberUser memberUser_ = memberUserService.get(memberId);
        if (memberUser_ != null) {

            memberUser_.setSex(memberUser.getSex());
            memberUser_.setRealName(memberUser.getRealName());
            memberUser_.setNickName(memberUser.getRealName());
            memberUser_.setBirthday(memberUser.getBirthday());
            memberUser_.setEducation(memberUser.getEducation());
            memberUser_.setEmail(memberUser.getEmail());
            memberUser_.setTelephone(memberUser.getTelephone());
            memberUser_.setWorkYear(memberUser.getWorkYear());
            memberUser_.setUpdateDate(new Date());
            memberUser_.setZoneId(memberUser.getZoneId());

            long zoneId = memberUser.getZoneId();
            Zone zone = zoneService.get(zoneId);
            if (zone != null) {
                memberUser_.setZoneName(zone.getName());
            }
            addHeadPath(request, memberUser_);//添加图片
        }
        int flag = memberUserService.update(memberUser_);
        if (flag > 0) {

            Resume resume = resumeService.getByMemberId(memberId);//个人信息完善后,简历完整度增加10%

            String integrity = resume.getIntegrity();
            resume.setIntegrity(String.valueOf(Integer.parseInt(integrity) + 10));
            resume.setUpdateDate(new Date());
            resumeService.update(resume);


            Map<String, Object> map = new HashMap<String, Object>();

            map.put("realName", memberUser_.getRealName());
            map.put("sex", memberUser_.getSex());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date_ = format.format(memberUser_.getBirthday());
            map.put("birthday", date_);
            map.put("education", memberUser_.getEducation());

            Map<String, Object> objMap = new HashMap<String, Object>();
            objMap.put("item", "edu");
            objMap.put("code", memberUser_.getEducation());

            Dictionary dictionary = dictionaryService.getNameByCode(objMap);
            map.put("educationName", dictionary.getValue());

            Map<String, Object> workMap = new HashMap<String, Object>();
            workMap.put("item", "workyear");
            workMap.put("code", memberUser_.getWorkYear());

            Dictionary dictionary_ = dictionaryService.getNameByCode(workMap);

            map.put("workYear", memberUser_.getWorkYear());
            map.put("workYearName", dictionary_.getValue());

            map.put("telephone", memberUser_.getTelephone());
            map.put("email", memberUser_.getEmail());
            map.put("zoneId", memberUser_.getZoneId());
            map.put("zoneName", memberUser_.getZoneName());
            map.put("headPath", ConstantUtil.SERVER_URL + memberUser_.getHeadPath());
            System.out.println("1");
            resultMap = getResultMap("1", "个人信息修改成功!", map);
        } else {
            resultMap = getResultMap("-1", "个人信息修改失败!");
        }

        outJson(resultMap);
    }

    /**
     * 修改展示个人基本信息
     */
    @RequestMapping(value = "showMemberUserInfo")
    public void showMemberUserInfo() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("id");//用户id
        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));
        if (memberUser != null) {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("realName", memberUser.getRealName());
            map.put("sex", memberUser.getSex());

/*            try {
                String ddd = com.xgh.recruitcmbs.util.DateUtil.formatDate(memberUser.getBirthday());
                System.out.println(ddd);
            } catch (Exception e) {

            }*/


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date_ = format.format(memberUser.getBirthday());
            map.put("birthday", date_);
            map.put("education", memberUser.getEducation());

            Map<String, Object> objMap = new HashMap<String, Object>();
            objMap.put("item", "edu");
            objMap.put("code", memberUser.getEducation());

            Dictionary dictionary = dictionaryService.getNameByCode(objMap);
            map.put("educationName", dictionary.getValue());

            Map<String, Object> workMap = new HashMap<String, Object>();
            workMap.put("item", "workyear");
            workMap.put("code", memberUser.getWorkYear());

            Dictionary dictionary_ = dictionaryService.getNameByCode(workMap);

            map.put("workYear", memberUser.getWorkYear());
            map.put("workYearName", dictionary_.getValue());


            map.put("telephone", memberUser.getTelephone());
            map.put("email", memberUser.getEmail());
            map.put("zoneId", memberUser.getZoneId());
            map.put("zoneName", memberUser.getZoneName());
            map.put("headPath", ConstantUtil.SERVER_URL + memberUser.getHeadPath());

            resultMap = getResultMap("1", "用户信息展示成功!", map);
        } else {
            resultMap = getResultMap("-1", "用户信息展示失败!");
        }

        outJson(resultMap);
    }


    /**
     * 添加个人头像图片
     *
     * @param request
     * @return
     */
    public boolean addHeadPath(HttpServletRequest request, MemberUser memberUser) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator fileNames = multipartRequest.getFileNames();//可以上传一张也可以上传多张图片
        if (fileNames != null && fileNames.hasNext()) {

            String name = (String) fileNames.next();
            MultipartFile myfile = multipartRequest.getFile(name);
            String OriginalFileName = myfile.getOriginalFilename();
            if (!"".equals(OriginalFileName)) {

                String saveName = DateUtil.getSystemTime().getTime() + "" + OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());
                SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
                String relative_path = formatdate.format(new Date());
                String serverPath = ConstantUtil.SERVER_URL;
                String realPath = ConstantUtil.SAVE_PATH + "/" + "memberUser" + "/" + relative_path;

                memberUser.setHeadPath("/" + "memberUser" + "/" + relative_path + saveName);
                memberUser.setHeadRealPath(serverPath + "/" + "memberUser" + "/" + relative_path + saveName);
                File filePath = new File(realPath);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                try {
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }
        }
        return true;

    }

    /**
     * 生成二维码
     */
    @RequestMapping(value = "getQrCodeUrl")
    public ModelAndView getQrCodeUrl() {

        String promotionId = request.getParameter("promotionId");//推广号码
        int a = promotionId.indexOf("1");
        System.out.println(a);
        promotionId = promotionId.substring(a, promotionId.length());
        TotalMemebrUser totalMemebrUser = totalMemberUserService.get(Long.parseLong(promotionId));
        String account = totalMemebrUser.getAccount();
        String mobile_ = account.substring(4, 8);
        String accountNo = account.replace(mobile_, "****");
        ModelAndView view = new ModelAndView();
        view.addObject("label", "ZPFSQ");
        view.addObject("account", accountNo);
        view.addObject("promotionId", promotionId);
        view.setViewName("recruit/qrcode/demo");
        return view;
    }


    /**
     * 求职者自我评价
     */
    @RequestMapping(value = "selfAddRemark")
    public void selfAddRemark() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        String selfEvaluation = request.getParameter("selfEvaluation");

        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));
        if (memberUser != null) {
            memberUser.setSelfEvaluation(selfEvaluation);
            memberUser.setUpdateDate(new Date());
        }

        int flag = memberUserService.update(memberUser);
        if (flag > 0) {
            resultMap = getResultMap("1", "自我评价成功!");
        } else {
            resultMap = getResultMap("-1", "自我评价失败!");
        }

        outJson(resultMap);
    }

    /**
     * redis缓存测试
     */
    @RequestMapping(value = "redisTest")
    public void redisTest() {

  /*      long flag = jedisUtil.SETS.sadd("password", "654321");

        long count = jedisUtil.SETS.scard("password");*/

        List<String> passwordList = jedisUtil.KEYS.sort("fee");
        if (passwordList != null && passwordList.size() > 0) {
            for (String value : passwordList) {

                System.out.println(value);
            }
        }


        System.out.println(passwordList.size());

       /* System.out.println("..." + flag);*/

    }


    /**
     * 个人中心信息展示
     */
    @RequestMapping(value = "memberIndexInfo")
    public void memberIndexInfo() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        Map<String, Object> memberUserMap = memberUserService.memberIndexInfo(map);

        if (memberUserMap != null && !memberUserMap.isEmpty()) {
            Object realName = memberUserMap.get("realName");
            if (realName == null) {
                resultMap = getResultMap("0", "显示失败!");
            } else {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("item", "serviceStatus");
                paramMap.put("code", memberUserMap.get("serviceStatus"));
                Dictionary dictionary = dictionaryService.getNameByCode(paramMap);
                memberUserMap.put("serviceStatus", dictionary.getValue());
                memberUserMap.put("headPath", ConstantUtil.SERVER_URL + memberUserMap.get("headPath") + "");
                resultMap = getResultMap("1", "显示成功!", memberUserMap);
            }

        } else {
            resultMap = getResultMap("0", "显示失败!");
        }

        outJson(resultMap);
    }


}
