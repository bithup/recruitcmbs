package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.FundAccount;

import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IFundAccountDao {


    public int add(FundAccount fundAccount);

    public FundAccount get(long id);

    public int update(FundAccount fundAccount);

    /**
     * 钱包余额
     * @param map
     * @return
     */
    public FundAccount getWalletBalance(Map<String,Object> map);
}
