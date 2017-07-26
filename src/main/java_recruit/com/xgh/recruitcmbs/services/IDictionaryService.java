package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.Dictionary;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IDictionaryService {


    public Dictionary get(long id);


    /**
     * 字典表根据code查询name值
     *
     * @param map
     * @return
     */
    public Dictionary getNameByCode(Map<String, Object> map);


    /**
     * 根据item获得键值对
     * @param map
     * @return
     */
    public List<Map<String,Object>> getKeyValue(Map<String, Object> map);


    /**
     * 根据code,item查询value值
     * @param map
     * @return
     */
    public String getValue(Map<String, Object> map);


}
