package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IHouseDaoR;
import com.xgh.recruitcmbs.dao.write.IHouseDaoW;
import com.xgh.recruitcmbs.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("houseDao")
public class HouseDaoImpl implements IHouseDao {

    @Autowired
    protected IHouseDaoR houseDaoR;

    @Autowired
    protected IHouseDaoW houseDaoW;


    public List<Map<String, Object>> getPositionHouseList(Map<String, Object> map) {
        return houseDaoR.getPositionHouseList(map);
    }

    public List<House> isExist(Map<String, Object> map) {
        return houseDaoR.isExist(map);
    }

    public int add(House house) {
        return houseDaoW.add(house);
    }

    public int update(House house) {
        return houseDaoW.update(house);
    }

    public int batchDeleteByIdList(List<String> list) {
        return houseDaoW.batchDeleteByIdList(list);
    }

    public int clearAll() {
        return houseDaoW.clearAll();
    }

    public List<String> getAllByMemberId(Map<String, Object> map) {
        return houseDaoR.getAllByMemberId(map);
    }
}
