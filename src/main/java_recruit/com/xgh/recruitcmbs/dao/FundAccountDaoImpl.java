package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IFundAccountDaoR;
import com.xgh.recruitcmbs.dao.write.IFundAccountDaoW;
import com.xgh.recruitcmbs.entity.FundAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("fundAccountDao")
public class FundAccountDaoImpl implements IFundAccountDao {

    @Autowired
    protected IFundAccountDaoR fundAccountDaoR;

    @Autowired
    protected IFundAccountDaoW fundAccountDaoW;


    public int add(FundAccount fundAccount) {
        return fundAccountDaoW.add(fundAccount);
    }

    public FundAccount get(long id) {
        return fundAccountDaoR.get(id);
    }

    public int update(FundAccount fundAccount) {
        return fundAccountDaoW.update(fundAccount);
    }

    public FundAccount getWalletBalance(Map<String, Object> map) {
        return fundAccountDaoR.getWalletBalance(map);
    }
}
