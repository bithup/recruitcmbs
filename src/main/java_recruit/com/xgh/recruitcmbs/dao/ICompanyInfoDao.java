package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.CompanyInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
public interface ICompanyInfoDao {


    public CompanyInfo get(long id);


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


    /**
     * 根据账号查询此账号是否已经存在
     *
     * @param account
     * @return
     */
    public int getCompanyInfoByAccount(String account);


    /**
     * 根据绑定手机号
     *
     * @param mobile
     * @return
     */
    public int getCompanyInfoByMobile(String mobile);

    /**
     * 根据企业姓名查询企业信息
     *
     * @param map
     * @return
     */
    public List<CompanyInfo> getCompanyInfoByName(Map<String, Object> map);


    /**
     * 企业登录
     */
    public CompanyInfo login(Map<String, Object> map);

    /**
     * 找回密码
     */
    public CompanyInfo findPassword(Map<String, Object> map);


    /**
     * 分页查询企业
     *
     * @param map
     * @return
     */
    List<CompanyInfo> getListPage(Map<String, Object> map);


}
