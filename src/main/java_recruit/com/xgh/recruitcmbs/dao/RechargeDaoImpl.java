package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IRechargeDaoR;
import com.xgh.recruitcmbs.dao.write.IRechargeDaoW;
import com.xgh.recruitcmbs.entity.Recharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/4/18.
 */
@Service("rechargeDao")
public class RechargeDaoImpl implements IRechargeDao {

    @Autowired
    protected IRechargeDaoR rechargeDaoR;

    @Autowired
    protected IRechargeDaoW rechargeDaoW;


    public Recharge get(long id) {
        return rechargeDaoR.get(id);
    }

    public int add(Recharge recharge) {
        return rechargeDaoW.add(recharge);
    }

    public int update(Recharge recharge) {
        return rechargeDaoW.update(recharge);
    }

    public Recharge getRechargeByOrderNo(String orderNo) {
        return rechargeDaoR.getRechargeByOrderNo(orderNo);
    }
}
