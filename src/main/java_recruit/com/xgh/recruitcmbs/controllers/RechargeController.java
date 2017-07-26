package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Recharge;
import com.xgh.recruitcmbs.services.IRechargeService;
import com.xgh.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BSX on 2017/4/18.
 */
@Controller
@Scope("prototype")
@RequestMapping("recruitcmbs/recharge/")
public class RechargeController extends BaseController {

    @Autowired
    protected IRechargeService rechargeService;

    /**
     * 充值
     */
    @RequestMapping(value="addRecharge")
    public void addRecharge(@ModelAttribute Recharge recharge){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Date date = new Date();
        recharge.setMemberType(2);
        recharge.setOrderNo(OrderUtil.getOrderNo());
        recharge.setPayStatus(0);
        recharge.setCreateDate(date);
        recharge.setUpdateDate(date);
        recharge.setStatus(1);
        int flag = rechargeService.add(recharge);
        if(flag>0){
            resultMap  = getResultMap("1","生成充值记录成功",recharge.getId());
        }else{
            resultMap = getResultMap("0","生成充值记录失败，请重试");
        }
        outJson(resultMap);
    }
}
