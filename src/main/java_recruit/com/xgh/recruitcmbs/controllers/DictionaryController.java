package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.services.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by BSX on 2017/3/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("/recruitcmbs/dictionary/")
public class DictionaryController extends BaseController {

    @Autowired
    protected IDictionaryService dictionaryService;


    /**
     * 根据item查询键值对列表，前端生成下拉框
     */
    @RequestMapping(value = "getItemList")
    public void getItemList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String items = request.getParameter("item");
        String item[] = items.split(",");
        List<String> list_ = Arrays.asList(item);

        List<Map<String, Object>> listPost_ = new ArrayList<Map<String, Object>>();

        if (list_ != null && list_.size() > 0) {

            for (String dic : list_) {
                Map<String, Object> map_ = new HashMap<String, Object>();

                map_.put("item", dic);
                List<Map<String, Object>> list = dictionaryService.getKeyValue(map_);

                Map<String, Object> map1 = new LinkedHashMap();
                map1.put("type", dic);
                map1.put("list", list);

                listPost_.add(map1);
            }
        }
        if (listPost_.size() > 0) {
            resultMap = getResultMap("1", "获取字典数据成功！", listPost_);
        } else {
            resultMap = getResultMap("0", "没有数据！");
        }
        outJson(resultMap);
    }

    /**
     * 根据字典表查询(单个)
     */
    @RequestMapping(value = "getItemByCode")
    public void getItemByCode() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String item = request.getParameter("item");

        Map<String, Object> map_ = new HashMap<String, Object>();
        map_.put("item", item);

        List<Map<String, Object>> list = dictionaryService.getKeyValue(map_);


        resultMap = getResultMap("1", "列表显示成功", list);
        outJson(resultMap);

    }


}
