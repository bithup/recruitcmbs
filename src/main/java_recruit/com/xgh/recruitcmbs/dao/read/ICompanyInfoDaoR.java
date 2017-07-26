package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.CompanyInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
public interface ICompanyInfoDaoR {

    /**
     * cq
     * 根据id查询企业信息
     *
     * @param id
     * @return
     */
    public CompanyInfo get(long id);

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
