package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.entity.Resume;

/**
 * Created by BSX on 2017/3/13.
 */
public interface IResumeDao {

    Resume get(long id);

    int insert(Resume resume);

    int update(Resume resume);

    Resume getByMemberId(long memberId);


}
