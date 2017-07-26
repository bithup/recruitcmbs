package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.Industry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IIndustryDao {

    public List<Industry> getIndustryList();
}
