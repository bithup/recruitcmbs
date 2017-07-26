package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.ITransactionRecordDao;
import com.xgh.recruitcmbs.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("transactionRecordService")
public class TransactionRecordServiceImpl implements ITransactionRecordService {

    @Autowired
    protected ITransactionRecordDao transactionRecordDao;

    public TransactionRecord get(long id) {
        return transactionRecordDao.get(id);
    }

    public int insert(TransactionRecord transactionRecord) {
        return transactionRecordDao.add(transactionRecord);
    }

    public int update(TransactionRecord transactionRecord) {
        return transactionRecordDao.update(transactionRecord);
    }

    public List<TransactionRecord> getTransactionList(Map<String, Object> map) {
        return transactionRecordDao.getTransactionList(map);
    }
}
