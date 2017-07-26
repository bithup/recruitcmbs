package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.ICompanyInfoDaoR;
import com.xgh.recruitcmbs.dao.write.ICompanyInfoDaoW;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
@Repository("companyInfoDao")
public class CompanyInfoDaoImpl implements ICompanyInfoDao {


    @Autowired
    protected ICompanyInfoDaoR companyInfoDaoR;

    @Autowired
    protected ICompanyInfoDaoW companyInfoDaoW;


    public CompanyInfo get(long id) {
        return companyInfoDaoR.get(id);
    }


    public int add(CompanyInfo companyInfo) {
        return companyInfoDaoW.add(companyInfo);
    }

    public int update(CompanyInfo companyInfo) {
        return companyInfoDaoW.update(companyInfo);
    }

    public int getCompanyInfoByAccount(String account) {
        return companyInfoDaoR.getCompanyInfoByAccount(account);
    }

    public int getCompanyInfoByMobile(String mobile) {
        return companyInfoDaoR.getCompanyInfoByMobile(mobile);
    }

    public CompanyInfo login(Map<String, Object> map) {
        return companyInfoDaoR.login(map);
    }

    public CompanyInfo findPassword(Map<String, Object> map) {
        return companyInfoDaoR.findPassword(map);
    }

    public List<CompanyInfo> getListPage(Map<String, Object> map) {
        return companyInfoDaoR.getListPage(map);
    }

    public List<CompanyInfo> getCompanyInfoByName(Map<String, Object> map) {
        return companyInfoDaoR.getCompanyInfoByName(map);
    }
}
