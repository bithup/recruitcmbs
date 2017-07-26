package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.GreatDemand;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IGreatDemandDaoR {

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
     * 获取该职位已经投递的数量
     *
     * @param map
     * @return
     */
    public GreatDemand getHotCount(Map<String, Object> map);


}
