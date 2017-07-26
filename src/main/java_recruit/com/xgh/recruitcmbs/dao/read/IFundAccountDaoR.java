package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.FundAccount;

import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IFundAccountDaoR {

    public FundAccount get(Long id);

    /**
     * 钱包余额
     * @param map
     * @return
     */
    public FundAccount getWalletBalance(Map<String,Object> map);

}
