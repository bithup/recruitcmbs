package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IKindsDao;
import com.xgh.recruitcmbs.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("kindsService")
public class KindsServiceImpl implements IKindsService {

    @Autowired
    protected IKindsDao kindsDao;


    public Kinds get(long id) {
        return kindsDao.get(id);
    }


    public List<Map<String,Object>> getThirdLevel(Map<String, Object> map) {
        return kindsDao.getThirdLevel(map);
    }

    public List<Map<String, Object>> getByPid(long parentId) {
        return kindsDao.getByPid(parentId);
    }

    public List<Kinds> getIndexKinds() {
        return kindsDao.getIndexKinds();
    }
}
