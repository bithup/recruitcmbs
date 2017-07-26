package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IHouseDao;
import com.xgh.recruitcmbs.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("houseService")
public class HouseServiceImpl implements IHouseService {

    @Autowired
    protected IHouseDao houseDao;


    public List<Map<String, Object>> getPositionHouseList(Map<String, Object> map) {
        return houseDao.getPositionHouseList(map);
    }

    public List<House> isExist(Map<String, Object> map) {
        return houseDao.isExist(map);
    }

    public int add(House house) {
        return houseDao.add(house);
    }

    public int update(House house) {
        return houseDao.update(house);
    }

    public int batchDeleteByIdList(List<String> list) {
        return houseDao.batchDeleteByIdList(list);
    }

    public int clearAll() {
        return houseDao.clearAll();
    }

    public List<String> getAllByMemberId(Map<String, Object> map) {
        return houseDao.getAllByMemberId(map);
    }
}
