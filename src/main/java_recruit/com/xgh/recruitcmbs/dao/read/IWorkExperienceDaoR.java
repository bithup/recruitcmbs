package com.xgh.recruitcmbs.dao.read;


import com.xgh.recruitcmbs.entity.WorkExperience;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IWorkExperienceDaoR {

    /**
     * bsx
     * 根据id查询工作经历
     *
     * @param id
     * @return
     */
    public WorkExperience get(long id);

    public List<WorkExperience> getByResumeId(Map<String,Object> map);

}
