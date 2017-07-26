package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IGreatDemandDao;
import com.xgh.recruitcmbs.dao.IPositionDao;
import com.xgh.recruitcmbs.dao.IResumeDeliveryDao;
import com.xgh.recruitcmbs.entity.GreatDemand;
import com.xgh.recruitcmbs.entity.Position;
import com.xgh.recruitcmbs.entity.ResumeDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("resumeDeliveryService")
public class ResumeDeliveryServiceImpl implements IResumeDeliveryService {


    @Autowired
    protected IResumeDeliveryDao resumeDeliveryDao;

    @Autowired
    protected IGreatDemandDao greatDemandDao;

    @Autowired
    protected IPositionDao positionDao;

    public List<Map<String, Object>> screenResumeDelivery(Map<String, Object> map) {
        return resumeDeliveryDao.screenResumeDelivery(map);
    }

    /**
     * 收件箱
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return resumeDeliveryDao.getListPage(map);
    }

    public ResumeDelivery get(long id) {
        return resumeDeliveryDao.get(id);
    }

    public int add(ResumeDelivery resumeDelivery) {

/*
        ResumeDelivery resumeDelivery_ = resumeDeliveryDao.get(18);

        resumeDelivery_.setUpdateDate(new Date());
        resumeDeliveryDao.update(resumeDelivery_);*/
/*        ResumeDelivery resumeDelivery1 = null;
        resumeDelivery1.getId();*/

        int flag = resumeDeliveryDao.add(resumeDelivery);


        if (flag > 0) {

            long positionId = resumeDelivery.getPositionId();//职位id

            Position position = positionDao.get(positionId);
            long companyId = position.getCompanyId();//公司id
            long jobKindsId = position.getJobKindsId();//职位分类id

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyId", companyId);
            map.put("jobKindsId", jobKindsId);

            GreatDemand greatDemand_ = greatDemandDao.getHotCount(map);
            if (greatDemand_ != null) {

                int count = greatDemand_.getHotCount();

                greatDemand_.setHotCount(count + 1);
                greatDemand_.setUpdateDate(new Date());

                return greatDemandDao.update(greatDemand_);
            } else {

                GreatDemand greatDemand = new GreatDemand();
                greatDemand.setCompanyId(companyId);
                greatDemand.setJobKindsId(jobKindsId);
                greatDemand.setCreateDate(new Date());
                greatDemand.setUpdateDate(new Date());
                greatDemand.setStatus(1);
                greatDemand.setHotCount(1);

                return greatDemandDao.add(greatDemand);
            }
        } else {
            return -1;
        }
    }

    public int update(ResumeDelivery resumeDelivery) {
        return resumeDeliveryDao.update(resumeDelivery);
    }

    public List<ResumeDelivery> getLastDeliveries(Map<String, Object> map) {
        return resumeDeliveryDao.getLastDeliveries(map);
    }

    public int getDeliveryCount(long positionId) {
        return resumeDeliveryDao.getDeliveryCount(positionId);
    }

    public List<LinkedHashMap<String, Object>> getDeliverJobListByPositionId(Map<String, Object> map) {
        return resumeDeliveryDao.getDeliverJobListByPositionId(map);
    }

    /**
     * 30天以内投递的简历
     *
     * @param map
     * @return
     */
    public List<ResumeDelivery> getDeliveriesAmongThirty(Map<String, Object> map) {
        return resumeDeliveryDao.getDeliveriesAmongThirty(map);
    }

    /**
     * 批量添加
     *
     * @param resumeDelivery
     * @return
     */
    public int addBatch(List<ResumeDelivery> resumeDelivery) {

        int flag = resumeDeliveryDao.addBatch(resumeDelivery);
        if (flag > 0) {

            for (ResumeDelivery resumeDelivery_ : resumeDelivery) {
                long positionId = resumeDelivery_.getPositionId();//职位id

                Position position = positionDao.get(positionId);
                long companyId = position.getCompanyId();//公司id
                long jobKindsId = position.getJobKindsId();//职位分类id

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("companyId", companyId);
                map.put("jobKindsId", jobKindsId);

                GreatDemand greatDemand_ = greatDemandDao.getHotCount(map);
                if (greatDemand_ != null) {

                    int count = greatDemand_.getHotCount();

                    greatDemand_.setHotCount(count + 1);
                    greatDemand_.setUpdateDate(new Date());

                    return greatDemandDao.update(greatDemand_);
                } else {
                    System.out.println("...............");
                    GreatDemand greatDemand = new GreatDemand();
                    greatDemand.setCompanyId(companyId);
                    greatDemand.setJobKindsId(jobKindsId);
                    greatDemand.setCreateDate(new Date());
                    greatDemand.setUpdateDate(new Date());
                    greatDemand.setStatus(1);
                    greatDemand.setHotCount(1);

                    return greatDemandDao.add(greatDemand);
                }

            }
        }


        return 1;
    }

    /**
     * 根据会员id获取简历投递记录
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getDeliveriesList(Map<String, Object> map) {
        return resumeDeliveryDao.getDeliveriesList(map);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public int batchDeleteByIdList(List<String> list) {
        return resumeDeliveryDao.batchDeleteByIdList(list);
    }

    /**
     * 会员id查询
     *
     * @param map
     * @return
     */
    public List<String> getDeliveriesListByMemberId(Map<String, Object> map) {
        return resumeDeliveryDao.getDeliveriesListByMemberId(map);
    }

    public List<Map<String, Object>> getMessageBoxCount(Map<String, Object> map) {
        return resumeDeliveryDao.getMessageBoxCount(map);
    }

    public ResumeDelivery getDeliveryByMemAndPosition(Map<String, Object> map) {
        return resumeDeliveryDao.getDeliveryByMemAndPosition(map);
    }
}
