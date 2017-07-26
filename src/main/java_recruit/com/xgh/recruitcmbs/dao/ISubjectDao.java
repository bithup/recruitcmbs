package com.xgh.recruitcmbs.dao;


import com.xgh.recruitcmbs.entity.Subject;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
public interface ISubjectDao {

    /**
     * bsx
     * 根据id查询轮播图
     *
     * @param id
     * @return
     */
    public Subject get(long id);

    /**
     * bsx
     * 添加轮播图
     *
     * @param subject
     * @return
     */
    public int add(Subject subject);


    /**
     * bsx
     * 修改轮播图
     *
     * @param subject
     * @return
     */
    public int update(Subject subject);


    /**
     * 获取首页轮播图
     * @return
     */
    public List<Subject> getSubjects();

}
