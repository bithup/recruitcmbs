package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.basic.BaseService;
import com.xgh.recruitcmbs.dao.ICompanyInfoDao;
import com.xgh.recruitcmbs.dao.IShieldCompanyDao;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.ShieldCompany;
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
@Service("shieldCompanyService")
public class ShieldCompanyServiceImpl extends BaseService implements IShieldCompanyService {

    @Autowired
    protected IShieldCompanyDao shieldCompanyDao;

    @Autowired
    protected ICompanyInfoDao companyInfoDao;


    public Map<String, Object> add(ShieldCompany shieldCompany) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<CompanyInfo> companyInfoList = null;
        String companyName = shieldCompany.getData3();//企业姓名
        if (!"".equals(companyName)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyName", companyName);

            companyInfoList = companyInfoDao.getCompanyInfoByName(map);
        }

        if (companyInfoList != null && companyInfoList.size() > 0) {

            CompanyInfo companyInfo = companyInfoList.get(0);

            shieldCompany.setCompanyId(companyInfo.getId());

            Date date = new Date();
            shieldCompany.setCreateDate(date);
            shieldCompany.setUpdateDate(date);
            shieldCompany.setStatus(1);
            int flag = shieldCompanyDao.add(shieldCompany);
            if (flag > 0) {
                resultMap = getResultMap("1", "添加成功！");
            } else {
                resultMap = getResultMap("0", "添加失败，请重试");
            }
        } else {
            resultMap = getResultMap("0", "该公司不存在,无法屏蔽！");
        }

        return resultMap;
    }

    public Map<String, Object> update(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        ShieldCompany shieldCompany = shieldCompanyDao.get(Long.parseLong(id));
        shieldCompany.setStatus(-1);
        shieldCompany.setUpdateDate(new Date());
        int flag = shieldCompanyDao.update(shieldCompany);
        if (flag > 0) {
            resultMap = getResultMap("1", "操作成功");
        } else {
            resultMap = getResultMap("0", "操作失败，请重试");
        }
        return resultMap;
    }

    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return shieldCompanyDao.getList(map);
    }

    public ShieldCompany get(long id) {
        return shieldCompanyDao.get(id);
    }
}
