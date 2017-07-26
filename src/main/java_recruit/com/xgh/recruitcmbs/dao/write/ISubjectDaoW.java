package com.xgh.recruitcmbs.dao.write;


import com.xgh.recruitcmbs.entity.Subject;

/**
 * Created by BSX on 2017/3/14.
 */
public interface ISubjectDaoW {

    /**
     * bsx
     * 插入一条轮播图
     *
     * @param subject
     * @return
     */
    public int add(Subject subject);


    /**
     * bsx
     * 修改一条轮播图
     *
     * @param subject
     * @return
     */
    public int update(Subject subject);

}
