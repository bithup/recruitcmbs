package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IShieldCompanyDao;
import com.xgh.recruitcmbs.entity.ShieldCompany;
import com.xgh.recruitcmbs.services.IShieldCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/4/10.
 */
@Controller
@Scope("prototype")
@RequestMapping("recruitcmbs/shieldCompany")
public class ShieldCompanyController extends BaseController{

    @Autowired
    protected IShieldCompanyService shieldCompanyService;


    /**
     *屏蔽一家公司
     * @param shieldCompany
     */
    @RequestMapping(value="addShieldCompany")
    public void addShieldCompany(@ModelAttribute ShieldCompany shieldCompany){
        outJson(shieldCompanyService.add(shieldCompany));
    }


    /**
     * 获取屏蔽过的公司
     */
    @RequestMapping(value="getShieldList")
    public void getShieldList(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("memberId",memberId);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = shieldCompanyService.getList(map);
        if(list.size()>0){
            resultMap = getResultMap("1","获取屏蔽公司成功",list);
        }else{
            resultMap = getResultMap("0","暂无数据");
        }
        outJson(resultMap);
    }

    /**
     * 删除一条屏蔽记录
     */
    @RequestMapping(value="deleteShieldCompany")
    public void deleteShieldCompany(){
        outJson(shieldCompanyService.update(request));
    }
}
