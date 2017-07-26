package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.ResumeDelivery;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IResumeDeliveryDaoW {

    /**
     * 添加
     *
     * @param resumeDelivery
     * @return
     */
    public int add(ResumeDelivery resumeDelivery);


    /**
     * 修改
     *
     * @param resumeDelivery
     * @return
     */
    public int update(ResumeDelivery resumeDelivery);

    /**
     * 批量投递简历
     *
     * @param resumeDelivery
     * @return
     */
    public int addBatch(List<ResumeDelivery> resumeDelivery);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public int batchDeleteByIdList(List<String> list);


}
