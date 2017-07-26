package com.xgh.recruitcmbs.dao;


import com.xgh.recruitcmbs.entity.TransactionRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface ITransactionRecordDao {

    /**
     * bsx
     * 根据id查询交易记录
     *
     * @param id
     * @return
     */
    public TransactionRecord get(long id);

    /**
     * bsx
     * 插入一条交易记录
     *
     * @param transactionRecord
     * @return
     */
    public int add(TransactionRecord transactionRecord);


    /**
     * bsx
     * 修改一条交易记录
     *
     * @param transactionRecord
     * @return
     */
    public int update(TransactionRecord transactionRecord);


    public List<TransactionRecord> getTransactionList(Map<String,Object> map);

}
