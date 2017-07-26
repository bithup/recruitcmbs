package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IMemberUserDao;
import com.xgh.recruitcmbs.entity.MemberUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IMemberUserService {

    /**
     * 手机号是否已经被注册
     * @param account
     * @return
     */
    public List<MemberUser> isAlreadyRegister(String account);


    public MemberUser get(long id);

    public Map<String, Object> memberIndexInfo(Map<String, Object> map);

    int add(MemberUser memberUser);

    int update(MemberUser memberUser);
}
