package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IProfessionalSkillsDaoR;
import com.xgh.recruitcmbs.dao.write.IProfessionalSkillsDaoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("professionalSkillsDao")
public class ProfessionalSkillsDaoImpl implements IProfessionalSkillsDao {

    @Autowired
    protected IProfessionalSkillsDaoR professionalSkillsDaoR;

    @Autowired
    protected IProfessionalSkillsDaoW professionalSkillsDaoW;
}
