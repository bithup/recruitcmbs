package com.xgh.recruitcmbs.dao;

import com.xgh.recruitcmbs.dao.read.IInterviewDaoR;
import com.xgh.recruitcmbs.dao.write.IInterviewDaoW;
import com.xgh.recruitcmbs.entity.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Service("interviewDao")
public class InterviewDaoImpl implements IInterviewDao {

    @Autowired
    protected IInterviewDaoR interviewDaoR;

    @Autowired
    protected IInterviewDaoW interviewDaoW;

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return interviewDaoR.getListPage(map);
    }

    public int add(Interview interview) {
        return interviewDaoW.add(interview);
    }

    public int update(Interview interview) {
        return interviewDaoW.update(interview);
    }

    public int updateBatch(String[] ids) {
        return interviewDaoW.updateBatch(ids);
    }

    public int clearOutByCompanyId(String companyId) {
        return interviewDaoW.clearOutByCompanyId(companyId);
    }

    public Interview getLastInterview(Map<String, Object> map) {
        return interviewDaoR.getLastInterview(map);
    }

    public Interview get(long id) {
        return interviewDaoR.get(id);
    }

    public int getEnrollNum(Map<String, Object> map) {
        return interviewDaoR.getEnrollNum(map);
    }

    public List<Map<String, Object>> getInterviewsByMemberId(Map<String, Object> map) {
        return interviewDaoR.getInterviewsByMemberId(map);
    }

    public int deleteMemberInterviews(String[] ids) {
        return interviewDaoW.deleteMemberInterviews(ids);
    }

    public int clearOutByMemberId(String memberId) {
        return interviewDaoW.clearOutByMemberId(memberId);
    }

    public List<Interview> getInterviewsAmongThirty(Map<String, Object> map) {
        return interviewDaoR.getInterviewsAmongThirty(map);
    }
}
