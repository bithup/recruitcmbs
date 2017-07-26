package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.StoreTalents;

/**
 * Created by BSX on 2017/3/14.
 */
public interface IStoreTalentsDaoW {

    public int add(StoreTalents storeTalents);

    public int update(StoreTalents storeTalents);

    public int updateBatch(String[] ids);

    public int clearOutByCompanyId(String companyId);
}
