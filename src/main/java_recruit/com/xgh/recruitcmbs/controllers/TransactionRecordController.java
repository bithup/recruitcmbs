package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.TransactionRecord;
import com.xgh.recruitcmbs.services.ITransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("recruitcmbs/transaction/")
public class TransactionRecordController extends BaseController{

    @Autowired
    protected ITransactionRecordService transactionRecordService;


    /**
     * 获取交易记录
     */
    @RequestMapping(value="getTransactionList")
    public void getTransactionList(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String  memberId = request.getParameter("dataId");
        String  memberType = request.getParameter("dataType");
        map.put("memberId",memberId);
        map.put("memberType",memberType);
        List<TransactionRecord> list = transactionRecordService.getTransactionList(map);

        List<Map<String,Object>> list_ = new ArrayList<Map<String, Object>>();
        for(TransactionRecord transactionRecord:list){
            Map<String,Object> transMap = new HashMap<String, Object>();
            transMap.put("id",transactionRecord.getId());
            transMap.put("memberId",transactionRecord.getMemberId());

            //用户类型
            if(transactionRecord.getMemberType()==1){
                transMap.put("memberType","用户");
            }else if(transactionRecord.getMemberType()==2){
                transMap.put("memberType","企业");
            }

            //交易类型
            if(transactionRecord.getDealType()==1){
                transMap.put("dealType","支出");
            }else if(transactionRecord.getDealType()==2){
                transMap.put("dealType","收入");
            }

            //交易来源
            if(transactionRecord.getSources()==1){
                transMap.put("sources","发红包");
            }else if(transactionRecord.getSources()==2){
                transMap.put("sources","收红包");
            }else if(transactionRecord.getSources()==3){
                transMap.put("sources","提现");
            }else if(transactionRecord.getSources()==4){
                transMap.put("sources","充值");
            }

            transMap.put("dealMoney",transactionRecord.getDealMoney());
            transMap.put("dealTime",transactionRecord.getDealTime());
            transMap.put("createDate",transactionRecord.getCreateDate());

            list_.add(transMap);
        }

        if(list.size()>0){
            resultMap = getResultMap("1","获取明细成功！",list_);
        }else{
            resultMap = getResultMap("0","暂无数据！");
        }
        outJson(resultMap);
    }
}
