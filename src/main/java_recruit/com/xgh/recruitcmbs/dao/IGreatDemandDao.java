package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.GreatDemand;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IGreatDemandDao {

    /**
     * 热门企业
     *
     * @return
     */
    public List<Map<String, Object>> getHotCompanies();


    /**
     * 热门职位分类
     *
     * @return
     */
    public List<Map<String, Object>> getHotPositions();


    /**
     * 添加
     *
     * @param greatDemand
     * @return
     */
    public int add(GreatDemand greatDemand);


    /**
     * 获取该职位已经投递的数量
     *
     * @param map
     * @return
     */
    public GreatDemand getHotCount(Map<String, Object> map);

    /**
     * 职位数量更新
     *
     * @param greatDemand
     * @return
     */
    public int update(GreatDemand greatDemand);


}
