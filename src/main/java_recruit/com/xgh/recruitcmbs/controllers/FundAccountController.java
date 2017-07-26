package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.FundAccount;
import com.xgh.recruitcmbs.services.IFundAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BSX on 2017/3/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("recruitcmbs/fundAccount/")
public class FundAccountController extends BaseController {

    @Autowired
    protected IFundAccountService fundAccountService;


    /**
     * 获得钱包余额
     */
    @RequestMapping(value="getWalletBalance")
    public void getWalletBalance(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String dataId = request.getParameter("dataId");
        String dataType = request.getParameter("dataType");
        map.put("dataId",dataId);
        map.put("dataType",dataType);
        FundAccount fundAccount = fundAccountService.getWalletBalance(map);
        if(fundAccount!=null){
            resultMap = getResultMap("1","获取钱包信息成功！",fundAccount);
        }else{
            resultMap = getResultMap("0","获取钱包信息失败！");
        }
        outJson(resultMap);
    }
}
