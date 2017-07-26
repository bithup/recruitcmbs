package com.xgh.recruitcmbs.dao.read;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IIndexAdvertDaoR {

    /**
     * 首页推荐的公司
     * @return
     */
    List<Map<String,Object>> getIndexAdverts(Map<String,Object> map);
}
