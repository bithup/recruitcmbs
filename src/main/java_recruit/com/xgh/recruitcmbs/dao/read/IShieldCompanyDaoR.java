package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.ShieldCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShieldCompanyDaoR {

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
