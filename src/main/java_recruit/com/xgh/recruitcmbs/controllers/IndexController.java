package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IZoneDao;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.services.*;
import com.xgh.recruitcmbs.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/24.
 */
@Controller
@Scope("prototype")
@RequestMapping("/recruitcmbs/index/")
public class IndexController extends BaseController {

    @Autowired
    protected IPositionService positionService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IGreatDemandService greatDemandService;

    @Autowired
    protected ISubjectService subjectService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IIndexAdvertService indexAdvertService;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    @Autowired
    protected IResumeService resumeService;

    @Autowired
    protected IJobObjectiveService jobObjectiveService;

    @Autowired
    protected IZoneDao zoneDao;


    /**
     * 求职版首页职位搜索
     */
    @RequestMapping(value = "getIndexSearch")
    public void getIndexSearch() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();

        String condition = request.getParameter("condition");
        String zoneName = request.getParameter("zoneName");
        String memberId = request.getParameter("memberId");
        String gpsLongitude = request.getParameter("gpsLongitude");
        String gpsLatitude = request.getParameter("gpsLatitude");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");

        map.put("condition", condition);
        if (null != memberId && !"".equals(memberId)) {

            String A="";

            Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("resumeId", resume.getId());
            JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
            String zoneName1 = jobObjective.getZoneName();
            Zone zone = zoneDao.getZoneByName(zoneName1);
            if (null != zone) {
                map.put("zoneId", zone.getId());
            }
        } else {
            Zone zone = zoneDao.getZoneByName(zoneName);
            map.put("zoneId", zone.getId());
        }
        map.put("gpsLongitude", gpsLongitude);
        map.put("gpsLatitude", gpsLatitude);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = positionService.getIndexSearch(map);
        for (Map<String, Object> positionMap : list) {
            dicMap.put("item", "companySize");
            dicMap.put("code", positionMap.get("companySize"));
            positionMap.put("companySize", dictionaryService.getValue(dicMap));
            positionMap.put("salary", positionMap.get("salaryMin") + "-" + positionMap.get("salaryMax"));
            positionMap.put("address", positionMap.get("address"));
            positionMap.put("distance", positionMap.get("distance") + "km");
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "查询成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无符合条件的职位或企业！");
        }
        outJson(resultMap);
    }


    /**
     * 热门企业
     */
    @RequestMapping(value = "getHotCompanies")
    public void getHotCompanies() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = greatDemandService.getHotCompanies();
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取热门企业成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 热门职位
     */
    @RequestMapping(value = "getHotPositions")
    public void getHotPositions() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = greatDemandService.getHotPositions();
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取热门职位成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 首页轮播图
     */
    @RequestMapping(value = "getSubjects")
    public void getSubjects() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Subject> list = subjectService.getSubjects();
        for (Subject subject : list) {
            subject.setRelativePath(ConstantUtil.SERVER_URL + subject.getMobileFilePath());
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取轮播图成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据");
        }
        outJson(resultMap);
    }


    /**
     * 首页分类
     */
    @RequestMapping(value = "getIndexKinds")
    public void getIndexKinds() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Kinds> list = kindsService.getIndexKinds();
        if (list.size() > 0) {
            resultMap = getResultMap("1", "查询一级分类成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 首页企业列表
     */
    @RequestMapping(value = "getIndexCompanies")
    public void getIndexCompanies() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();

        String zoneName = request.getParameter("zoneName");
        String memberId = request.getParameter("memberId");
        int page = Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));

        map.put("zoneName", zoneName);
        Zone zone = zoneDao.getIdByName(map);

        if (null != memberId && !"".equals(memberId)) {
            Resume resume = resumeService.getByMemberId(Long.parseLong(memberId));
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("resumeId", resume.getId());
            JobObjective jobObjective = jobObjectiveService.getByResumeId(paramMap);
            long zoneId = jobObjective.getZoneId();

            if ("".equals(zoneId) || zoneId == 0) {
                map.put("zoneName", zone.getId());
            } else {
                map.put("zoneId", zoneId);
            }

        } else {
            map.put("zoneName", zone.getId());
        }

        List<Map<String, Object>> advertsList = indexAdvertService.getIndexAdverts(map);

        List<Long> ids = new ArrayList<Long>();

        if (advertsList != null && advertsList.size() > 0) {
            for (Map<String, Object> advertMap : advertsList) {
                long id = Long.parseLong(advertMap.get("companyId") + "");
                ids.add(id);//查询出首页列表的企业id
            }
        }

        map.put("page", page);
        map.put("pagesize", pagesize);
        if (ids != null && ids.size() > 0) {
            map.put("ids", ids);
        } else {
            map.put("ids", "");
        }

        List<CompanyInfo> companyInfosList = companyInfoServices.getListPage(map);//查询出来不包括热门企业的列表
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        //进入首页把热门企业放在最前边
        if (page == 1) {
            for (Map<String, Object> advertMap : advertsList) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("companyId", advertMap.get("companyId") + "");
                paramMap.put("positionStatus", "1");

                List<Position> positionList = positionService.getPositionList(paramMap);
                if (positionList.size() > 0) {
                    if (null != advertMap.get("companySize") && Integer.parseInt(advertMap.get("companySize") + "") >= 0) {
                        dicMap.put("item", "companySize");
                        dicMap.put("code", advertMap.get("companySize"));
                        advertMap.put("companySize", dictionaryService.getValue(dicMap));
                    } else {
                        advertMap.put("companySize", "");
                    }
                    if (null != advertMap.get("zoneName") && !"".equals(advertMap.get("zoneName")) && null != advertMap.get("address") && !"".equals(advertMap.get("address"))) {
                        advertMap.put("address", advertMap.get("zoneName").toString() + advertMap.get("address"));
                        advertMap.remove("zoneName");
                    } else {
                        advertMap.put("address", "");
                        advertMap.remove("zoneName");
                    }
                    advertMap.put("position", positionList.get(0).getJobName());
                    advertMap.put("positionNum", positionList.size());
                    list.add(advertMap);
                }
            }
        }
        for (CompanyInfo companyInfo : companyInfosList) {
            Map<String, Object> companyMap = new HashMap<String, Object>();
            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put("companyId", companyInfo.getId());
            paramMap.put("positionStatus", "1");

            List<Position> positionList = positionService.getPositionList(paramMap);
            if (positionList.size() > 0) {
                companyMap.put("companyId", companyInfo.getId());
                if (null != companyInfo.getZoneName() && !"".equals(companyInfo.getZoneName()) && null != companyInfo.getAddress() && !"".equals(companyInfo.getAddress())) {
                    companyMap.put("address", companyInfo.getZoneName() + companyInfo.getAddress());
                } else {
                    companyMap.put("address", "");
                }
                companyMap.put("companyName", companyInfo.getCompanyName());
                if (null != companyInfo.getCompanySize() && !"".equals(companyInfo.getCompanySize())) {
                    dicMap.put("item", "companySize");
                    dicMap.put("code", companyInfo.getCompanySize());
                    companyMap.put("companySize", dictionaryService.getValue(dicMap));
                } else {
                    companyMap.put("companySize", "");
                }
                companyMap.put("logoRealPath", companyInfo.getLogoRealPath());
                companyMap.put("industryName", companyInfo.getIndustryName());
                companyMap.put("position", positionList.get(0).getJobName());
                companyMap.put("positionNum", positionList.size());
                list.add(companyMap);
            }
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取企业列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


}
