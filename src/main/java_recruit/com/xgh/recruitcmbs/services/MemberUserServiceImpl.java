package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.*;
import com.xgh.recruitcmbs.entity.*;
import com.xgh.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("memberUserService")
public class MemberUserServiceImpl implements IMemberUserService {

    @Autowired
    protected IMemberUserDao memberUserDao;

    @Autowired
    protected IResumeDao resumeDao;

    @Autowired
    protected IJobObjectiveDao jobObjectiveDao;

    @Autowired
    protected IFundAccountDao fundAccountDao;

    @Autowired
    protected ITotalMemberUserDao totalMemberUserDao;



    public List<MemberUser> isAlreadyRegister(String account) {
        return memberUserDao.isAlreadyRegister(account);
    }

    public MemberUser get(long id) {
        return memberUserDao.get(id);
    }

    public Map<String, Object> memberIndexInfo(Map<String, Object> map) {
        return memberUserDao.memberIndexInfo(map);
    }

    public int add(MemberUser memberUser) {

/*
        TotalMemebrUser totalMemebrUser_ = totalMemberUserDao.login(memberUser.getAccount());

        TotalMemebrUser totalMemebrUser = new TotalMemebrUser();
        if (totalMemebrUser_ == null) {

            totalMemebrUser.setAccount(memberUser.getAccount());
            totalMemebrUser.setPassword(memberUser.getPassword());
            totalMemebrUser.setType(5);//从招聘平台注册的
            totalMemebrUser.setCreateDate(new Date());
            totalMemebrUser.setUpdateDate(new Date());
            totalMemebrUser.setStatus(1);

            totalMemberUserDao.add(totalMemebrUser);//先注册到总表里面
        }*/


        /**
         * 添加一条用户信息
         */
        Date date = new Date();
        memberUser.setCreateDate(date);
        memberUser.setUpdateDate(date);
        memberUser.setIsOpen(1);
        memberUser.setStatus(1);
      /*  memberUser.setData1(totalMemebrUser.getId());//总表id*/
        memberUserDao.add(memberUser);


        /**
         * 添加一条简历信息
         */
        Resume resume = new Resume();
        resume.setMemberId(memberUser.getId());
        resume.setIsScreen(1);
        resume.setIsDefault(1);
        resume.setIntegrity("0");
        resume.setCreateDate(date);
        resume.setUpdateDate(date);
        resume.setStatus(1);
        resumeDao.insert(resume);

        /**
         * 添加一条求职意向
         */
        JobObjective jobObjective = new JobObjective();
        jobObjective.setResumeId(resume.getId());
        jobObjective.setCreateDate(date);
        jobObjective.setUpdateDate(date);
        jobObjective.setStatus(1);
        jobObjectiveDao.add(jobObjective);

        /**
         * 添加一条资金账户信息
         */
        FundAccount fundAccount = new FundAccount();
        fundAccount.setDataId(memberUser.getId());
        fundAccount.setDataType(1);
        fundAccount.setPurseBalance(0d);
        fundAccount.setCreateDate(date);
        fundAccount.setUpdateDate(date);
        fundAccount.setStatus(1);

        int flag = fundAccountDao.add(fundAccount);

        return flag;
    }

    public int update(MemberUser memberUser) {
        return memberUserDao.update(memberUser);
    }
}
