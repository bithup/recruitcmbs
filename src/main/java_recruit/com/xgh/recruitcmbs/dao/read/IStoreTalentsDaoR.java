package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.StoreTalents;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IStoreTalentsDaoR {


    public StoreTalents get(long id);

    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    public List<StoreTalents> isExist(Map<String, Object> map);
}
