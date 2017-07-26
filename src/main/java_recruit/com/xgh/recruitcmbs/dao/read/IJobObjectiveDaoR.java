package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.JobObjective;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IJobObjectiveDaoR {


    /**
     * @param
     * @return
     */
    public List<Map<String, Object>> companyResearch(Map<String, Object> map);



    public JobObjective getByResumeId(Map<String,Object> map);


    JobObjective get(long id);
}
