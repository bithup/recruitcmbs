package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.Kinds;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IKindsService {


    public Kinds get(long id);

    public List<Map<String,Object>> getThirdLevel(Map<String, Object> map);

    /**
     * 根据父id查询分类列表
     * @param parentId
     * @return
     */
    public List<Map<String,Object>> getByPid(long parentId);


    /**
     * 首页一级分类查询
     * @return
     */
    public List<Kinds> getIndexKinds();
}
