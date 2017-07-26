package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.ShieldCompany;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShieldCompanyService {

    /**
     * 添加屏蔽公司
     * @param shieldCompany
     * @return
     */
    Map<String,Object> add(ShieldCompany shieldCompany);

    /**
     * 删除屏蔽公司（逻辑删除）
     * @param request
     * @return
     */
    Map<String,Object> update(HttpServletRequest request);

    /**
     * 我屏蔽的公司
     * @param map
     * @return
     */
    List<Map<String,Object>> getList(Map<String,Object> map);


    /**
     * 根据id获取屏蔽记录
     * @param id
     * @return
     */
    ShieldCompany get(long id);
}
