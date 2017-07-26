package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.services.*;
import com.xgh.recruitcmbs.util.ConstantUtil;
import com.xgh.recruitcmbs.util.DateThreadUtil;
import com.xgh.recruitcmbs.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BSX on 2017/3/13.
 */
@Controller
@Scope("prototype")
@RequestMapping("recruitcmbs/resume/")
public class ResumeController extends BaseController {


    Logger logger = Logger.getLogger(ResumeController.class);

    @Autowired
    protected IResumeService resumeService;

    @Autowired
    protected IWorkExperienceService workExperienceService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IEduExperienceService eduExperienceService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IJobObjectiveService jobObjectiveService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 企业获取个人简历详情
     */
    @RequestMapping(value = "getResume")
    public void getResume() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> resume = resumeService.get(request);
        if (resume != null) {
            resume.put("serverUrl", ConstantUtil.SERVER_IP);
            resultMap = getResultMap("1", "获取简历详情成功！", resume);
        } else {
            resultMap = getResultMap("0", "获取简历详情失败！");
        }
        outJson(resultMap);
    }

    /**
     * 求职者简历添加个人工作经验
     *
     * @param workExperience
     */
    @RequestMapping(value = "resumeAddWorkExperience")
    public void resumeAddWorkExperience(@ModelAttribute WorkExperience workExperience) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (workExperience.getId() > 0) {

            WorkExperience workExperience_ = workExperienceService.get(workExperience.getId());

            workExperience_.setUpdateDate(new Date());
            workExperience_.setStatus(1);
            workExperience_.setCompanyName(workExperience.getCompanyName());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String startDate = simpleDateFormat.format(workExperience.getStartDate());
            String endDate = simpleDateFormat.format(workExperience.getEndDate());
            try {
                Date startDate_ = simpleDateFormat.parse(startDate);
                Date endDate_ = simpleDateFormat.parse(endDate);
                workExperience_.setStartDate(startDate_);
                workExperience_.setEndDate(endDate_);
            } catch (Exception e) {

            }

            workExperience_.setMonthlyPay(workExperience.getMonthlyPay());
            workExperience_.setJobIntro(workExperience.getJobIntro());
            workExperience_.setJobName(workExperience.getJobName());

            Kinds kinds = kindsService.get(workExperience.getJobKindsId());

            workExperience_.setJobKindsName(kinds.getName());
            int flag = workExperienceService.update(workExperience_);
            if (flag > 0) {
                resultMap = getResultMap("1", "求职者修改工作经历成功!");
            } else {
                resultMap = getResultMap("-1", "求职者修改工作经历失败!");
            }
        } else {

            workExperience.setStatus(1);
            workExperience.setCreateDate(new Date());
            workExperience.setUpdateDate(new Date());
            workExperience.setIndustryName("0");
            workExperience.setData3("0");
            workExperience.setData4("0");
            workExperience.setData5("0");
            workExperience.setData8(0d);

            Kinds kinds = kindsService.get(workExperience.getJobKindsId());
            workExperience.setJobKindsName(kinds.getName());

            int flag = workExperienceService.insert(workExperience);
            if (flag > 0) {

/*
                Resume resume = resumeService.get(workExperience.getResumeId());

                resume.setIntegrity(String.valueOf(Integer.parseInt(resume.getIntegrity()) + 10));
                resume.setUpdateDate(new Date());
                resumeService.update(resume);
*/

                resultMap = getResultMap("1", "求职者添加工作经历成功!", workExperience.getId());
            } else {
                resultMap = getResultMap("-1", "求职者添加工作经历失败!");
            }
        }

        outJson(resultMap);

    }

    /**
     * 获取求职者简历工作经验详情
     */
    @RequestMapping(value = "getResumeWorkExperience")
    public void getResumeWorkExperience() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String id = request.getParameter("id");

        if (id != null && !"".equals(id)) {

            WorkExperience workExperience = workExperienceService.get(Long.parseLong(id));
            if (workExperience != null) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", workExperience.getId());
                map.put("companyName", workExperience.getCompanyName());
                map.put("jobName", workExperience.getJobName());
                map.put("jobIntro", workExperience.getJobIntro());
                map.put("monthlyPay", workExperience.getMonthlyPay());
                map.put("resumeId", workExperience.getResumeId());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = simpleDateFormat.format(workExperience.getStartDate());
                String endDate = simpleDateFormat.format(workExperience.getEndDate());

                map.put("startDate", startDate);
                map.put("endDate", endDate);

                map.put("jobKindsId", workExperience.getJobKindsId());
                map.put("jobKindsName", workExperience.getJobKindsName());

                resultMap = getResultMap("1", "工作经验详情显示成功!", map);
            } else {
                resultMap = getResultMap("-1", "工作经验详情显示失败!");
            }
        }

        outJson(resultMap);
    }

    /**
     * 求职者删除工作经验
     */
    @RequestMapping(value = "deleteWorkExperience")
    public void deleteWorkExperience() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String workExperienceId = request.getParameter("id");
        if (workExperienceId != null && !"".equals(workExperienceId)) {

            WorkExperience workExperience = workExperienceService.get(Long.parseLong(workExperienceId));
            if (workExperience != null) {

                workExperience.setUpdateDate(new Date());
                workExperience.setStatus(-1);
                int flag = workExperienceService.update(workExperience);
                if (flag > 0) {

                    long resumeId = workExperience.getResumeId();
                    Resume resume = resumeService.get(resumeId);

                    String integrity = resume.getIntegrity();

                    resume.setIntegrity(String.valueOf(Integer.parseInt(integrity) - 10));
                    resume.setUpdateDate(new Date());
                    resumeService.update(resume);


                    resultMap = getResultMap("1", "工作经历删除成功!");
                } else {
                    resultMap = getResultMap("-1", "工作经历删除失败!");
                }

            }
        }
        outJson(resultMap);
    }

    /**
     * 求职者教育背景添加
     *
     * @param eduExperience
     */
    @RequestMapping(value = "addEduBackGround")
    public void addEduBackGround(@ModelAttribute EduExperience eduExperience) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (eduExperience.getId() > 0) {

            EduExperience eduExperience_ = eduExperienceService.get(eduExperience.getId());

            eduExperience_.setUpdateDate(new Date());
            eduExperience_.setSchoolName(eduExperience.getSchoolName());
            eduExperience_.setMajor(eduExperience.getMajor());
            eduExperience_.setEduBackground(eduExperience.getEduBackground());
            eduExperience_.setEduBeginDate(eduExperience.getEduBeginDate());
            eduExperience_.setEduEndDate(eduExperience.getEduEndDate());

            int flag = eduExperienceService.update(eduExperience_);
            if (flag > 0) {
                resultMap = getResultMap("1", "求职者修改教育背景成功!");
            } else {
                resultMap = getResultMap("-1", "求职者修改教育背景成功!");
            }

        } else {

            eduExperience.setCreateDate(new Date());
            eduExperience.setUpdateDate(new Date());
            eduExperience.setStatus(1);

            int flag = eduExperienceService.add(eduExperience);
            if (flag > 0) {
/*
                long resumeId = eduExperience.getResumeId();

                Resume resume = resumeService.get(resumeId);
                if (resume != null) {
                    String integrity = resume.getIntegrity();
                    if (integrity != null && !"".equals(integrity)) {
                        //教育经历占简历完整度百分之10
                        resume.setIntegrity(String.valueOf(Integer.parseInt(integrity) + 5));
                        resume.setUpdateDate(new Date());
                        resumeService.update(resume);
                    }
                }*/

                resultMap = getResultMap("1", "求职者添加工作经历成功!", eduExperience.getId());
            } else {
                resultMap = getResultMap("-1", "求职者添加工作经历失败!");
            }
        }

        outJson(resultMap);
    }

    /**
     * 查询教育经历id
     */
    @RequestMapping(value = "getEduExperience")
    public void getEduExperience() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String id = request.getParameter("id");

        if (id != null && !"".equals(id)) {

            EduExperience eduExperience = eduExperienceService.get(Long.parseLong(id));

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", eduExperience.getId());
            map.put("schoolName", eduExperience.getSchoolName());
            map.put("major", eduExperience.getMajor());
            try {
                String beginDate = DateUtil.formatDate(eduExperience.getEduBeginDate());//2013-09-23
                String endDate = DateUtil.formatDate(eduExperience.getEduEndDate());
/*              Date beginDate_ = java.sql.Date.valueOf(beginDate);*/
  /*            Date endDate_ = java.sql.Date.valueOf(endDate);*/
                map.put("eduBeginDate", beginDate);
                map.put("eduEndDate", endDate);
            } catch (Exception e) {
                logger.info("获取求职者教育背景抛出异常.......");
            }
            map.put("resumeId", eduExperience.getResumeId());

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("code", eduExperience.getEduBackground());
            paramMap.put("item", "edu");

            Dictionary dictionary = dictionaryService.getNameByCode(paramMap);
            if (dictionary != null) {
                map.put("eduBackground", dictionary.getCode());
                map.put("eduBackgroundValue", dictionary.getValue());
            }
            resultMap = getResultMap("1", "查询教育经历成功!", map);
        } else {
            resultMap = getResultMap("-1", "查询教育经历成功!");
        }

        outJson(resultMap);
    }

    /**
     * 删除求职者教育背景
     */
    @RequestMapping(value = "deleteEduExperience")
    public void deleteEduExperience() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String id = request.getParameter("id");

        EduExperience eduExperience = eduExperienceService.get(Long.parseLong(id));
        if (eduExperience != null) {
            eduExperience.setStatus(-1);
            eduExperience.setUpdateDate(new Date());
        }

        int flag = eduExperienceService.update(eduExperience);
        if (flag > 0) {

            long resumeId = eduExperience.getResumeId();
            Resume resume = resumeService.get(resumeId);
            String integrity = resume.getIntegrity();
            resume.setIntegrity(String.valueOf(Integer.parseInt(integrity) - 5));
            resume.setUpdateDate(new Date());

            resumeService.update(resume);

            resultMap = getResultMap("1", "求职者教育背景删除成功!");
        } else {
            resultMap = getResultMap("-1", "求职者教育背景删除失败!");
        }

        outJson(resultMap);
    }

    /**
     * 简历预览
     *
     * @return
     */
    @RequestMapping(value = "resumeInit")
    public ModelAndView resumeInit() {

        String memberId = request.getParameter("memberId");
        String resumeId = request.getParameter("resumeId");

        /**
         * 个人基本信息展示
         */
        MemberUser memberUser = memberUserService.get(Long.parseLong(memberId));

        Map<String, Object> map = new HashMap<String, Object>();

        long zoneId = memberUser.getZoneId();
        if (zoneId != 0) {
            Zone zone = zoneService.get(zoneId);
            String areaName = zone.getName();
            zone = zoneService.get(Long.parseLong(zone.getPid()));
            map.put("zoneName", zone.getName() + areaName);
        }


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("item", "edu");
        paramMap.put("code", memberUser.getEducation());
        String education = dictionaryService.getValue(paramMap);

        map.put("education", education);
        try {
            String birthday = DateUtil.formatDate(memberUser.getBirthday());
            map.put("birthday", birthday);
        } catch (Exception e) {
            logger.info("抛出异常..........");
        }
        map.put("realName", memberUser.getRealName());
        map.put("workYear", memberUser.getWorkYear());
        map.put("telephone", memberUser.getTelephone());
        map.put("email", memberUser.getEmail());
        map.put("headPath", ConstantUtil.SERVER_URL + memberUser.getHeadPath());
        map.put("selfEvaluation", memberUser.getSelfEvaluation());//自我评价

        if (memberUser.getSex() == 1) {
            map.put("sex", "男");
        } else
            map.put("sex", "女");

        paramMap.clear();
        paramMap.put("resumeId", resumeId);

        ModelAndView view = new ModelAndView();
        if (memberUser != null) {
            view.setViewName("recruit/resume/resume");
            view.addObject("memberUser", map);

            /**
             * 个人工作经历
             */
            List<WorkExperience> workExperienceList = workExperienceService.getByResumeId(paramMap);
            if (workExperienceList != null && workExperienceList.size() > 0) {

                for (WorkExperience workExperience : workExperienceList) {
                    try {
                        String startDate = DateUtil.formatDate(workExperience.getStartDate());
                        String endDate = DateUtil.formatDate(workExperience.getEndDate());
                        workExperience.setStartDate(java.sql.Date.valueOf(startDate));
                        workExperience.setEndDate(java.sql.Date.valueOf(endDate));
                    } catch (Exception e) {
                    }
                }
                view.addObject("workExperienceList", workExperienceList);
            }
            /**
             * 个人教育经历
             */
            List<EduExperience> eduExperienceList = eduExperienceService.getByResumeId(paramMap);
            if (eduExperienceList != null && eduExperienceList.size() > 0) {

                for (EduExperience eduExperience : eduExperienceList) {
                    try {
                        paramMap.put("item", "edu");
                        paramMap.put("code", eduExperience.getEduBackground());
                        Dictionary dictionary = dictionaryService.getNameByCode(paramMap);

                        eduExperience.setData3(dictionary.getValue());
                        String startDate = DateUtil.formatDate(eduExperience.getEduBeginDate());
                        String endDate = DateUtil.formatDate(eduExperience.getEduEndDate());
                        eduExperience.setEduBeginDate(java.sql.Date.valueOf(startDate));
                        eduExperience.setEduEndDate(java.sql.Date.valueOf(endDate));
                    } catch (Exception e) {
                    }
                }
                view.addObject("eduExperienceList", eduExperienceList);
            }

            paramMap.clear();
            paramMap.put("resumeId", resumeId);

            /**
             * 个人期望工作
             */
            JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
            if (jobObjective != null) {
                Map<String, Object> jobMap = new HashMap<String, Object>();

                if (jobObjective.getJobType() == 1) {
                    jobMap.put("jobType", "全职");
                } else {
                    jobMap.put("jobType", "兼职");
                }

                jobMap.put("zoneName", jobObjective.getZoneName());

                jobMap.put("item", "salary");
                jobMap.put("code", jobObjective.getSalaryMin());
                Dictionary dictionary = dictionaryService.getNameByCode(jobMap);

                jobMap.put("salaryMin", dictionary.getValue());

                jobMap.put("code", jobObjective.getSalaryMax());
                dictionary = dictionaryService.getNameByCode(jobMap);
                jobMap.put("salaryMax", dictionary.getValue());

                jobMap.put("item", "serviceStatus");
                jobMap.put("code", jobObjective.getServiceStatus());
                dictionary = dictionaryService.getNameByCode(jobMap);

                jobMap.put("serviceStatus", dictionary.getValue());
                jobMap.put("jobName", jobObjective.getJobName());

                view.addObject("jobObjective", jobMap);
            }
        }
        return view;

    }

    /**
     * 修改简历隐藏状态
     */
    @RequestMapping(value = "modifyResumeHideStatus")
    public void modifyResumeHideStatus() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        String msg;

        Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));

        if (resume != null) {

            if (resume.getIsScreen() == 1) {
                resume.setUpdateDate(new Date());
                resume.setIsScreen(0);
                msg = "简历已设置为不公开状态!";
            } else {
                resume.setUpdateDate(new Date());
                resume.setIsScreen(1);
                msg = "简历已设置为公开状态!";
            }

            int flag = resumeService.update(resume);
            if (flag > 0) {
                resultMap = getResultMap("1", msg);
            } else {
                resultMap = getResultMap("-1", msg);
            }
        }

        outJson(resultMap);
    }


}
