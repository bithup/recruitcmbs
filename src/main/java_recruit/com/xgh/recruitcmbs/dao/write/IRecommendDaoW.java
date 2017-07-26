package com.xgh.recruitcmbs.dao.write;


import com.xgh.recruitcmbs.entity.Recommend;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 */
public interface IRecommendDaoW {
    /**
     * 添加反馈
     * @param recommend
     * @return
     */
     public int add(Recommend recommend);


    public int delete(long id);

    public int update(Recommend recommend);
}
