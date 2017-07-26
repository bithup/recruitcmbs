package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.EduExperience;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IEduExperienceDaoR {

    public List<EduExperience> getByResumeId(Map<String, Object> map);


    /**
     * 查询教育工作背景
     *
     * @param id
     * @return
     */
    public EduExperience get(long id);




}
