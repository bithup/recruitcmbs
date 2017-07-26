package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.House;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IHouseDaoW {


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

}
