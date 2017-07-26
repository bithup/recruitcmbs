package com.xgh.recruitcmbs.dao.read;


import com.xgh.recruitcmbs.entity.TransactionRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface ITransactionRecordDaoR {

    /**
     * bsx
     * 根据id查询交易记录
     *
     * @param id
     * @return
     */
    public TransactionRecord get(long id);


    public List<TransactionRecord> getTransactionList(Map<String,Object> map);

}
