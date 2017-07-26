package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.CompanyInfo;

import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
public interface ICompanyInfoDaoW {

    /**
     * 注册个人信息
     *
     * @param companyInfo
     * @return
     */
    public int add(CompanyInfo companyInfo);

    /**
     * 更新信息
     */
    public int update(CompanyInfo companyInfo);

}
