package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.JobObjective;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IJobObjectiveDaoW {

    int add(JobObjective jobObjective);

    int update(JobObjective jobObjective);
}
