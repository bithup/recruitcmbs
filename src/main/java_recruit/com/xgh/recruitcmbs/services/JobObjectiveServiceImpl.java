package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IJobObjectiveDao;
import com.xgh.recruitcmbs.entity.JobObjective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("jobObjectiveService")
public class JobObjectiveServiceImpl implements IJobObjectiveService {


    @Autowired
    protected IJobObjectiveDao jobObjectiveDao;


    public List<Map<String, Object>> companyResearch(Map<String,Object> map) {
        return jobObjectiveDao.companyResearch(map);
    }

    public JobObjective getByResumeId(Map<String, Object> map) {
        return jobObjectiveDao.getByResumeId(map);
    }

    public int update(JobObjective jobObjective) {
        return jobObjectiveDao.update(jobObjective);
    }

    public JobObjective get(long id) {
        return jobObjectiveDao.get(id);
    }
}
