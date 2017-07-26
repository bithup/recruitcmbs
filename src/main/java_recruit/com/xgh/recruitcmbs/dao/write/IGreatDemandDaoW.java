package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.GreatDemand;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IGreatDemandDaoW {

    /**
     * 职位数量添加
     *
     * @param greatDemand
     * @return
     */
    public int add(GreatDemand greatDemand);

    /**
     * 职位数量更新
     * @param greatDemand
     * @return
     */
    public int update(GreatDemand greatDemand);


}
