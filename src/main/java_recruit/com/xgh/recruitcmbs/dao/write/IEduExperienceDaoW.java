package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.EduExperience;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IEduExperienceDaoW {



    /**
     * 求职者添加教育背景
     *
     * @param eduExperience
     * @return
     */
    public int add(EduExperience eduExperience);



    /**
     * 求职者修改教育背景
     *
     * @param eduExperience
     * @return
     */
    public int update(EduExperience eduExperience);


}
