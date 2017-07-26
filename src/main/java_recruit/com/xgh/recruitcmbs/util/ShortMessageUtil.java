package com.xgh.recruitcmbs.util;


import com.xgh.recruitcmbs.entity.ShortMessage;
import com.xgh.recruitcmbs.services.ICompanyInfoServices;
import com.xgh.recruitcmbs.services.IShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/12/28.
 */
public class ShortMessageUtil {

    @Autowired
    protected IShortMessageService shortMessageService;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    public ShortMessage sendMessage(String telPhone,String account, String type) {

        String validationCode = ValidationCode.getSecurityCode(5, 3);
        String content;

        if ("0".equals(type)) {
            content = "短信验证码为：" + validationCode + "！请对本条短信保密，千万不要让他人看到哦！";
        } else {
            content = "您已成功找回密码，账号为：" + account + ",默认密码：123456。为确保您的账号安全，请尽快修改密码！请对本条短信保密，千万不要让他人看到哦！";
        }

        String sendUrl = ConstantUtil.SEND_URL;
        System.out.println(sendUrl);
        String sendUrl1 = sendUrl.replace("{用户名}", URLEncoder.encode(ConstantUtil.SMS_ACCOUNT));
        String sendUrl2 = sendUrl1.replace("{短信密码}", URLEncoder.encode(ConstantUtil.SMS_PASSWORD));
        String sendUrl3 = sendUrl2.replace("{短信号码}", telPhone);
        String sendUrl4 = sendUrl3.replace("{短信内容}", content);

        String sTemp1 = sendUrl4.substring(0, sendUrl4.lastIndexOf("&") + 5);
        String sTemp2 = sendUrl4.substring(sendUrl4.lastIndexOf("&") + 5);

        try {
            sTemp2 = URLEncoder.encode(sTemp2, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendUrl4 = sTemp1 + sTemp2;

        InputStream in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(sendUrl4);
            in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                sb.append(inputLine);
            }
            System.out.println("短信批次号=" + sb.toString());
            /*return sb.toString();*/
            String[] result = sb.toString().split("&");
            String num = result[0].substring(result[0].lastIndexOf("=") + 1, result[0].length());
            String errId = result[4].substring(result[4].lastIndexOf("=") + 1, result[4].length());

            ShortMessage smsRecord = new ShortMessage();
            smsRecord.setPhoneNum(telPhone);
            smsRecord.setContent(content);
            smsRecord.setSendTime(new Date());
            smsRecord.setData1(validationCode);
            if (!"0".equals(num)) {
                smsRecord.setSatatus(1);
            } else {
                smsRecord.setSatatus(Integer.parseInt(errId));
            }
            return smsRecord;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        }
        return null;
    }


}
