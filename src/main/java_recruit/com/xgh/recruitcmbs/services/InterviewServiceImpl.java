package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.basic.BaseService;
import com.xgh.recruitcmbs.dao.IFundAccountDao;
import com.xgh.recruitcmbs.dao.IInterviewDao;
import com.xgh.recruitcmbs.dao.ITransactionRecordDao;
import com.xgh.recruitcmbs.dao.read.IInterviewDaoR;
import com.xgh.recruitcmbs.dao.write.IInterviewDaoW;
import com.xgh.recruitcmbs.entity.FundAccount;
import com.xgh.recruitcmbs.entity.Interview;
import com.xgh.recruitcmbs.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("interviewService")
public class InterviewServiceImpl extends BaseService implements IInterviewService {

    @Autowired
    protected IInterviewDao interviewDao;

    @Autowired
    protected ITransactionRecordDao transactionRecordDao;

    @Autowired
    protected IFundAccountDao fundAccountDao;


    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return interviewDao.getListPage(map);
    }

    public int add(Interview interview) {
        return interviewDao.add(interview);
    }

    public int update(Interview interview) {
        return interviewDao.update(interview);
    }

    public int updateBatch(String[] ids) {
        return interviewDao.updateBatch(ids);
    }

    public int clearOutByCompanyId(String companyId) {
        return interviewDao.clearOutByCompanyId(companyId);
    }

    public Interview getLastInterview(Map<String, Object> map) {
        return interviewDao.getLastInterview(map);
    }

    public Interview get(long id) {
        return interviewDao.get(id);
    }

    /**
     * 企业版转正
     *
     * @param request
     * @return
     */
    public Map<String, Object> updateRegular(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String id = request.getParameter("id");
        Interview interview = interviewDao.get(Long.parseLong(id));

        interview.getCompanyId();//企业id

        Map<String, Object> map_ = new HashMap<String, Object>();
        map_.put("dataId", interview.getCompanyId());
        map_.put("dataType", "2");
        FundAccount fundAccounts = fundAccountDao.getWalletBalance(map_);
        String password = fundAccounts.getPayPassword();
        if (password == null || "".equals(password)) {
            resultMap = getResultMap("-2", "");
            return resultMap;
        }

        if (interview.getData7() == 3) {
            resultMap = getResultMap("-2", "已转正，不能重复操作！");
        } else {
            long companyId = interview.getCompanyId();
            long memberId = interview.getMemberId();
            Date date = new Date();

            //修改企业钱包余额
            map.put("dataId", companyId);
            map.put("dataType", 2);
            FundAccount fundAccount = fundAccountDao.getWalletBalance(map);
            if (fundAccount.getPurseBalance() >= 300) {
                fundAccount.setPurseBalance(fundAccount.getPurseBalance() - 300);
                fundAccount.setUpdateDate(date);
                fundAccountDao.update(fundAccount);

                //添加一条企业的交易记录
                TransactionRecord transactionRecord = new TransactionRecord();
                transactionRecord.setInstCode("recruit");
                transactionRecord.setMemberId(companyId);
                transactionRecord.setMemberType(2);
                transactionRecord.setDealType(1);
                transactionRecord.setDealMoney(300d);
                transactionRecord.setDealTime(date);
                transactionRecord.setSources(1);
                transactionRecord.setCreateDate(date);
                transactionRecord.setUpdateDate(date);
                transactionRecord.setStatus(1);
                transactionRecordDao.add(transactionRecord);

                //修改求职者钱包余额
                map.put("dataId", memberId);
                map.put("dataType", 1);
                FundAccount fundAccount_ = fundAccountDao.getWalletBalance(map);
                fundAccount_.setPurseBalance(fundAccount_.getPurseBalance() + 300);
                fundAccount_.setUpdateDate(date);
                fundAccountDao.update(fundAccount_);

                //添加一条求职者的交易记录
                transactionRecord.setMemberId(memberId);
                transactionRecord.setMemberType(1);
                transactionRecord.setDealType(2);
                transactionRecord.setDealMoney(300d);
                transactionRecord.setDealTime(date);
                transactionRecord.setSources(2);
                transactionRecord.setCreateDate(date);
                transactionRecord.setUpdateDate(date);
                transactionRecord.setStatus(1);
                transactionRecordDao.add(transactionRecord);

                //修改面试邀请状态
                interview.setUpdateDate(date);
                interview.setData7(3);
                int flag = interviewDao.update(interview);
                if (flag > 0) {
                    resultMap = getResultMap("1", "操作成功！");
                } else {
                    resultMap = getResultMap("0", "操作失败！");
                }
            } else {
                resultMap = getResultMap("-1", "钱包余额不足，请先充值！");
            }
        }
        return resultMap;
    }

    public int getEnrollNum(Map<String, Object> map) {
        return interviewDao.getEnrollNum(map);
    }

    public List<Map<String, Object>> getInterviewsByMemberId(Map<String, Object> map) {
        return interviewDao.getInterviewsByMemberId(map);
    }

    public int deleteMemberInterviews(String[] ids) {
        return interviewDao.deleteMemberInterviews(ids);
    }

    public int clearOutByMemberId(String memberId) {
        return interviewDao.clearOutByMemberId(memberId);
    }


}
