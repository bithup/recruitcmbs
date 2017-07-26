package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.entity.Resume;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by BSX on 2017/3/13.
 */
public interface IResumeService {

    public Map<String, Object> get(HttpServletRequest request);

    public int insert(Resume resume);

    public int update(Resume resume);

    public int save(HttpServletRequest request, Resume resume);

    Resume getByMemberId(long memberId);

    public Resume get(long id);

}
