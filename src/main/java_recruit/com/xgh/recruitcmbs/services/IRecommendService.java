package com.xgh.recruitcmbs.services;




import com.xgh.recruitcmbs.entity.Recommend;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 */
public interface IRecommendService {
    /**
     * 获取列表数据
     * @param postMap
     * @return
     */
    public List<Map<String,Object>> getRecommendList(Map<String, Object> postMap);
    /**
     * 添加反馈
     * @param recommend
     * @return
     */
    public Integer add(Recommend recommend);
}
