package com.xgh.recruitcmbs.dao.write;


import com.xgh.recruitcmbs.entity.TransactionRecord;

/**
 * Created by BSX on 2017/3/14.
 */
public interface ITransactionRecordDaoW {

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

}
