package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IFundAccountDao;
import com.xgh.recruitcmbs.dao.IRechargeDao;
import com.xgh.recruitcmbs.dao.ITransactionRecordDao;
import com.xgh.recruitcmbs.entity.FundAccount;
import com.xgh.recruitcmbs.entity.Recharge;
import com.xgh.recruitcmbs.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BSX on 2017/4/18.
 */
@Service("rechargeService")
public class RechargeServiceImpl implements IRechargeService {

    @Autowired
    protected IRechargeDao rechargeDao;

    @Autowired
    protected ITransactionRecordDao transactionRecordDao;

    @Autowired
    protected IFundAccountDao fundAccountDao;

    public Recharge get(long id) {
        return rechargeDao.get(id);
    }

    public int add(Recharge recharge) {
        return rechargeDao.add(recharge);
    }

    public int update(Recharge recharge) {
        //添加一条交易记录
        Date date = new Date();
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setMemberId(recharge.getMemberId());
        transactionRecord.setMemberType(2);
        transactionRecord.setDealMoney(recharge.getRechargeAmount());
        transactionRecord.setDealType(1);
        transactionRecord.setDealTime(date);
        transactionRecord.setSources(4);
        transactionRecord.setCreateDate(date);
        transactionRecord.setUpdateDate(date);
        transactionRecord.setStatus(0);
        transactionRecord.setInstCode("recruit");
        transactionRecordDao.add(transactionRecord);

        //将充值金额添加到用户钱包
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("dataId",recharge.getMemberId());
        map.put("dataType",recharge.getMemberType());
        FundAccount fundAccount = fundAccountDao.getWalletBalance(map);
        fundAccount.setPurseBalance(fundAccount.getPurseBalance()+recharge.getRechargeAmount());
        fundAccount.setUpdateDate(date);

        //修改充值状态等信息
        return rechargeDao.update(recharge);
    }

    public Recharge getRechargeByOrderNo(String orderNo) {
        return rechargeDao.getRechargeByOrderNo(orderNo);
    }
}
