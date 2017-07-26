package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IProfessionalSkillsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("professionalSkillsService")
public class ProfessionalSkillsServiceImpl implements IProfessionalSkillsService {

    @Autowired
    protected IProfessionalSkillsDao professionalSkillsDao;

}
