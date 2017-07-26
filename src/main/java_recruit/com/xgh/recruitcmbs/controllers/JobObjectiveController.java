package com.xgh.recruitcmbs.controllers;

/**
 * Created by CQ on 2017/3/15.
 */

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IZoneDao;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.services.ICompanyInfoServices;
import com.xgh.recruitcmbs.services.IDictionaryService;
import com.xgh.recruitcmbs.services.IJobObjectiveService;
import com.xgh.recruitcmbs.services.IResumeService;
import com.xgh.recruitcmbs.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/jobObjective/")
public class JobObjectiveController extends BaseController {


    @Autowired
    protected IJobObjectiveService jobObjectiveService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    @Autowired
    protected IZoneDao zoneDao;

    @Autowired
    protected IResumeService resumeService;

    /**
     * 企业首页搜索
     */
    @RequestMapping(value = "companyResearch")
    public void companyResearch() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String jobName = request.getParameter("jobName");
        String jobId = request.getParameter("jobId");
        String zoneName_ = request.getParameter("zoneName");
        String companyId = request.getParameter("companyId");//企业id
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");

        Map<String, Object> map = new HashMap<String, Object>();

        //根据公司id查询出该公司所在区域

        CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(companyId));
        long companyZoneId = companyInfo.getZoneId();

        Zone zone_ = zoneDao.get(companyZoneId);
        String pid_ = zone_.getPid();
        zone_ = zoneDao.get(Long.parseLong(pid_));

        map.put("jobName", jobName);
        map.put("jobId", jobId);

        if (zoneName_ != null && !"".equals(zoneName_)) {
            map.put("zoneName", zoneName_ + "市");
        } else {
            map.put("zoneId", companyZoneId);//郑州市的id
        }

        long startTime = System.currentTimeMillis();
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));

        List<Map<String, Object>> jobList = jobObjectiveService.companyResearch(map);
        long endTime = System.currentTimeMillis();

        long finishTime = endTime - startTime;
        System.out.println(".........." + finishTime);

        if (jobList != null && jobList.size() > 0) {

            for (Map<String, Object> map_ : jobList) {
                /**
                 * 区域查询
                 */
                String zoneId = map_.get("zoneId") + "";
                Zone zone = zoneDao.get(Long.parseLong(zoneId));
                String zoneAreaName = zone.getName();
                long pid = Long.parseLong(zone.getPid());
                zone = zoneDao.get(pid);
                String zoneName = zone.getName();

                map_.put("zoneName", zoneName + zoneAreaName);
                map_.remove("zoneId");
                /**
                 *根据code查询字典表对应value值
                 */
                String edu = map_.get("edu") + "";
                Map<String, Object> mapDict = new HashMap<String, Object>();
                mapDict.put("item", ConstantUtil.FileUploadCode.Edu);
                mapDict.put("code", edu);
                Dictionary dictionary = dictionaryService.getNameByCode(mapDict);
                if (dictionary != null) {
                    map_.put("edu", dictionary.getValue());
                }
            }
            resultMap = getResultMap("1", "查询列表显示成功!", jobList);
        } else {
            resultMap = getResultMap("0", "查询列表显示暂无数据!");
        }

        outJson(resultMap);

    }

    /**
     * 添加求职意向
     *
     * @param jobObjective
     */
    @RequestMapping(value = "updateObjective")
    public void updateObjective(@ModelAttribute JobObjective jobObjective) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        map.put("zoneName", jobObjective.getZoneName());
        Zone zone = zoneDao.getIdByName(map);

        jobObjective.setZoneId(zone.getId());
        jobObjective.setUpdateDate(date);
        JobObjective jobObjective1 = jobObjectiveService.get(jobObjective.getId());
        jobObjective.setResumeId(jobObjective1.getResumeId());
        jobObjective.setCreateDate(jobObjective1.getCreateDate());
        jobObjective.setStatus(jobObjective1.getStatus());
        int flag = jobObjectiveService.update(jobObjective);
        if (flag > 0) {

/*            Resume resume = resumeService.get(jobObjective.getResumeId());
            String integrity = resume.getIntegrity();//简历完整度

            resume.setIntegrity(String.valueOf(Integer.parseInt(integrity) + 20));
            resume.setUpdateDate(new Date());
            resumeService.update(resume);*/


            resultMap = getResultMap("1", "保存成功！", jobObjective1.getId());
        } else {
            resultMap = getResultMap("0", "保存失败，请重试！");
        }
        outJson(resultMap);
    }

    /**
     * 获取个人求职意向
     */
    @RequestMapping(value = "getJobObjective")
    public void getJobObjective() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));
        map.put("resumeId", resume.getId());
        JobObjective jobObjective = jobObjectiveService.getByResumeId(map);

        Map<String, Object> objectiveMap = new HashMap<String, Object>();
        objectiveMap.put("id", jobObjective.getId());
        objectiveMap.put("zoneName", jobObjective.getZoneName());
        objectiveMap.put("jobName", jobObjective.getJobName());

        Map<String, Object> dicMap = new HashMap<String, Object>();

        dicMap.put("item", "salary");
        dicMap.put("code", jobObjective.getSalaryMin());
        String salaryMin = dictionaryService.getValue(dicMap);
        dicMap.put("code", jobObjective.getSalaryMax());
        String salaryMax = dictionaryService.getValue(dicMap);
        objectiveMap.put("salary", salaryMin + "-" + salaryMax);
        objectiveMap.put("salaryMin", jobObjective.getSalaryMin());
        objectiveMap.put("salaryMax", jobObjective.getSalaryMax());
        objectiveMap.put("data1", jobObjective.getData1());

        if (jobObjective.getJobType() == 1) {
            objectiveMap.put("jobType", "全职");
        } else {
            objectiveMap.put("jobType", "兼职");
        }

        dicMap.put("item", "serviceStatus");
        dicMap.put("code", jobObjective.getServiceStatus());
        objectiveMap.put("serviceStatus", dictionaryService.getValue(dicMap));

        if (null != jobObjective) {
            resultMap = getResultMap("1", "获取求职意向成功！", objectiveMap);
        } else {
            resultMap = getResultMap("0", "获取求职意向失败！");
        }
        outJson(resultMap);
    }


}
