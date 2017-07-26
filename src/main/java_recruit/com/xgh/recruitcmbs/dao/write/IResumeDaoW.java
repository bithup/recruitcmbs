package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.Resume;

/**
 * Created by BSX on 2017/3/13.
 */
public interface IResumeDaoW {

    int insert(Resume resume);

    int update(Resume resume);
}
