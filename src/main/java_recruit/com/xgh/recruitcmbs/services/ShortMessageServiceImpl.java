package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.IShortMessageDao;
import com.xgh.recruitcmbs.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("shortMessageService")
public class ShortMessageServiceImpl implements IShortMessageService {

    @Autowired
    protected IShortMessageDao shortMessageDao;


    public int add(ShortMessage shortMessage) {
        return shortMessageDao.add(shortMessage);
    }

    public ShortMessage getMsgByPhoneNum(String mobile) {
        return shortMessageDao.getMsgByPhoneNum(mobile);
    }
}
