package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IEduExperienceDaoR;
import com.xgh.recruitcmbs.dao.write.IEduExperienceDaoW;
import com.xgh.recruitcmbs.entity.EduExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("eduExperienceDao")
public class EduExperienceDaoImpl implements IEduExperienceDao {

    @Autowired
    protected IEduExperienceDaoR eduExperienceDaoR;

    @Autowired
    protected IEduExperienceDaoW eduExperienceDaoW;

    public List<EduExperience> getByResumeId(Map<String, Object> map) {
        return eduExperienceDaoR.getByResumeId(map);
    }


    public EduExperience get(long id) {
        return eduExperienceDaoR.get(id);
    }

    public int add(EduExperience eduExperience) {
        return eduExperienceDaoW.add(eduExperience);
    }

    public int update(EduExperience eduExperience) {
        return eduExperienceDaoW.update(eduExperience);
    }
}
