package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IJobObjectiveDaoR;
import com.xgh.recruitcmbs.dao.write.IJobObjectiveDaoW;
import com.xgh.recruitcmbs.entity.JobObjective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("jobObjectiveDao")
public class JobObjectiveDaoImpl implements IJobObjectiveDao {

    @Autowired
    protected IJobObjectiveDaoR jobObjectiveDaoR;

    @Autowired
    protected IJobObjectiveDaoW jobObjectiveDaoW;


    public List<Map<String, Object>> companyResearch(Map<String,Object> map) {
        return jobObjectiveDaoR.companyResearch(map);
    }
    public JobObjective getByResumeId(Map<String, Object> map) {
        return jobObjectiveDaoR.getByResumeId(map);
    }

    public int add(JobObjective jobObjective) {
        return jobObjectiveDaoW.add(jobObjective);
    }

    public int update(JobObjective jobObjective) {
        return jobObjectiveDaoW.update(jobObjective);
    }

    public JobObjective get(long id) {
        return jobObjectiveDaoR.get(id);
    }
}
