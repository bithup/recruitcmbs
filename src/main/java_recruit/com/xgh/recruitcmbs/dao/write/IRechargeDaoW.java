package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.Recharge;

/**
 * Created by BSX on 2017/4/18.
 */
public interface IRechargeDaoW {

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
}
