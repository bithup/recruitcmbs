package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.ICompanyInfoDao;
import com.xgh.recruitcmbs.dao.IZoneDao;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.services.*;
import com.xgh.recruitcmbs.util.DoubleUtil;
import javafx.geometry.Pos;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CQ on 2017/3/22.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/position/")
public class PositionController extends BaseController {

    Logger logger = Logger.getLogger(PositionController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    protected IPositionService positionService;

    @Autowired
    protected IResumeDeliveryService resumeDeliverService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IResumeService resumeService;

    @Autowired
    protected IJobObjectiveService jobObjectiveService;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IInterviewService interviewService;

    @Autowired
    protected IHouseService houseService;

    @Autowired
    protected IZoneDao zoneDao;


    /**
     * 企业发布职位列表展示
     */
    @RequestMapping(value = "getCompanyPositionList")
    public void getPositionList() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String companyId = request.getParameter("companyId");//企业id
        String positionStatus = request.getParameter("positionStatus");//职位状态

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("positionStatus", positionStatus);

        List<Map<String, Object>> positionList_ = new ArrayList<Map<String, Object>>();

        List<Position> positionList = positionService.getPositionList(map);
        if (positionList != null && positionList.size() > 0) {

            for (Position position : positionList) {
                Map<String, Object> map_ = new LinkedHashMap<String, Object>();
                int count = resumeDeliverService.getDeliveryCount(position.getId());
                map_.put("id", position.getId());//职位id
                map_.put("jobName", position.getJobName());//职位名称
                map_.put("count", count);//职位投递数量
                positionList_.add(map_);
            }
            resultMap = getResultMap("1", "列表显示成功!", positionList_);
        } else {
            resultMap = getResultMap("-1", "暂无职位列表!");
        }
        outJson(resultMap);

    }

    /**
     * 编辑企业发布职位调用接口
     */
    @RequestMapping(value = "getPositionInfo")
    public void getPositionInfo() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String positionId = request.getParameter("id");

