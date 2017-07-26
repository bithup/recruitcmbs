package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.MemberUser;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IMemberUserDaoW {

    int add(MemberUser memberUser);

    int update(MemberUser memberUser);
}
