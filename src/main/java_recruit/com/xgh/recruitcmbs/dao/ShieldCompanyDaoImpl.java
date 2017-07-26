package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IShieldCompanyDaoR;
import com.xgh.recruitcmbs.dao.write.IShieldCompanyDaoW;
import com.xgh.recruitcmbs.entity.ShieldCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("shieldCompanyDao")
public class ShieldCompanyDaoImpl implements IShieldCompanyDao {

    @Autowired
    protected IShieldCompanyDaoR shieldCompanyDaoR;

    @Autowired
    protected IShieldCompanyDaoW shieldCompanyDaoW;

    public int add(ShieldCompany shieldCompany) {
        return shieldCompanyDaoW.add(shieldCompany);
    }

    public int update(ShieldCompany shieldCompany) {
        return shieldCompanyDaoW.update(shieldCompany);
    }

    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return shieldCompanyDaoR.getList(map);
    }

    public ShieldCompany get(long id) {
        return shieldCompanyDaoR.get(id);
    }
}
