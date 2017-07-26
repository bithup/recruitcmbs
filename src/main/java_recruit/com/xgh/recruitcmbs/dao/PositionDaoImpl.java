package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IPositionDaoR;
import com.xgh.recruitcmbs.dao.write.IPositionDaoW;
import com.xgh.recruitcmbs.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("positionDao")
public class PositionDaoImpl implements IPositionDao {

    @Autowired
    protected IPositionDaoR positionDaoR;

    @Autowired
    protected IPositionDaoW positionDaoW;

    public List<Map<String, Object>> getIndexSearch(Map<String, Object> map) {
        return positionDaoR.getIndexSearch(map);
    }

    public Map<String, Object> getPositionInfo(long id) {
        return positionDaoR.getPositionInfo(id);
    }

    public List<Position> getPositionList(Map<String, Object> map) {
        return positionDaoR.getPositionList(map);
    }

    public int add(Position position) {
        return positionDaoW.add(position);
    }


    public int update(Position position) {
        return positionDaoW.update(position);
    }


    public int batchUpdateByIdList(List<String> list) {
        return positionDaoW.batchUpdateByIdList(list);
    }

    public List<Position> getExpiryPositionList(Map<String, Object> map) {
        return positionDaoR.getExpiryPositionList(map);
    }

    public int batchDeleteByIdList(List<String> list) {
        return positionDaoW.batchDeleteByIdList(list);
    }


    public int batchUpdateMap(Map<String, Object> map) {
        return positionDaoW.batchUpdateMap(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return positionDaoR.getListPage(map);
    }

    public List<Position> getByCompanyId(Map<String, Object> map) {
        return positionDaoR.getByCompanyId(map);
    }

    public List<Map<String, Object>> screenPosition(Map<String, Object> map) {
        return positionDaoR.screenPosition(map);
    }

    public Position get(long id) {
        return positionDaoR.get(id);
    }

    public List<Position> getJobKindsNameByCompanyId(Map<String, Object> map) {
        return positionDaoR.getJobKindsNameByCompanyId(map);
    }
}
