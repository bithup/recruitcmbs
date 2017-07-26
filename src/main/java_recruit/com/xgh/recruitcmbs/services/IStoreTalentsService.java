package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.StoreTalents;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IStoreTalentsService {

    public StoreTalents get(long id);

    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    public int add(StoreTalents storeTalents);

    public int update(StoreTalents storeTalents);

    public List<StoreTalents> isExist(Map<String, Object> map);

    public int updateBatch(String[] ids);

    public int clearOutByCompanyId(String companyId);
}
