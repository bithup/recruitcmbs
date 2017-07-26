package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.Interview;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IInterviewService {

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int add(Interview interview);

    public int update(Interview interview);

    public int updateBatch(String[] ids);

    public int clearOutByCompanyId(String companyId);

    public Interview getLastInterview(Map<String,Object> map);

    public Interview get(long id);

    public Map<String,Object> updateRegular(HttpServletRequest request);

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
}
