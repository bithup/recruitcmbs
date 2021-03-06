package com.xgh.recruitcmbs.services;


import com.xgh.recruitcmbs.entity.WorkExperience;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IWorkExperienceService {

    /**
     * bsx
     * 根据id查询工作经历
     *
     * @param id
     * @return
     */
    public WorkExperience get(long id);

    /**
     * bsx
     * 插入一条工作经历记录
     *
     * @param workExperience
     * @return
     */
    public int insert(WorkExperience workExperience);


    /**
     * bsx
     * 修改一条工作经历记录
     *
     * @param workExperience
     * @return
     */
    public int update(WorkExperience workExperience);

    /**
     * 根据简历id查询工作经验
     *
     * @param map
     * @return
     */
    public List<WorkExperience> getByResumeId(Map<String, Object> map);


}
