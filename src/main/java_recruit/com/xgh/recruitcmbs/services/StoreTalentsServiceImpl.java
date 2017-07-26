package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IStoreTalentsDao;
import com.xgh.recruitcmbs.entity.StoreTalents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("storeTalentsService")
public class StoreTalentsServiceImpl implements IStoreTalentsService {

    @Autowired
    protected IStoreTalentsDao storeTalentsDao;

    public StoreTalents get(long id) {
        return storeTalentsDao.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return storeTalentsDao.getListPage(map);
    }

    public int add(StoreTalents storeTalents) {
        return storeTalentsDao.add(storeTalents);
    }

    public int update(StoreTalents storeTalents) {
        return storeTalentsDao.update(storeTalents);
    }

    public List<StoreTalents> isExist(Map<String, Object> map) {
        return storeTalentsDao.isExist(map);
    }

    public int updateBatch(String[] ids) {
        return storeTalentsDao.updateBatch(ids);
    }

    public int clearOutByCompanyId(String companyId) {
        return storeTalentsDao.clearOutByCompanyId(companyId);
    }
}
