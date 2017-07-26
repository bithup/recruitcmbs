package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.ICompanyInfoDaoR;
import com.xgh.recruitcmbs.dao.read.IResumeDaoR;
import com.xgh.recruitcmbs.dao.write.IResumeDaoW;
import com.xgh.recruitcmbs.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BSX on 2017/3/13.
 */
@Service("resumeDao")
public class ResumeDaoImpl implements IResumeDao {

    @Autowired
    protected IResumeDaoR resumeDaoR;

    @Autowired
    protected IResumeDaoW resumeDaoW;

    public Resume get(long id) {
        return resumeDaoR.get(id);
    }

    public int insert(Resume resume) {
        return resumeDaoW.insert(resume);
    }

    public int update(Resume resume) {
        return resumeDaoW.update(resume);
    }

    public Resume getByMemberId(long memberId) {
        return resumeDaoR.getByMemberId(memberId);
    }
}
