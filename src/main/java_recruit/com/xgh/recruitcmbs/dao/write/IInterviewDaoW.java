package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.Interview;
import com.xgh.recruitcmbs.entity.StoreTalents;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IInterviewDaoW {


    /**
     * 企业发送面试邀请
     * @param interview
     * @return
     */
    public int add(Interview interview);

    /**
     * 修改面试邀请的邀请状态
     * @param interview
     * @return
     */
    public int update(Interview interview);

    /**
     * 企业删除面试邀请
     * @param ids
     * @return
     */
    public int updateBatch(String[] ids);

    /**
     * 企业清除面试邀请记录
     * @param companyId
     * @return
     */
    public int clearOutByCompanyId(String companyId);

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
