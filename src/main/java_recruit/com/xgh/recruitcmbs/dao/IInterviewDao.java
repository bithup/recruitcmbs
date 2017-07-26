package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.Interview;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IInterviewDao {

    /**
     * 企业获取发送的面试邀请记录
     * @param map
     * @return
     */
    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    /**
     * 企业发送面试邀请
     * @param interview
     * @return
     */
    public int add(Interview interview);

    /**
     * 企业修改面试邀请的邀请状态
     * @param interview
     * @return
     */
    public int update(Interview interview);

    /**
     * 企业删除发送的面试邀请
     * @param ids
     * @return
     */
    public int updateBatch(String[] ids);

    /**
     * 企业清除发送的面试邀请记录
     * @param companyId
     * @return
     */
    public int clearOutByCompanyId(String companyId);

    /**
     * 企业获取最近发送的面试邀请
     * @param map
     * @return
     */
    public Interview getLastInterview(Map<String,Object> map);

    /**
     * 根据邀请id查询面试邀请
     * @param id
     * @return
     */
    public Interview get(long id);

    /**
     * 获取企业已经招收的人数
     * @param map
     * @return
     */
    int getEnrollNum(Map<String,Object> map);

    /**
     * 查询求职者面试邀请记录
     * @param map
     * @return
     */
    List<Map<String,Object>> getInterviewsByMemberId(Map<String,Object> map);

    /**
     *求职者删除面试邀请
     * @param ids
     * @return
     */
    int deleteMemberInterviews(String[] ids);


    /**
     * 求职者清空面试邀请
     * @param memberId
     * @return
     */
    int clearOutByMemberId(String memberId);

    /**
     * 企业给求职者发送面试邀请是否超过30天
     *
     * @param map
     * @return
     */
    List<Interview> getInterviewsAmongThirty(Map<String, Object> map);


}
