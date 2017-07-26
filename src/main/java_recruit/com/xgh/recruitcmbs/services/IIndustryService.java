package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IIndustryDao;
import com.xgh.recruitcmbs.entity.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IIndustryService {

    public List<Industry> getIndustryList();
}
