package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IIndustryDaoR;
import com.xgh.recruitcmbs.dao.write.IIndustryDaoW;
import com.xgh.recruitcmbs.entity.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("industryDao")
public class IndustryDaoImpl implements IIndustryDao {

    @Autowired
    protected IIndustryDaoR industryDaoR;

    @Autowired
    protected IIndustryDaoW industryDaoW;

    public List<Industry> getIndustryList() {
        return industryDaoR.getIndustryList();
    }
}
