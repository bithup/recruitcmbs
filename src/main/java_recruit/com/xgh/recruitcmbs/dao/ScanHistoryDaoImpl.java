package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IScanHistoryDaoR;
import com.xgh.recruitcmbs.dao.write.IScanHistoryDaoW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("scanHistoryDao")
public class ScanHistoryDaoImpl implements IScanHistoryDao {

    @Autowired
    protected IScanHistoryDaoR scanHistoryDaoR;

    @Autowired
    protected IScanHistoryDaoW scanHistoryDaoW;
}
