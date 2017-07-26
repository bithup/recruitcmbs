package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IMemberUserDaoR;
import com.xgh.recruitcmbs.dao.write.IMemberUserDaoW;
import com.xgh.recruitcmbs.entity.MemberUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("memberUserDao")
public class MemberUserDaoImpl implements IMemberUserDao {

    @Autowired
    protected IMemberUserDaoR memberUserDaoR;

    @Autowired
    protected IMemberUserDaoW memberUserDaoW;


    public Map<String, Object> memberIndexInfo(Map<String, Object> map) {
        return memberUserDaoR.memberIndexInfo(map);
    }

    public MemberUser get(long id) {
        return memberUserDaoR.get(id);
    }

    public List<MemberUser> isAlreadyRegister(String account) {
        return memberUserDaoR.isAlreadyRegister(account);
    }

    public int add(MemberUser memberUser) {
        return memberUserDaoW.add(memberUser);
    }

    public int update(MemberUser memberUser) {
        return memberUserDaoW.update(memberUser);
    }
}
