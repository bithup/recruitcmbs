package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IKindsDao;
import com.xgh.recruitcmbs.dao.IPositionDao;
import com.xgh.recruitcmbs.dao.IZoneDao;
import com.xgh.recruitcmbs.entity.Kinds;
import com.xgh.recruitcmbs.entity.Position;
import com.xgh.recruitcmbs.entity.Zone;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created by BSX on 2017/3/14.
 */
@Service("positionService")
public class PositionServiceImpl implements IPositionService {

    @Autowired
    protected IPositionDao positionDao;

    @Autowired
    protected IKindsDao kindsDao;

    @Autowired
    protected IZoneDao zoneDao;


    public Map<String, Object> getPositionInfo(long id) {
        return positionDao.getPositionInfo(id);
    }

    public List<Map<String, Object>> getIndexSearch(Map<String, Object> map) {
        return positionDao.getIndexSearch(map);
    }


    public List<Position> getPositionList(Map<String, Object> map) {
        return positionDao.getPositionList(map);
    }

    public int save(HttpServletRequest request, Position position) {


        Kinds kinds = kindsDao.get(position.getJobKindsId());

        String zoneName = position.getZoneName();
        if (!"".equals(zoneName)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("zoneName", zoneName);
            List<Zone> zone = zoneDao.getZones(map);
            Zone zone_ = zone.get(0);
            position.setZoneId(zone_.getId());
        }
        if (kinds != null) {
            position.setJobKindsName(kinds.getName());
        }

        position.setCreateDate(new Date());
        position.setUpdateDate(new Date());
        position.setUnit(1);
        position.setStatus(1);
        position.setIsReceive(1);
        position.setReleaseStatus(1);
        position.setPositionStatus(1);

        return positionDao.add(position);


    }

    public int update(HttpServletRequest request, Position position) {

        Position position_ = positionDao.get(position.getId());

        Kinds kinds = kindsDao.get(position.getJobKindsId());

        String zoneName = position.getZoneName();
        if (!"".equals(zoneName)) {
            System.out.println();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("zoneName", zoneName);
            List<Zone> zone = zoneDao.getZones(map);
            Zone zone_ = zone.get(0);
            position.setZoneId(zone_.getId());

        }
        if (kinds != null) {
            position.setJobKindsName(kinds.getName());
        }

        position.setUnit(position_.getUnit());
        position.setReleaseStatus(position_.getReleaseStatus());
        position.setPositionStatus(position_.getPositionStatus());
        position.setIsReceive(position_.getIsReceive());
        position.setIsCheck(position_.getIsCheck());
        position.setCreateDate(position_.getCreateDate());
        position.setUpdateDate(new Date());
        position.setStatus(1);
        return positionDao.update(position);

    }

    public int batchUpdateByIdList(List<String> list) {
        return positionDao.batchUpdateByIdList(list);
    }

    public List<Position> getExpiryPositionList(Map<String, Object> map) {
        return positionDao.getExpiryPositionList(map);
    }

    public int batchDeleteByIdList(List<String> list) {
        return positionDao.batchDeleteByIdList(list);
    }


    public int batchUpdateMap(Map<String, Object> map) {
        return positionDao.batchUpdateMap(map);
    }

    public Position get(long id) {
        return positionDao.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return positionDao.getListPage(map);
    }

    public List<Position> getByCompanyId(Map<String, Object> map) {
        return positionDao.getByCompanyId(map);
    }

    public List<Map<String, Object>> screenPosition(Map<String, Object> map) {
        return positionDao.screenPosition(map);
    }

    public List<Position> getJobKindsNameByCompanyId(Map<String, Object> map) {
        return positionDao.getJobKindsNameByCompanyId(map);
    }
}
