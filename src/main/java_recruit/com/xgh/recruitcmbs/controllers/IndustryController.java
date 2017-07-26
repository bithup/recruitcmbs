package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Industry;
import com.xgh.recruitcmbs.services.IIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/20.
 */
@Controller
@Scope("prototype")
@RequestMapping("/recruitcmbs/industry")
public class IndustryController extends BaseController {

    @Autowired
    protected IIndustryService industryService;


    /**
     * 获得行业列表
     */
    @RequestMapping(value="getIndustryList")
    public void getIndustryList(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Industry> list = industryService.getIndustryList();
        if(list.size()>0){
            resultMap = getResultMap("1","获取行业列表成功！",list);
        }else{
            resultMap = getResultMap("0","暂无数据！");
        }
        outJson(resultMap);
    }


}
