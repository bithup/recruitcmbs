package com.xgh.recruitcmbs.services;

import java.util.List;
import java.util.Map;

import com.xgh.recruitcmbs.entity.Position;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IPositionService {

    List<Map<String, Object>> getIndexSearch(Map<String, Object> map);


    /**
     * 查询职位
     *
     * @param id
     * @return
     */
    public Map<String, Object> getPositionInfo(long id);


    /**
     * 查询该公司职位列表
     *
     * @param map
     * @return
     */
    public List<Position> getPositionList(Map<String, Object> map);


    public int save(HttpServletRequest request, Position position);


    public int update(HttpServletRequest request, Position position);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int batchUpdateByIdList(List<String> list);


    /**
     * 职位管理失效列表
     *
     * @param map
     * @return
     */
    public List<Position> getExpiryPositionList(Map<String, Object> map);


    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    public int batchDeleteByIdList(List<String> list);


    /**
     * @param map
     * @return
     */
    public int batchUpdateMap(Map<String, Object> map);


    /**
     * @param id
     * @return
     */
    public Position get(long id);


    /**
     * 根据条件分页查询职位列表
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getListPage(Map<String, Object> map);


    /**
     * 根据企业id查询发布的职位
     *
     * @param map
     * @return
     */
    List<Position> getByCompanyId(Map<String, Object> map);


    /**
     * 求职版筛选职位
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> screenPosition(Map<String, Object> map);

    /**
     * @param map
     * @return
     */
    public List<Position> getJobKindsNameByCompanyId(Map<String, Object> map);

}
