package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IWorkExperienceDaoR;
import com.xgh.recruitcmbs.dao.write.IWorkExperienceDaoW;
import com.xgh.recruitcmbs.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("workExperienceDao")
public class WorkExperienceDaoImpl implements IWorkExperienceDao {

    @Autowired
    protected IWorkExperienceDaoR workExperienceDaoR;

    @Autowired
    protected IWorkExperienceDaoW workExperienceDaoW;

    public WorkExperience get(long id) {
        return workExperienceDaoR.get(id);
    }

    public int add(WorkExperience workExperience) {
        return workExperienceDaoW.add(workExperience);
    }

    public int update(WorkExperience workExperience) {
        return workExperienceDaoW.update(workExperience);
    }

    public List<WorkExperience> getByResumeId(Map<String, Object> map) {
        return workExperienceDaoR.getByResumeId(map);
    }
}
