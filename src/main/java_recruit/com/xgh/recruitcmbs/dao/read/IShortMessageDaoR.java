package com.xgh.recruitcmbs.dao.read;

import com.xgh.recruitcmbs.entity.ShortMessage;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShortMessageDaoR {


    /**
     * 判断该手机短信验证码是否存在
     * @return
     */
    public ShortMessage getMsgByPhoneNum(String mobile);


}
