package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.House;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IHouseService {

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
     * 添加收藏
     *
     * @param house
     * @return
     */
    int add(House house);


    /**
     * 修改收藏
     *
     * @param house
     * @return
     */
    int update(House house);


    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public int batchDeleteByIdList(List<String> list);

    /**
     * 清除所有
     *
     * @return
     */
    public int clearAll();

    /**
     * 查询出该 会员所有的收藏职位
     *
     * @param map
     * @return
     */
    List<String> getAllByMemberId(Map<String, Object> map);
}
