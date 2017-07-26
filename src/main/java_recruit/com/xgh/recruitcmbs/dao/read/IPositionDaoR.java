package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.Position;
import javafx.geometry.Pos;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IPositionDaoR {

    List<Map<String, Object>> getIndexSearch(Map<String, Object> map);


    /**
     * @param id
     * @return
     */
    public Position get(long id);

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

    /**
     * 职位管理失效列表
     *
     * @param map
     * @return
     */
    public List<Position> getExpiryPositionList(Map<String, Object> map);

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
