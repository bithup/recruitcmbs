package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.Resume;



/**
 * Created by BSX on 2017/3/13.
 */
public interface IResumeDaoR {

    Resume get(long id);

    Resume getByMemberId(long memberId);



}
