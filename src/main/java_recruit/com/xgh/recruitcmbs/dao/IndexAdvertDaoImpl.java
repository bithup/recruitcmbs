package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IIndexAdvertDaoR;
import com.xgh.recruitcmbs.dao.write.IIndexAdvertDaoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("indexAdvertDao")
public class IndexAdvertDaoImpl implements IIndexAdvertDao {

    @Autowired
    protected IIndexAdvertDaoR indexAdvertDaoR;

    @Autowired
    protected IIndexAdvertDaoW indexAdvertDaoW;

    public List<Map<String, Object>> getIndexAdverts(Map<String,Object> map) {
        return indexAdvertDaoR.getIndexAdverts(map);
    }
}
