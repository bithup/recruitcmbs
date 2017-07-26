package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IEduExperienceDao;
import com.xgh.recruitcmbs.entity.EduExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("eduExperienceService")
public class EduExperienceServiceImpl implements IEduExperienceService {

    @Autowired
    protected IEduExperienceDao eduExperienceDao;


    public EduExperience get(long id) {
        return eduExperienceDao.get(id);
    }

    public int add(EduExperience eduExperience) {
        return eduExperienceDao.add(eduExperience);
    }

    public int update(EduExperience eduExperience) {
        return eduExperienceDao.update(eduExperience);
    }

    public List<EduExperience> getByResumeId(Map<String, Object> map) {
        return eduExperienceDao.getByResumeId(map);
    }
}
