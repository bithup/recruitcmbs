package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IGreatDemandDaoR;
import com.xgh.recruitcmbs.dao.write.IGreatDemandDaoW;
import com.xgh.recruitcmbs.entity.GreatDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("greatDemandDao")
public class GreatDemandDaoImpl implements IGreatDemandDao {

    @Autowired
    protected IGreatDemandDaoR greatDemandDaoR;

    @Autowired
    protected IGreatDemandDaoW greatDemandDaoW;

    public List<Map<String, Object>> getHotCompanies() {
        return greatDemandDaoR.getHotCompanies();
    }

    public List<Map<String, Object>> getHotPositions() {
        return greatDemandDaoR.getHotPositions();
    }


    public int add(GreatDemand greatDemand) {
        return greatDemandDaoW.add(greatDemand);
    }

    public GreatDemand getHotCount(Map<String, Object> map) {
        return greatDemandDaoR.getHotCount(map);
    }

    public int update(GreatDemand greatDemand) {
        return greatDemandDaoW.update(greatDemand);
    }
}
