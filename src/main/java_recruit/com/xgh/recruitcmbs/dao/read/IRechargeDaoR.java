package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.Recharge;

/**
 * Created by BSX on 2017/4/18.
 */
public interface IRechargeDaoR {

    /**
     * 根据id获取充值记录详情
     * @param id
     * @return
     */
    Recharge get(long id);

    /**
     * 根据订单编号查询充值记录详情
     * @param orderNo
     * @return
     */
    Recharge getRechargeByOrderNo(String orderNo);
}
