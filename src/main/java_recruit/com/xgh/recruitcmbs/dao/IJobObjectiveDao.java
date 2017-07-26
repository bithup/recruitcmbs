package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.JobObjective;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IJobObjectiveDao {


    /**
     * 企业搜索职位
     *
     * @param
     * @return
     */
    public List<Map<String, Object>> companyResearch(Map<String, Object> map);



    public JobObjective getByResumeId(Map<String,Object> map);


    int add(JobObjective jobObjective);

    int update(JobObjective jobObjective);

    JobObjective get(long id);
}
