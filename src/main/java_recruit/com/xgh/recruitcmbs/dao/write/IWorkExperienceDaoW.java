package com.xgh.recruitcmbs.dao.write;


import com.xgh.recruitcmbs.entity.WorkExperience;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IWorkExperienceDaoW {

    /**
     * bsx
     * 插入一条工作经历记录
     *
     * @param workExperience
     * @return
     */
    public int add(WorkExperience workExperience);


    /**
     * bsx
     * 修改一条工作经历记录
     *
     * @param workExperience
     * @return
     */
    public int update(WorkExperience workExperience);

}
