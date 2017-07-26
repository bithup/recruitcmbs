package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IIndexAdvertDao;
import com.xgh.recruitcmbs.dao.read.IIndexAdvertDaoR;
import com.xgh.recruitcmbs.dao.write.IIndexAdvertDaoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("indexAdvertService")
public class IndexAdvertServiceImpl implements IIndexAdvertService {

    @Autowired
    protected IIndexAdvertDao indexAdvertDao;

    public List<Map<String, Object>> getIndexAdverts(Map<String,Object> map) {
        return indexAdvertDao.getIndexAdverts(map);
    }
}
