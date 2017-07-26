package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IShortMessageDaoR;
import com.xgh.recruitcmbs.dao.write.IShortMessageDaoW;
import com.xgh.recruitcmbs.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("shortMessageDao")
public class ShortMessageDaoImpl implements IShortMessageDao {

    @Autowired
    protected IShortMessageDaoR shortMessageDaoR;

    @Autowired
    protected IShortMessageDaoW shortMessageDaoW;


    public int add(ShortMessage shortMessage) {
        return shortMessageDaoW.add(shortMessage);
    }


    public ShortMessage getMsgByPhoneNum(String mobile) {
        return shortMessageDaoR.getMsgByPhoneNum(mobile);
    }


}
