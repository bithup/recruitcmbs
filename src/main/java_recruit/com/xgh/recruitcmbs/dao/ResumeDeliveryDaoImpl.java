package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IResumeDeliveryDaoR;
import com.xgh.recruitcmbs.dao.write.IResumeDeliveryDaoW;
import com.xgh.recruitcmbs.entity.ResumeDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("resumeDeliveryDao")
public class ResumeDeliveryDaoImpl implements IResumeDeliveryDao {


    @Autowired
    protected IResumeDeliveryDaoR resumeDeliveryDaoR;

    @Autowired
    protected IResumeDeliveryDaoW resumeDeliveryDaoW;


    public List<Map<String, Object>> screenResumeDelivery(Map<String, Object> map) {
        return resumeDeliveryDaoR.screenResumeDelivery(map);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return resumeDeliveryDaoR.getListPage(map);
    }

    public ResumeDelivery get(long id) {
        return resumeDeliveryDaoR.get(id);
    }

    public int add(ResumeDelivery resumeDelivery) {
        return resumeDeliveryDaoW.add(resumeDelivery);
    }

    public int update(ResumeDelivery resumeDelivery) {
        return resumeDeliveryDaoW.update(resumeDelivery);
    }

    public List<ResumeDelivery> getLastDeliveries(Map<String, Object> map) {
        return resumeDeliveryDaoR.getLastDeliveries(map);
    }

    public int getDeliveryCount(long positionId) {
        return resumeDeliveryDaoR.getDeliveryCount(positionId);
    }

    public List<LinkedHashMap<String, Object>> getDeliverJobListByPositionId(Map<String, Object> map) {
        return resumeDeliveryDaoR.getDeliverJobListByPositionId(map);
    }

    public List<ResumeDelivery> getDeliveriesAmongThirty(Map<String, Object> map) {
        return resumeDeliveryDaoR.getDeliveriesAmongThirty(map);
    }

    public int addBatch(List<ResumeDelivery> resumeDelivery) {
        return resumeDeliveryDaoW.addBatch(resumeDelivery);
    }

    public List<Map<String, Object>> getDeliveriesList(Map<String, Object> map) {
        return resumeDeliveryDaoR.getDeliveriesList(map);
    }

    public int batchDeleteByIdList(List<String> list) {
        return resumeDeliveryDaoW.batchDeleteByIdList(list);
    }

    public List<String> getDeliveriesListByMemberId(Map<String, Object> map) {
        return resumeDeliveryDaoR.getDeliveriesListByMemberId(map);
    }

    public List<Map<String, Object>> getMessageBoxCount(Map<String, Object> map) {
        return resumeDeliveryDaoR.getMessageBoxCount(map);
    }

    public ResumeDelivery getDeliveryByMemAndPosition(Map<String, Object> map) {
        return resumeDeliveryDaoR.getDeliveryByMemAndPosition(map);
    }
}
