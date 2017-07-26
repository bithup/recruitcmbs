package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.ITransactionRecordDaoR;
import com.xgh.recruitcmbs.dao.write.ITransactionRecordDaoW;
import com.xgh.recruitcmbs.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("transactionRecordDao")
public class TransactionRecordDaoImpl implements ITransactionRecordDao {

    @Autowired
    protected ITransactionRecordDaoR transactionRecordDaoR;

    @Autowired
    protected ITransactionRecordDaoW transactionRecordDaoW;

    public TransactionRecord get(long id) {
        return transactionRecordDaoR.get(id);
    }

    public int add(TransactionRecord transactionRecord) {
        return transactionRecordDaoW.add(transactionRecord);
    }

    public int update(TransactionRecord transactionRecord) {
        return transactionRecordDaoW.update(transactionRecord);
    }

    public List<TransactionRecord> getTransactionList(Map<String, Object> map) {
        return transactionRecordDaoR.getTransactionList(map);
    }
}
