package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.ShortMessage;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShortMessageService {


    /**
     * 向短信记录表插入短信
     *
     * @param shortMessage
     * @return
     */
    public int add(ShortMessage shortMessage);


    /**
     * 判断该手机短信验证码是否存在
     * @return
     */
    public ShortMessage getMsgByPhoneNum(String mobile);


}
