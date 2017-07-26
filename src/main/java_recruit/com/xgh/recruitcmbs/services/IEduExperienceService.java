package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.EduExperience;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IEduExperienceService {


    /**
     * 查询教育工作背景
     *
     * @param id
     * @return
     */
    public EduExperience get(long id);

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


    public List<EduExperience> getByResumeId(Map<String, Object> map);

}