        Map<String, Object> positionMap = positionService.getPositionInfo(Long.parseLong(positionId));
        if (positionMap != null && !positionMap.isEmpty()) {

            String jobType = positionMap.get("jobType") + "";
            if ("1".equals(jobType)) {
                positionMap.put("jobType", "全职");
            } else {
                positionMap.put("jobType", "兼职");
            }

            String experience = positionMap.get("experience") + "";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("item", "jobyear");
            map.put("code", experience);
            Dictionary dictionary = dictionaryService.getNameByCode(map);
            positionMap.put("experience", dictionary.getValue());
            positionMap.put("experienceCode", dictionary.getCode());

            String joblabel = positionMap.get("positionLabel") + "";
            String[] joblabel_ = joblabel.split(",");//福利标签

            Map<String, Object> map_ = new HashMap<String, Object>();


            if (joblabel_ != null && joblabel_.length > 0) {

                String label_ = "";

                for (int i = 0; i < joblabel_.length; i++) {
                    map_.put("item", "joblabel");
                    map_.put("code", joblabel_[i]);
                    Dictionary dictionary_ = dictionaryService.getNameByCode(map_);
                    if (dictionary_ != null) {
                        if (i == joblabel_.length - 1) {
                            label_ += dictionary_.getValue();
                        } else {
                            label_ += dictionary_.getValue() + ",";
                        }
                    }
                }

                positionMap.put("positionLabel", label_);
                positionMap.put("positionLabelId", joblabel);
            }

            String edu = positionMap.get("qualification") + "";
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("item", "edu");
            paramMap.put("code", edu);

            Dictionary dictionary_ = dictionaryService.getNameByCode(paramMap);
            positionMap.put("qualification", dictionary_.getValue());
            positionMap.put("qualificationCode", dictionary_.getCode());


            resultMap = getResultMap("1", "职位显示成功", positionMap);
        }
        outJson(resultMap);

    }


    /**
     * 企业添加发布职位
     */
    @RequestMapping(value = "add")
    public void add(@ModelAttribute Position position) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Long jobKindsId = position.getJobKindsId();
        if (jobKindsId == null) {

            resultMap = getResultMap("-1", "职位类型不能为空!");
            outJson(resultMap);
            return;
        }

        String jobName = position.getJobName();
        if ("".equals(jobName)) {
            resultMap = getResultMap("-1", "职位名称不能为空!");
            outJson(resultMap);
            return;
        }

        String salaryMin = position.getSalaryMin();
        if ("".equals(salaryMin)) {
            resultMap = getResultMap("-1", "薪资范围不能为空!");
            outJson(resultMap);
            return;
        }

        String salaryMax = position.getSalaryMax();
        if ("".equals(salaryMax)) {
            resultMap = getResultMap("-1", "薪资范围不能为空!");
            outJson(resultMap);
            return;
        }

        Date expiryDate = position.getExpiryDate();
        if (expiryDate == null) {
            resultMap = getResultMap("-1", "截止日期不能为空!");
            outJson(resultMap);
            return;
        }

        Integer num = position.getExperience();
        if (num == null) {
            resultMap = getResultMap("-1", "经验要求不能为空!");
            outJson(resultMap);
            return;
        }

        Integer edu = position.getQualification();
        if (edu == null) {
            resultMap = getResultMap("-1", "学历不能为空!");
            outJson(resultMap);
            return;
        }

        Integer type = position.getJobType();
        if (type == null) {
            resultMap = getResultMap("-1", "职位性质不能为空!");
            outJson(resultMap);
            return;
        }

        Integer recruitNum = position.getRecruitingNum();
        if (recruitNum == null) {
            resultMap = getResultMap("-1", "招聘人数不能为空!");
            outJson(resultMap);
            return;
        }

        String zoneName = position.getZoneName();
        if ("".equals(zoneName)) {
            resultMap = getResultMap("-1", "工作城市不能为空!");
            outJson(resultMap);
            return;
        }

        String jobDescription = position.getJobDescription();
        if ("".equals(jobDescription)) {
            resultMap = getResultMap("-1", "职位描述不能为空!");
            outJson(resultMap);
            return;
        }

        String label = position.getPositionLabel();
        if ("".equals(label)) {
            resultMap = getResultMap("-1", "职位亮点不能为空!");
            outJson(resultMap);
            return;
        }

        if (position.getId() > 0) {
            int flag = positionService.update(request, position);
            if (flag > 0) {
                resultMap = getResultMap("1", "修改职位成功!");
            } else {
                resultMap = getResultMap("-1", "修改职位失败!");
            }
        } else {
            int flag = positionService.save(request, position);
            if (flag > 0) {
                resultMap = getResultMap("1", "添加职位成功!");
            } else {
                resultMap = getResultMap("-1", "添加职位成功!");
            }
        }

        outJson(resultMap);
    }

    /**
     * 企业修改职位状态
     */
    @RequestMapping(value = "modifyPositionStatus")
    public void modifyPositionStatus() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String positionIds = request.getParameter("positionIds");
        String type = request.getParameter("type");
        if (!"".equals(positionIds)) {

            String positionIds_[] = positionIds.split(",");

            List<String> list = Arrays.asList(positionIds_);
            if (list != null && list.size() > 0) {

                Map<String, Object> map = new HashMap<String, Object>();
                String msg;
                if ("1".equals(type)) {
                    map.put("positionStatus", 2);
                    msg = "职位暂停状态";
                } else {
                    map.put("positionStatus", 1);
                    msg = "职位恢复正常状态";
                }
                map.put("idList", list);
                int flag = positionService.batchUpdateMap(map);
                if (flag > 0) {
                    resultMap = getResultMap("1", msg + "成功!");
                } else {
                    resultMap = getResultMap("-1", msg + "失败!");
                }
            }
        }

        outJson(resultMap);
    }

    /**
     * 删除职位状态
     */
    @RequestMapping(value = "batchDeleteByIdList")
    public void batchDeleteByIdList() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String positionIds = request.getParameter("positionIds");
        if (!"".equals(positionIds)) {

            String positionIds_[] = positionIds.split(",");

            List<String> list = Arrays.asList(positionIds_);
            if (list != null && list.size() > 0) {
                int flag = positionService.batchDeleteByIdList(list);
                if (flag > 0) {
                    resultMap = getResultMap("1", "职位已经删除成功!");
                }
            }
        }
        outJson(resultMap);
    }


    /**
     * 企业失效职位列表查询
     */
    @RequestMapping(value = "getExpiryPositionList")
    public void getExpiryPositionList() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String companyId = request.getParameter("companyId");//企业id

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);

        List<Map<String, Object>> positionList_ = new ArrayList<Map<String, Object>>();

        List<Position> positionList = positionService.getExpiryPositionList(map);
        if (positionList != null && positionList.size() > 0) {
            for (Position position : positionList) {
                Map<String, Object> map_ = new LinkedHashMap<String, Object>();
                int count = resumeDeliverService.getDeliveryCount(position.getId());
                map_.put("id", position.getId());//职位id
                map_.put("jobName", position.getJobName());//职位名称
                map_.put("count", count);//职位投递数量
                positionList_.add(map_);
                logger.info("...........");
            }
            resultMap = getResultMap("1", "失效列表查询成功!", positionList_);
        } else {
            resultMap = getResultMap("-1", "暂无失效职位!");
        }

        outJson(resultMap);
    }

    /**
     * 根据职位查询求职人员所投递职位列表
     */
    @RequestMapping(value = "getDeliverJobListByPositionId")
    public void getDeliverJobListByPositionId() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String positionId = request.getParameter("positionId");
        String edu = request.getParameter("education");//学历
        String workYear = request.getParameter("workYear");
        String jobType = request.getParameter("jobType");
        String zoneId_ = request.getParameter("zoneId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("zoneName", zoneId_);
        Zone zone_ = null;
        if (!"".equals(zoneId_)) {
            zone_ = zoneService.getIdByName(paramMap);
        }


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("positionId", positionId);
        map.put("education", edu);
        map.put("workYear", workYear);
        map.put("jobType", jobType);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));


        if (zone_ != null) {
            map.put("zoneId", zone_.getId());
        }

        List<LinkedHashMap<String, Object>> resumeDeliverList = resumeDeliverService.getDeliverJobListByPositionId(map);
        if (resumeDeliverList != null && resumeDeliverList.size() > 0) {

            for (Map<String, Object> map_ : resumeDeliverList) {

                String zoneId = map_.get("zoneId") + "";
                Zone zone = zoneService.get(Long.parseLong(zoneId));
                /**
                 * 查询城市姓名
                 */
                String zoneName = zone.getName();
                String pid = zone.getPid();
                zone = zoneService.get(Long.parseLong(pid));
                String cityName = zone.getName();
                map_.put("zoneName", cityName + zoneName);

                /**
                 * 根据code查询字典表name
                 */
                String education = map_.get("education") + "";
                Map<String, Object> eduMap = new HashMap<String, Object>();
                eduMap.put("item", "edu");
                eduMap.put("code", education);

                Dictionary dictionary = dictionaryService.getNameByCode(eduMap);
                map_.put("education", dictionary.getValue());
                map_.remove("zoneId");

            }

            resultMap = getResultMap("1", "查询职位列表成功!", resumeDeliverList);
        } else {
            resultMap = getResultMap("-1", "该职位暂无求职者投递!");
        }
        outJson(resultMap);
    }


    /**
     * 根据职位分类id查询职位列表
     */
    @RequestMapping(value = "getPositionsList")
    public void getPositionsList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();

        String kindsId = request.getParameter("kindsId");
        String zoneName = request.getParameter("zoneName");
        String memberId = request.getParameter("memberId");
        String gpsLongitude = request.getParameter("gpsLongitude");
        String gpsLatitude = request.getParameter("gpsLatitude");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");

        map.put("kindsId", kindsId);
        if (null != memberId && !"".equals(memberId)) {

            Zone zone = zoneDao.getZoneByName(zoneName + "市");
            if (null != zone) {
                map.put("zoneId", zone.getId());
            }
        }
        map.put("longitude", gpsLongitude);
        map.put("latitude", gpsLatitude);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));

        List<Map<String, Object>> list = positionService.getListPage(map);
        for (Map<String, Object> positionMap : list) {

            //公司规模
            dicMap.put("item", "companySize");
            dicMap.put("code", positionMap.get("companySize"));
            positionMap.put("companySize", dictionaryService.getValue(dicMap));

            //工作经验
            dicMap.put("item", "jobyear");
            dicMap.put("code", positionMap.get("experience"));
            positionMap.put("experience", dictionaryService.getValue(dicMap));

            //学历
            dicMap.put("item", "edu");
            dicMap.put("code", positionMap.get("qualification"));
            positionMap.put("qualification", dictionaryService.getValue(dicMap));

            //距离
            positionMap.put("distance", positionMap.get("distance") + "km");

        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "查询职位列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 职位列表分页查询
     */
    @RequestMapping(value = "getPositionsListPage")
    public void getPositionsListPage() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String zoneName = request.getParameter("zoneName");

        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");

        if (null != memberId && !"".equals(memberId)) {
            Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("resumeId", resume.getId());
            JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
            long kindsId = jobObjective.getData1();
            if (kindsId > 0) {
                map.put("kindsId", kindsId);
            }
            System.out.println(".........");
        }
        map.put("zoneName", zoneName);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String, Object>> list = positionService.getListPage(map);
        for (Map<String, Object> positionMap : list) {

            //公司规模
            dicMap.put("item", "companySize");
            dicMap.put("code", positionMap.get("companySize"));
            positionMap.put("companySize", dictionaryService.getValue(dicMap));

            //工作经验
            dicMap.put("item", "jobyear");
            dicMap.put("code", positionMap.get("experience"));
            positionMap.put("experience", dictionaryService.getValue(dicMap));

            //学历
            dicMap.put("item", "edu");
            dicMap.put("code", positionMap.get("qualification"));
            positionMap.put("qualification", dictionaryService.getValue(dicMap));

            //距离
            positionMap.put("distance", positionMap.get("distance") + "km");

        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "查询职位列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 职位详情
     */
    @RequestMapping(value = "positionDetail")
    public void positionDetail() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        String positionId = request.getParameter("positionId");
        String memberId = request.getParameter("memberId");
        Position position = positionService.get(Long.parseLong(positionId));
        if (null != position) {


            Map<String, Object> positionDetail = new HashMap<String, Object>();
            //职位信息
            positionDetail.put("positionId", position.getId());
            positionDetail.put("positionName", position.getJobName());
            positionDetail.put("address", position.getZoneName());
            positionDetail.put("salary", position.getSalaryMin() + "-" + position.getSalaryMax());
            positionDetail.put("jobDescription", position.getJobDescription());
            if (position.getJobType() == 1) {
                positionDetail.put("jobType", "全职");
            } else if (position.getJobType() == 2) {
                positionDetail.put("jobType", "兼职");
            } else {
                positionDetail.put("jobType", "全职");
            }
            dicMap.put("item", "jobyear");
            dicMap.put("code", position.getExperience());
            positionDetail.put("experience", dictionaryService.getValue(dicMap) + "工作经验");
            dicMap.put("item", "edu");
            dicMap.put("code", position.getQualification());
            positionDetail.put("qualification", dictionaryService.getValue(dicMap));
            positionDetail.put("recruitingNum", position.getRecruitingNum() + "人");
            String positionLabel = position.getPositionLabel();
            String[] labels = positionLabel.split(",|，");
            if (labels.length > 0) {
                dicMap.put("item", "joblabel");
                List<String> labelList = new ArrayList<String>();
                for (int i = 0; i < labels.length; i++) {
                    dicMap.put("code", labels[i]);
                    String label = dictionaryService.getValue(dicMap);
                    labelList.add(label);
                }
                positionDetail.put("positionLabel", labelList);
            }

            //相似职位
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("kindsId", position.getJobKindsId());
            map.put("page", 1);
            map.put("pagesize", 4);
            List<Map<String, Object>> positionList = positionService.getListPage(map);
            for (Map<String, Object> positionMap : positionList) {
                dicMap.put("item", "edu");
                dicMap.put("code", positionMap.get("qualification"));
                positionMap.put("qualification", dictionaryService.getValue(dicMap));
                dicMap.put("item", "jobyear");
                dicMap.put("code", positionMap.get("experience"));
                positionMap.put("salary", positionMap.get("salaryMin").toString() + positionMap.get("salaryMax"));
            }
            positionDetail.put("similarJob", positionList);

            //登录时是否已经收藏此职位
            map.put("memberId", memberId);
            map.put("positionId", positionId);
            List<House> houseList = houseService.isExist(map);
            if (houseList.size() > 0) {
                positionDetail.put("isHouse", "1");
            } else {
                positionDetail.put("isHouse", "0");
            }

            //企业详情
            CompanyInfo companyInfo = companyInfoServices.get(position.getCompanyId());
            if (companyInfo!=null&&companyInfo.getId()>0){
                positionDetail.put("companyId", companyInfo.getId());
                positionDetail.put("companyName", companyInfo.getCompanyName());
                dicMap.put("item", "companySize");
                dicMap.put("code", companyInfo.getCompanySize());
                positionDetail.put("companySize", dictionaryService.getValue(dicMap));
                positionDetail.put("industryName", companyInfo.getIndustryName());
                positionDetail.put("logoRealPath", companyInfo.getLogoRealPath());
                positionDetail.put("companyAddress", companyInfo.getZoneName() + companyInfo.getAddress());
                //查询录取的人数
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("companyId", companyInfo.getId());
                paramMap.put("type", 3);
                positionDetail.put("enrollNum", "已有" + interviewService.getEnrollNum(paramMap) + "人被录用");
            }

            resultMap = getResultMap("1", "获取职位详情成功！", positionDetail);
        } else {
            resultMap = getResultMap("0", "获取职位详情失败！");
        }
        outJson(resultMap);
    }


    /**
     * 筛选职位
     */
    @RequestMapping(value = "screenPosition")
    public void screenPosition() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
/*
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");*/
        String distance = request.getParameter("distance");
        String screenSalary = request.getParameter("screenSalary");
        String companySize = request.getParameter("companySize");
        String jobyear = request.getParameter("jobyear");
        String qualification = request.getParameter("qualification");
        String recruitingNum = request.getParameter("recruitingNum");
        String jobType = request.getParameter("jobType");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String memberId = request.getParameter("memberId");
        String zoneName = request.getParameter("zoneName");//当前所在区域


/*      map.put("longitude", longitude);
        map.put("latitude", latitude);*/
        if (null != distance && !"".equals(distance)) {
            dicMap.put("item", "distance");
            List<Map<String, Object>> dicList = dictionaryService.getKeyValue(dicMap);
            String code = "";
            for (Map<String, Object> distanceMap : dicList) {
                if (distanceMap.get("value").toString().equals("全城")) {
                    code = distanceMap.get("code").toString();
                }
            }
            if (code.equals(distance)) {
                map.put("distance", "");
            } else {
                dicMap.put("code", distance);
                String distance_ = dictionaryService.getValue(dicMap);
                distance_ = distance_.substring(0, distance_.indexOf("k"));

                map.put("distance", distance_);
            }
        }
        if (null != screenSalary && !"".equals(screenSalary)) {
            dicMap.put("item", "screenSalary");
            List<Map<String, Object>> dicList = dictionaryService.getKeyValue(dicMap);
            Map<String, Object> min = null;
            Map<String, Object> max = null;
            for (Map<String, Object> salaryMap : dicList) {
                if (salaryMap.get("value").toString().indexOf("以下") > -1) {
                    min = salaryMap;
                }
                if (salaryMap.get("value").toString().indexOf("以上") > -1) {
                    max = salaryMap;
                }
            }
            if (screenSalary.equals(min.get("code"))) {
                map.put("salaryMin", "0");
                map.put("salaryMax", min.get("value").toString().substring(0, min.get("value").toString().indexOf("k")));
            } else if (screenSalary.equals(max.get("code"))) {
                map.put("salaryMin", max.get("value").toString().substring(0, max.get("value").toString().indexOf("k")));
            } else {
                dicMap.put("item", "screenSalary");
                dicMap.put("code", screenSalary);
                String value = dictionaryService.getValue(dicMap);
                map.put("salaryMin", value.substring(0, value.indexOf("-")));
                map.put("salaryMax", value.substring(value.indexOf("-") + 1, value.indexOf("k")));
            }
        }
        if (null != recruitingNum && !"".equals(recruitingNum)) {
            dicMap.put("item", "recruitingNum");
            List<Map<String, Object>> dicList = dictionaryService.getKeyValue(dicMap);
            Map<String, Object> max = null;
            for (Map<String, Object> numMap : dicList) {
                if (numMap.get("value").toString().indexOf("以上") > -1) {
                    max = numMap;
                }
            }
            if (recruitingNum.equals(max.get("code"))) {
                map.put("recruitingNumMin", max.get("value").toString().substring(0, max.get("value").toString().indexOf("人")));
            } else {
                dicMap.put("item", "recruitingNum");
                dicMap.put("code", recruitingNum);
                String value = dictionaryService.getValue(dicMap);
                map.put("recruitingNumMin", value.substring(0, value.indexOf("-")));
                map.put("recruitingNumMax", value.substring(value.indexOf("-") + 1, value.indexOf("人")));
            }
        }
        if (null != memberId && !"".equals(memberId)) {
            Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("resumeId", resume.getId());
            JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
            long kindsId = jobObjective.getData1();
            if (kindsId > 0) {
                map.put("kindsId", kindsId);
            }
        }

        map.put("companySize", companySize);
        map.put("jobyear", jobyear);
        map.put("qualification", qualification);
        map.put("jobType", jobType);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        map.put("zoneName", zoneName);

        List<Map<String, Object>> list = positionService.screenPosition(map);
        if (list.size() > 0) {
            for (Map<String, Object> map_ : list) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("item", "companySize");
                paramMap.put("code", companySize);

                Dictionary dict = dictionaryService.getNameByCode(paramMap);
                if (dict != null) {
                    map_.put("companyValue", dict.getValue());
                }


                if (map_.get("distance") != null && !"".equals(String.valueOf(map_.get("distance")))) {
                    BigDecimal b = new BigDecimal(map_.get("distance") + "");
                    double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    map_.put("distance", f1);
                }
            }

            resultMap = getResultMap("1", "获取职位列表成功", list);
        } else {
            resultMap = getResultMap("0", "暂无符合条件的职位");
        }
        outJson(resultMap);
    }


}
