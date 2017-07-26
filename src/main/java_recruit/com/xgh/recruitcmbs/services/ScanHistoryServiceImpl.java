package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IScanHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("scanHistoryService")
public class ScanHistoryServiceImpl implements IScanHistoryService {

    @Autowired
    protected IScanHistoryDao scanHistoryDao;

}
