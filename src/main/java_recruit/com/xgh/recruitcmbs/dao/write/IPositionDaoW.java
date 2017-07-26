package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.Position;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IPositionDaoW {

    /**
     * 企业发布职位
     *
     * @param position
     * @return
     */
    public int add(Position position);


    /**
     * 企业修改职位
     *
     * @param position
     * @return
     */
    public int update(Position position);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int batchUpdateByIdList(List<String> list);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public int batchDeleteByIdList(List<String> list);

    /**
     *
     * @param map
     * @return
     */
    public int batchUpdateMap(Map<String, Object> map);


}
