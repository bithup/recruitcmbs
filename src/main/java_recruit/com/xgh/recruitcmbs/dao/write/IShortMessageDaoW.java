package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.ShortMessage;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IShortMessageDaoW {

    /**
     * 向短信记录表插入短信
     *
     * @param shortMessage
     * @return
     */
    public int add(ShortMessage shortMessage);

}
