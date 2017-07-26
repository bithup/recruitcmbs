package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.Recharge;

/**
 * Created by BSX on 2017/4/18.
 */
public interface IRechargeDao {

    /**
     * 根据id获取充值记录详情
     * @param id
     * @return
     */
    Recharge get(long id);


    /**
     * 添加一条充值记录
     * @param recharge
     * @return
     */
    int add(Recharge recharge);

    /**
     * 修改充值记录
     * @param recharge
     * @return
     */
    int update(Recharge recharge);

    /**
     * 根据订单编号查询充值记录详情
     * @param orderNo
     * @return
     */
    Recharge getRechargeByOrderNo(String orderNo);
}
