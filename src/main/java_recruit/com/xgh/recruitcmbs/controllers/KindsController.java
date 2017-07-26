package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Kinds;
import com.xgh.recruitcmbs.entity.Position;
import com.xgh.recruitcmbs.services.IKindsService;
import com.xgh.recruitcmbs.services.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/14.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/kinds/")
public class KindsController extends BaseController {

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IPositionService positionService;


    @RequestMapping(value = "topIndexKinds")
    public void topIndexKinds() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
/*        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");

        Map<String, Object> map = new HashMap<String, Object>();
        if (page != null && !"".equals(page)) {
            map.put("page", Integer.parseInt(page));
        }
        if (pageSize != null && !"".equals(pageSize)) {
            map.put("pageSize", Integer.parseInt(pageSize));
        }*/

        String companyId = request.getParameter("companyId");

        Map<String, Object> map_ = new HashMap<String, Object>();
        map_.put("companyId", companyId);
        List<Position> positionList = positionService.getJobKindsNameByCompanyId(map_);


  /*      List<Map<String, Object>> kindsList = kindsService.getThirdLevel(map);*/
        if (positionList != null && positionList.size() > 0) {
            resultMap = getResultMap("1", "获取首页职位列表成功!", positionList);
        } else {
            resultMap = getResultMap("0", "获取首页职位列表失败!");
        }

        outJson(resultMap);
    }


    /**
     * 职位三级分类
     */
    @RequestMapping(value = "getByPid")
    public void getByPid() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String parentId = request.getParameter("parentId");
        List<Map<String, Object>> list = kindsService.getByPid(Long.parseLong(parentId));
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取职位分类成功！", list);
        } else {
            resultMap = getResultMap("0", "已经是最后一级了");
        }
        outJson(resultMap);
    }


}
