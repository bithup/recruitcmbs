package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IWorkExperienceDao;
import com.xgh.recruitcmbs.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("workExperienceService")
public class WorkExperienceServiceImpl implements IWorkExperienceService {

    @Autowired
    protected IWorkExperienceDao workExperienceDao;


    public WorkExperience get(long id) {
        return workExperienceDao.get(id);
    }

    public int insert(WorkExperience workExperience) {
        return workExperienceDao.add(workExperience);
    }

    public int update(WorkExperience workExperience) {
        return workExperienceDao.update(workExperience);
    }

    public List<WorkExperience> getByResumeId(Map<String, Object> map) {
        return workExperienceDao.getByResumeId(map);
    }
}
