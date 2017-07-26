package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.basic.BaseService;
import com.xgh.recruitcmbs.dao.ICompanyInfoDao;
import com.xgh.recruitcmbs.dao.IFundAccountDao;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.FundAccount;
import com.xgh.recruitcmbs.entity.ShortMessage;
import com.xgh.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2017/3/13.
 */
@Service
public class CompanyInfoServicesImpl extends BaseService implements ICompanyInfoServices {

    @Autowired
    protected ICompanyInfoDao companyInfoDao;

    @Autowired
    protected IFundAccountDao fundAccountDao;

    @Autowired
    protected IShortMessageService shortMessageService;

    public CompanyInfo get(long id) {
        return companyInfoDao.get(id);
    }


    public CompanyInfo login(Map<String, Object> map) {
        return companyInfoDao.login(map);
    }

    public int getCompanyInfoByAccount(String account) {
        return companyInfoDao.getCompanyInfoByAccount(account);
    }

    public Map<String, Object> register(HttpServletRequest request, CompanyInfo companyInfo, String validationCode) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String mobile_ = request.getSession().getAttribute("mobile") + "";
        String validationCode_ = request.getSession().getAttribute("validationCode") + "";

        if (!validationCode.equals(validationCode_)) {

            resultMap = getResultMap("-1", "输入的短信验证码不正确!");
            return resultMap;
        }
        if (!mobile_.equals(companyInfo.getMobile())) {

            resultMap = getResultMap("-1", "输入的手机号和绑定的手机短信验证码手机号不一致!");
            return resultMap;
        }
        String password = companyInfo.getPassword();
        String password_ = MD5Util.getMD5(password);

        companyInfo.setPassword(password_);
        companyInfo.setCreateDate(new Date());
        companyInfo.setUpdateDate(new Date());
        companyInfo.setIsCheck(0);
        companyInfo.setIsCertify(2);
        companyInfo.setStatus(1);

        int flag = companyInfoDao.add(companyInfo);

        FundAccount fundAccount = new FundAccount();

        fundAccount.setDataId(companyInfo.getId());
        fundAccount.setDataType(2);//1用户；2企业
        fundAccount.setPurseBalance(0.00d);
        fundAccount.setCreateDate(new Date());
        fundAccount.setUpdateDate(new Date());
        fundAccount.setStatus(1);

        int flg = fundAccountDao.add(fundAccount);

        if (flag > 0 && flg > 0) {
            resultMap = getResultMap("1", "企业注册成功!", companyInfo);
        }
        return resultMap;
    }

    public int getCompanyInfoByMobile(String mobile) {
        return companyInfoDao.getCompanyInfoByMobile(mobile);
    }

    public CompanyInfo findPassword(Map<String, Object> map) {
        return companyInfoDao.findPassword(map);
    }

    public int update(HttpServletRequest request, CompanyInfo companyInfo, ShortMessage shortMessage) {

        request.getSession().setAttribute("mobile", shortMessage.getPhoneNum());
        request.getSession().setAttribute("validationCode", shortMessage.getData1());
        shortMessageService.add(shortMessage);//短信记录表存储
        return companyInfoDao.update(companyInfo);

    }

    public int update(CompanyInfo companyInfo) {
        companyInfoDao.update(companyInfo);
        return 1;
    }

    public List<CompanyInfo> getListPage(Map<String, Object> map) {
        return companyInfoDao.getListPage(map);
    }
}
