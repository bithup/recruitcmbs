package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.ShieldCompany;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShieldCompanyDaoW {

    /**
     * 添加一条屏蔽记录
     * @param shieldCompany
     * @return
     */
    int add(ShieldCompany shieldCompany);

    /**
     * 修改屏蔽记录
     * @param shieldCompany
     * @return
     */
    int update(ShieldCompany shieldCompany);
}
