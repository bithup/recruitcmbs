package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IHouseDaoR {

    /**
     * 职位搜藏列表
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getPositionHouseList(Map<String, Object> map);


    /**
     * 是否收藏公司
     *
     * @param map
     * @return
     */
    List<House> isExist(Map<String, Object> map);

    /**
     * 查询出该 会员所有的收藏职位
     *
     * @param map
     * @return
     */
    List<String> getAllByMemberId(Map<String, Object> map);

}
