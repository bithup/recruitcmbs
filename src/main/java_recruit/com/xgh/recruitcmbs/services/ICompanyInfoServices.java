package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.ShortMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
public interface ICompanyInfoServices {


    public CompanyInfo get(long id);

    public Map<String, Object> register(HttpServletRequest request, CompanyInfo companyInfo, String validationCode);

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
     * 企业登录
     */
    public CompanyInfo login(Map<String, Object> map);

    /**
     * 更新信息
     */
    public int update(HttpServletRequest request, CompanyInfo companyInfo, ShortMessage shortMessage);

    /**
     * 找回密码
     */
    public CompanyInfo findPassword(Map<String, Object> map);
    /**
     * 修改企业信息
     * @param companyInfo
     * @return
     */
    public int update(CompanyInfo companyInfo);


    /**
     * 分页查询企业
     * @param map
     * @return
     */
    List<CompanyInfo> getListPage(Map<String,Object> map);


}
