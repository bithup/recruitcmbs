package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IStoreTalentsDaoR;
import com.xgh.recruitcmbs.dao.write.IStoreTalentsDaoW;
import com.xgh.recruitcmbs.entity.StoreTalents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("toreTalentsDao")
public class StoreTalentsDaoImpl implements IStoreTalentsDao {

    @Autowired
    protected IStoreTalentsDaoR storeTalentsDaoR;

    @Autowired
    protected IStoreTalentsDaoW storeTalentsDaoW;

    public StoreTalents get(long id) {
        return storeTalentsDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return storeTalentsDaoR.getListPage(map);
    }

    public int add(StoreTalents storeTalents) {
        return storeTalentsDaoW.add(storeTalents);
    }

    public int update(StoreTalents storeTalents) {
        return storeTalentsDaoW.update(storeTalents);
    }

    public List<StoreTalents> isExist(Map<String, Object> map) {
        return storeTalentsDaoR.isExist(map);
    }

    public int updateBatch(String[] ids) {
        return storeTalentsDaoW.updateBatch(ids);
    }

    public int clearOutByCompanyId(String companyId) {
        return storeTalentsDaoW.clearOutByCompanyId(companyId);
    }
}
