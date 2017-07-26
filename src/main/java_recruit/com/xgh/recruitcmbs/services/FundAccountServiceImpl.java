package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IFundAccountDao;
import com.xgh.recruitcmbs.entity.FundAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("fundAccountService")
public class FundAccountServiceImpl implements IFundAccountService {

    @Autowired
    protected IFundAccountDao fundAccountDao;

    public FundAccount get(long id) {
        return fundAccountDao.get(id);
    }

    public int add(FundAccount fundAccount) {
        return fundAccountDao.add(fundAccount);
    }

    public int update(FundAccount fundAccount) {
        return fundAccountDao.update(fundAccount);
    }

    public FundAccount getWalletBalance(Map<String, Object> map) {
        return fundAccountDao.getWalletBalance(map);
    }
}
