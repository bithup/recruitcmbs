package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.JobObjective;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IJobObjectiveService {


    /**
     * 企业搜索职位
     *
     * @param
     * @return
     */
    public List<Map<String, Object>> companyResearch(Map<String,Object> map);

    public JobObjective getByResumeId(Map<String,Object> map);

    public int update(JobObjective jobObjective);

    JobObjective get(long id);


}
