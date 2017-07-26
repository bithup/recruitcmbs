package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.Interview;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IInterviewDaoR {

    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    public Interview getLastInterview(Map<String, Object> map);

    public Interview get(long id);

    /**
     * 获取企业已经招收的人数
     *
     * @param map
     * @return
     */
    int getEnrollNum(Map<String, Object> map);

    /**
     * 查询求职者面试邀请记录
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getInterviewsByMemberId(Map<String, Object> map);

    /**
     * 企业给求职者发送面试邀请是否超过30天
     *
     * @param map
     * @return
     */
    List<Interview> getInterviewsAmongThirty(Map<String, Object> map);


}
