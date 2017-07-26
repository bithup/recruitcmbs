package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IGreatDemandDao;
import com.xgh.recruitcmbs.entity.GreatDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("greatDemandService")
public class GreatDemandServiceImpl implements IGreatDemandService {

    @Autowired
    protected IGreatDemandDao greatDemandDao;


    public List<Map<String, Object>> getHotCompanies() {
        return greatDemandDao.getHotCompanies();
    }

    public List<Map<String, Object>> getHotPositions() {
        return greatDemandDao.getHotPositions();
    }

    public int add(GreatDemand greatDemand) {
        return greatDemandDao.add(greatDemand);
    }


    public GreatDemand getHotCount(Map<String, Object> map) {
        return greatDemandDao.getHotCount(map);
    }

    public int update(GreatDemand greatDemand) {
        return greatDemandDao.update(greatDemand);
    }
}
