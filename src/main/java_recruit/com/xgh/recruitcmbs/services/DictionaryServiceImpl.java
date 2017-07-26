package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IDictionaryDao;
import com.xgh.recruitcmbs.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    protected IDictionaryDao dictionaryDao;


    public Dictionary get(long id) {
        return dictionaryDao.get(id);
    }


    public Dictionary getNameByCode(Map<String, Object> map) {
        return dictionaryDao.getNameByCode(map);
    }

    public List<Map<String, Object>> getKeyValue(Map<String, Object> map) {

        return dictionaryDao.getKeyValue(map);
    }

    public String getValue(Map<String, Object> map) {
        return dictionaryDao.getValue(map);
    }
}
