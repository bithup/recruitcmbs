package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.*;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.recruitcmbs.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BSX on 2017/3/13.
 */
@Service("resumeService")
public class ResumeServiceImpl implements IResumeService {

    @Autowired
    protected IResumeDao resumeDao;

    @Autowired
    protected IEduExperienceDao eduExperienceDao;

    @Autowired
    protected IWorkExperienceDao workExperienceDao;

    @Autowired
    protected IMemberUserDao memberUserDao;

    @Autowired
    protected IJobObjectiveDao jobObjectiveDao;

    @Autowired
    protected IStoreTalentsDao storeTalentsDao;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IResumeDeliveryDao resumeDeliveryDao;

    public Map<String, Object> get(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();

        String resumeId_ = request.getParameter("resumeId");

        //简历id
        long resumeId = Long.parseLong(request.getParameter("resumeId"));
        //企业id
        String companyId = request.getParameter("companyId");
        //简历投递id
        String id = request.getParameter("id");
        //如果从收件箱进来将查看状态改为已查看
        if (null != id && !"".equals(id)) {
            ResumeDelivery resumeDelivery = resumeDeliveryDao.get(Long.parseLong(id));
            if (null != resumeDelivery) {
                if (resumeDelivery.getIsView() == 0) {
                    resumeDelivery.setIsView(1);
                    resumeDelivery.setUpdateDate(new Date());
                    resumeDeliveryDao.update(resumeDelivery);
                }
            }
        }
        //获取求职者基本信息并对需要的地方进行格式化处理
        map.put("resumeId", resumeId);
        Resume resume = resumeDao.get(resumeId);
        MemberUser memberUser = memberUserDao.get(resume.getMemberId());
        Map<String, Object> memberMap = new HashMap<String, Object>();
        memberMap.put("id", memberUser.getId());
        memberMap.put("realName", memberUser.getRealName());
        if (memberUser.getSex() == 1) {
            memberMap.put("sex", "男");
        } else if (memberUser.getSex() == 2) {
            memberMap.put("sex", "女");
        }
        //将生日转换为年龄
        if (null != memberUser.getBirthday()) {
            memberMap.put("age", DateUtil.getAge(memberUser.getBirthday()) + "岁");
        } else {
            memberMap.put("age", "未知");
        }
        //求职者学历
        if (null != memberUser.getEducation() && memberUser.getEducation() > 0) {
            dicMap.put("item", "edu");
            dicMap.put("code", memberUser.getEducation());
            memberMap.put("education", dictionaryService.getValue(dicMap));
        } else {
            memberMap.put("education", "未知");
        }
        //求职者工作年限
        if (null != memberUser.getWorkYear() && !"".equals(memberUser.getWorkYear())) {
            dicMap.put("item", "workyear");
            dicMap.put("code", memberUser.getWorkYear());
            memberMap.put("workyear", dictionaryService.getValue(dicMap) + "工作经验");
        } else {
            memberMap.put("workyear", "未知");
        }
        memberMap.put("headRealPath", memberUser.getHeadRealPath());
        memberMap.put("address", memberUser.getZoneName());
        memberMap.put("telephone", memberUser.getTelephone());
        memberMap.put("email", memberUser.getEmail());

        //求职者教育经历
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
        List<EduExperience> eduList = eduExperienceDao.getByResumeId(map);
        List<Map<String, Object>> eduExList = new ArrayList<Map<String, Object>>();
        if (eduList.size() > 0) {
            for (EduExperience eduExperience : eduList) {
                Map<String, Object> eduMap = new HashMap<String, Object>();
                eduMap.put("eduId", eduExperience.getId());
                eduMap.put("beginAndEndDate", sdf.format(eduExperience.getEduBeginDate()) + "-" + sdf.format(eduExperience.getEduEndDate()));
                eduMap.put("schoolName", eduExperience.getSchoolName());
                dicMap.put("item", "edu");
                dicMap.put("code", eduExperience.getEduBackground());
                eduMap.put("eduBackground", dictionaryService.getValue(dicMap));
                eduMap.put("major", eduExperience.getMajor());
                eduExList.add(eduMap);
            }
        }

        //求职者工作经历
        List<WorkExperience> workList = workExperienceDao.getByResumeId(map);
        List<Map<String, Object>> workExList = new ArrayList<Map<String, Object>>();
        if (workList.size() > 0) {
            for (WorkExperience workExperience : workList) {
                Map<String, Object> workMap = new HashMap<String, Object>();
                workMap.put("workExperienceId", workExperience.getId());
                workMap.put("companyName", workExperience.getCompanyName());
                workMap.put("jobName", workExperience.getJobName());
                dicMap.put("item", "salary");
                dicMap.put("code", workExperience.getMonthlyPay());
                workMap.put("monthlyPay", dictionaryService.getValue(dicMap));
                workMap.put("beginAndEndDate", sdf.format(workExperience.getStartDate()) + "-" + sdf.format(workExperience.getEndDate()));
                workMap.put("jobIntro", workExperience.getJobIntro());
                workExList.add(workMap);
            }
        }

        //求职者求职意向
        JobObjective jobObjective = jobObjectiveDao.getByResumeId(map);
        Map<String, Object> objectiveMap = new HashMap<String, Object>();
        if (null != jobObjective) {
            objectiveMap.put("jobName", jobObjective.getJobName());
            dicMap.put("item", "salary");
            dicMap.put("code", jobObjective.getSalaryMin());
            String salaryMin = dictionaryService.getValue(dicMap);
            dicMap.put("code", jobObjective.getSalaryMax());
            String salaryMax = dictionaryService.getValue(dicMap);
            objectiveMap.put("salary", salaryMin + "-" + salaryMax);
            if (jobObjective.getJobType() == 1) {
                objectiveMap.put("jobType", "全职");
            } else if (jobObjective.getJobType() == 2) {
                objectiveMap.put("jobType", "兼职");
            }
            dicMap.put("item", "serviceStatus");
            dicMap.put("code", jobObjective.getServiceStatus());
            objectiveMap.put("serviceStatus", dictionaryService.getValue(dicMap));
            objectiveMap.put("industryName", jobObjective.getIndustryName());
            objectiveMap.put("zoneName", jobObjective.getZoneName());
        }
        //企业是否已经收藏此求职者
        if (null == companyId || "".equals(companyId)) {
            resultMap.put("isStoreTalents", "0");
        } else {
            map.put("companyId", companyId);
            map.put("memberId", memberUser.getId());
            List<StoreTalents> storeList = storeTalentsDao.isExist(map);
            if (storeList.size() > 0) {
                StoreTalents storeTalents = storeList.get(0);
                if (storeTalents.getStatus() == 1) {
                    resultMap.put("isStoreTalents", "1");
                    resultMap.put("storeTalentId", storeTalents.getId());
                } else {
                    resultMap.put("isStoreTalents", "0");
                    resultMap.put("storeTalentId", storeTalents.getId());
                }
            } else {

                resultMap.put("isStoreTalents", "0");
            }
        }
        resultMap.put("resume", resume);
        resultMap.put("memberUser", memberMap);
        resultMap.put("eduExperience", eduExList);
        resultMap.put("workExperience", workExList);
        resultMap.put("jobObjective", objectiveMap);
        resultMap.put("selfEvaluation", memberUser.getSelfEvaluation());
        return resultMap;
    }

    public Resume get(long id) {
        return resumeDao.get(id);
    }

    public int insert(Resume resume) {
        return resumeDao.insert(resume);
    }

    public int update(Resume resume) {
        return resumeDao.update(resume);
    }

    public int save(HttpServletRequest request, Resume resume) {
        return 0;
    }

    public Resume getByMemberId(long memberId) {
        return resumeDao.getByMemberId(memberId);
    }
}
