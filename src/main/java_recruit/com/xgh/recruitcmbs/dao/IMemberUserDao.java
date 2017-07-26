package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.MemberUser;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IMemberUserDao {

    public MemberUser get(long id);

    /**
     * 手机号是否已经被注册
     * @param account
     * @return
     */
    public List<MemberUser> isAlreadyRegister(String account);

    public Map<String, Object> memberIndexInfo(Map<String, Object> map);

    int add(MemberUser memberUser);

    int update(MemberUser memberUser);
}
