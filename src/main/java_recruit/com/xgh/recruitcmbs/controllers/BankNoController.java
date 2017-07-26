package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IBankNoDao;
import com.xgh.recruitcmbs.services.IBankNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CQ on 2016/11/14.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/bankNo/")
public class BankNoController extends BaseController {

    @Autowired
    protected IBankNoService bankNoService;

    @Autowired
    protected IBankNoDao bankNoDao;


    /**
     * 显示绑定银行卡列表
     */
    @RequestMapping(value = "getBankNoByMemId")
    public void getBankNoByMemId() {

        outJson(bankNoService.getBankNoByMemId(request));

    }


    /**
     * 默认绑定银行卡
     */
    @RequestMapping(value = "setDefaultBankNo")
    public void setDefaultBankNo() {

        outJson(bankNoService.getDefaultBankNo(request));

    }


    /**
     * 解除银行卡绑定
     */
    @RequestMapping(value = "unbindBanking")
    public void unbindBanking() {

        outJson(bankNoService.unbindBanking(request));
    }


}
