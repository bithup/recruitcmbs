package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IIndustryDao;
import com.xgh.recruitcmbs.dao.read.IIndustryDaoR;
import com.xgh.recruitcmbs.dao.write.IIndustryDaoW;
import com.xgh.recruitcmbs.entity.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("industryService")
public class IndustryServiceImpl implements IIndustryService {

    @Autowired
    protected IIndustryDao industryDao;

    public List<Industry> getIndustryList() {
        return industryDao.getIndustryList();
    }
}
