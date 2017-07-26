package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.ISubjectDaoR;
import com.xgh.recruitcmbs.dao.write.ISubjectDaoW;
import com.xgh.recruitcmbs.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("subjectDao")
public class SubjectDaoImpl implements ISubjectDao {

    @Autowired
    protected ISubjectDaoR subjectDaoR;

    @Autowired
    protected ISubjectDaoW subjectDaoW;

    public Subject get(long id) {
        return subjectDaoR.get(id);
    }

    public int add(Subject subject) {
        return subjectDaoW.add(subject);
    }

    public int update(Subject subject) {
        return subjectDaoW.update(subject);
    }

    public List<Subject> getSubjects() {
        return subjectDaoR.getSubjects();
    }
}
