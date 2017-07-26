package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IDictionaryDaoR;
import com.xgh.recruitcmbs.dao.write.IDictionaryDaoW;
import com.xgh.recruitcmbs.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("dictionaryDao")
public class DictionaryDaoImpl implements IDictionaryDao {

    @Autowired
    protected IDictionaryDaoR dictionaryDaoR;

    @Autowired
    protected IDictionaryDaoW dictionaryDaoW;


    public Dictionary get(long id) {
        return dictionaryDaoR.get(id);
    }


    public Dictionary getNameByCode(Map<String, Object> map) {
        return dictionaryDaoR.getNameByCode(map);
    }

    public List<Map<String, Object>> getKeyValue(Map<String, Object> map) {
        return dictionaryDaoR.getKeyValue(map);
    }

    public String getValue(Map<String, Object> map) {
        return dictionaryDaoR.getValue(map);
    }
}
