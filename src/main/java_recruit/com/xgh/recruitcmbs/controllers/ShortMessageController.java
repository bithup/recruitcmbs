package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.ShortMessage;
import com.xgh.recruitcmbs.services.ICompanyInfoServices;
import com.xgh.recruitcmbs.services.IShortMessageService;
import com.xgh.recruitcmbs.util.ShortMessageUtil;
import com.xgh.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CQ on 2017/3/17.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/shortMessage/")
public class ShortMessageController extends BaseController {

    @Autowired
    protected IShortMessageService shortMessageService;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    /**
     * 调用短信验证接口
     */

    @RequestMapping(value = "sendMessage")
    public void sendMessage() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);

        ShortMessageUtil shortMessageUtil = new ShortMessageUtil();
        if (!"".equals(mobile)) {
            ShortMessage shortMessage = shortMessageUtil.sendMessage(mobile, "", type);

            if (shortMessage != null) {

                request.getSession().setAttribute("mobile", shortMessage.getPhoneNum());
                request.getSession().setAttribute("validationCode", shortMessage.getData1());
                int flag = shortMessageService.add(shortMessage);
                if (flag > 0) {
                    resultMap = getResultMap("1", "发送短信验证码成功!");
                }
            } else {
                resultMap = getResultMap("-1", "发送短信验证码失败!");
            }
        } else {
            getResultMap("-1", "请输入绑定手机号!");
        }
        outJson(resultMap);
    }

    /**
     * 调用找回密码接口
     */
    @RequestMapping(value = "findPassword")
    public void findPassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String mobile = request.getParameter("mobile");//绑定手机号
        String type = request.getParameter("type");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        CompanyInfo companyInfo = companyInfoServices.findPassword(map);

        ShortMessageUtil shortMessageUtil = new ShortMessageUtil();
        if (!"".equals(mobile)) {
            ShortMessage shortMessage = shortMessageUtil.sendMessage(mobile, companyInfo.getAccount(), type);

            if (shortMessage != null) {

                if (companyInfo != null) {
                    companyInfo.setPassword(MD5Util.getMD5("123456"));
                    companyInfo.setUpdateDate(new Date());
                } else {
                    resultMap = getResultMap("-1", "该绑定手机号不存在!");
                    outJson(resultMap);
                    return;
                }

                int flag = companyInfoServices.update(request, companyInfo, shortMessage);
                if (flag > 0) {
                    resultMap = getResultMap("1", "成功找回用户名和密码!");
                }
            } else {
                resultMap = getResultMap("-1", "发送短信验证码失败!");
            }

        } else
            getResultMap("-1", "请输入绑定手机号!");
        outJson(resultMap);

    }

}
