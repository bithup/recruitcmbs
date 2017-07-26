package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.ResumeDelivery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IResumeDeliveryDaoR {


    /**
     * 投递简历筛选
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> screenResumeDelivery(Map<String, Object> map);


    /**
     * 收件箱
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    /**
     * 根据id获取投递记录
     *
     * @param id
     * @return
     */
    public ResumeDelivery get(long id);


    public List<ResumeDelivery> getLastDeliveries(Map<String, Object> map);

    /**
     * 获取投递该职位的数量
     *
     * @param positionId
     * @return
     */
    public int getDeliveryCount(long positionId);

    /**
     * 根据职位id查询已经投递的职位列表
     *
     * @param map
     * @return
     */
    public List<LinkedHashMap<String, Object>> getDeliverJobListByPositionId(Map<String, Object> map);


    /**
     * 查询看30天之内
     *
     * @param map
     * @return
     */
    public List<ResumeDelivery> getDeliveriesAmongThirty(Map<String, Object> map);

    /**
     * 根据会员id查询已经投递的职位
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getDeliveriesList(Map<String, Object> map);

    /**
     * 根据会员id只查询简历投递表
     *
     * @param map
     * @return
     */
    public List<String> getDeliveriesListByMemberId(Map<String, Object> map);

    /**
     * 收件箱查询
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getMessageBoxCount(Map<String, Object> map);

    /**
     * 根据职位id和公司id
     * @param map
     * @return
     */
    public ResumeDelivery getDeliveryByMemAndPosition(Map<String, Object> map);


}
