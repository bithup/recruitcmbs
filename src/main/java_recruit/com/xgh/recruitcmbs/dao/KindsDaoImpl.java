package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IKindsDaoR;
import com.xgh.recruitcmbs.dao.write.IKindsDaoW;
import com.xgh.recruitcmbs.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("kindsDao")
public class KindsDaoImpl implements IKindsDao {

    @Autowired
    protected IKindsDaoR kindsDaoR;

    @Autowired
    protected IKindsDaoW kindsDaoW;


    public Kinds get(long id) {
        return kindsDaoR.get(id);
    }


    public List<Map<String,Object>> getThirdLevel(Map<String, Object> map) {
        return kindsDaoR.getThirdLevel(map);
    }

    public List<Map<String, Object>> getByPid(long parentId) {
        return kindsDaoR.getByPid(parentId);
    }

    public List<Kinds> getIndexKinds() {
        return kindsDaoR.getIndexKinds();
    }
}
