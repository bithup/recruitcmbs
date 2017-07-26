package com.xgh.recruitcmbs.services;

import com.xgh.recruitcmbs.dao.ISubjectDao;
import com.xgh.recruitcmbs.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("subjectService")
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    protected ISubjectDao subjectDao;


    public Subject get(long id) {
        return subjectDao.get(id);
    }

    public int insert(Subject subject) {
        return subjectDao.add(subject);
    }

    public int update(Subject subject) {
        return subjectDao.update(subject);
    }

    public List<Subject> getSubjects() {
        return subjectDao.getSubjects();
    }
}
