package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IInterviewDao;
import com.xgh.recruitcmbs.entity.Interview;
import com.xgh.recruitcmbs.entity.ResumeDelivery;

import com.xgh.recruitcmbs.services.IDictionaryService;
import com.xgh.recruitcmbs.services.IInterviewService;
import com.xgh.recruitcmbs.services.IResumeDeliveryService;
import com.xgh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BSX on 2017/3/15.
 */
@Controller
@Scope("prototype")
@RequestMapping("/recruitcmbs/interview/")
public class InterviewController extends BaseController {

    @Autowired
    protected IInterviewService interviewService;

    @Autowired
    protected IResumeDeliveryService resumeDeliveryService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IInterviewDao interviewDao;

    /**
     * 企业查询发出的面试邀请记录
     */
    @RequestMapping(value = "getListPage")
    public void getListPage() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("companyId", companyId);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String, Object>> list = interviewService.getListPage(map);
        for (Map<String, Object> interviewMap : list) {
            dicMap.put("item", "edu");
            dicMap.put("code", interviewMap.get("education"));
            interviewMap.put("education", dictionaryService.getValue(dicMap));
            dicMap.put("item", "workyear");
            dicMap.put("code", interviewMap.get("workYear"));
            interviewMap.put("workYear", dictionaryService.getValue(dicMap));
            interviewMap.put("address", interviewMap.get("zoneName"));

        }
        if (list != null && list.size() > 0) {
            resultMap = getResultMap("1", "获取邀请列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }


    /**
     * 收件箱发送面试邀请
     */
    @RequestMapping(value = "addBoxInterview")
    public void addInterview() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");

        //收件箱里面发送面试邀请,发送的是投递id,首页列表和详情界面是用户id
        String resumeDelveryId = request.getParameter("id");//投递id
        ResumeDelivery resumeDelivery = resumeDeliveryService.get(Long.parseLong(resumeDelveryId));
        Map<String, Object> map_ = new HashMap<String, Object>();
        if (resumeDelivery != null) {
            long memberId = resumeDelivery.getMemberId();
            long positionId = resumeDelivery.getPositionId();//职位id

            map_.put("companyId", companyId);
            map_.put("memberId", memberId);
            map_.put("data1", positionId);
        }
        long positionId = resumeDelivery.getPositionId();//职位id
        long memberId = resumeDelivery.getMemberId();
        Interview interview = interviewService.getLastInterview(map_);//先判断之前是否给该用户发送过面试邀请
        if (interview != null) {//不等于空表示之前发送过面试邀请,然后判断是否超过30天,如果超过30天则允许继续发送,不超过则不允许继续发送;

            Calendar theCa = Calendar.getInstance();
            theCa.setTime(new Date());
            theCa.add(theCa.DATE, -30);//当前日期提前30天
            Date start = theCa.getTime();
            Date createDate = interview.getCreateDate();
            if (createDate.getTime() < start.getTime()) {//超过30天,则继续发送
                Interview interview1 = new Interview();
                interview1.setCompanyId(Long.parseLong(companyId));
                interview1.setMemberId(memberId);
                interview1.setStatus(1);
                interview1.setPersonOperationStatus(1);
                interview1.setData1(positionId);
                interview.setData7(0);
                interview1.setUpdateDate(new Date());
                interview1.setCreateDate(new Date());
                int flag = interviewService.add(interview1);
                if (flag > 0) {

                    resumeDelivery.setDeliveryStatus(1);
                    resumeDelivery.setUpdateDate(new Date());
                    resumeDeliveryService.update(resumeDelivery);

                    resultMap = getResultMap("1", "发送成功！");
                } else {
                    resultMap = getResultMap("0", "发送失败！");
                }
            } else {
                resultMap = getResultMap("2", "同一个职位,30天内不得重复发送！");
            }
        } else {
            Interview interview_ = new Interview();
            interview_.setCompanyId(Long.parseLong(companyId));
            interview_.setMemberId(memberId);
            interview_.setStatus(1);
            interview_.setPersonOperationStatus(1);
            interview_.setData1(positionId);
            interview_.setData7(0);
            Date date = new Date();
            interview_.setUpdateDate(date);
            interview_.setCreateDate(date);
            int flag = interviewService.add(interview_);
            if (flag > 0) {

                resumeDelivery.setDeliveryStatus(1);
                resumeDelivery.setUpdateDate(new Date());
                resumeDeliveryService.update(resumeDelivery);
                System.out.println("...........");
                resultMap = getResultMap("1", "发送成功！");
            } else {
                resultMap = getResultMap("0", "发送失败！");
            }
        }
        outJson(resultMap);

    }

    /**
     * 简历详情发送面试邀请
     */
    @RequestMapping(value = "addResumeInterview")
    public void addResumeInterview() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String companyId = request.getParameter("companyId");
        String memberId = request.getParameter("id");//用户id
        String positionId = request.getParameter("positionId");//职位id

        //说明是收件箱里面点击进入的简历详情
        if (!"".equals(positionId)) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("companyId", companyId);
            map.put("memberId", memberId);
            map.put("data1", positionId);

            Interview interview = interviewService.getLastInterview(map);
            if (interview != null) {//不等于空代表之前投递过,然后判断是否超过时间限制;
                Calendar theCa = Calendar.getInstance();
                theCa.setTime(new Date());
                theCa.add(theCa.DATE, -30);//当前日期提前30天
                Date start = theCa.getTime();
                Date createDate = interview.getCreateDate();
                System.out.println("......");
                if (createDate.getTime() < start.getTime()) {//超过30天可以发送面试邀请
                    Interview interview1 = new Interview();
                    interview1.setCompanyId(Long.parseLong(companyId));
                    interview1.setMemberId(Long.parseLong(memberId));
                    interview1.setStatus(1);
                    interview1.setPersonOperationStatus(1);
                    interview1.setData1(Long.parseLong(positionId));
                    interview1.setData7(0);
                    interview1.setUpdateDate(new Date());
                    interview1.setCreateDate(new Date());
                    int flag = interviewService.add(interview1);
                    if (flag > 0) {

                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("memberId", memberId);
                        map1.put("positionId", positionId);

                        ResumeDelivery resumeDelivery = resumeDeliveryService.getDeliveryByMemAndPosition(map1);
                        if (resumeDelivery != null) {//发送面试邀请后,更新投递记录状态

                            resumeDelivery.setDeliveryStatus(1);
                            resumeDelivery.setUpdateDate(new Date());
                            resumeDeliveryService.update(resumeDelivery);
                        }

                        resultMap = getResultMap("1", "发送成功！");
                    } else {
                        resultMap = getResultMap("0", "发送失败！");
                    }
                } else {//小于30天不让发送
                    resultMap = getResultMap("2", "同一个职位,30天内不得重复发送！");
                }
            } else {//之前没有投递过属于第一次投递;

                Interview interview_ = new Interview();
                interview_.setCompanyId(Long.parseLong(companyId));
                interview_.setMemberId(Long.parseLong(memberId));
                interview_.setStatus(1);
                interview_.setPersonOperationStatus(1);
                interview_.setData1(Long.parseLong(positionId));
                interview_.setData7(0);
                Date date = new Date();
                interview_.setUpdateDate(date);
                interview_.setCreateDate(date);
                int flag = interviewService.add(interview_);
                if (flag > 0) {
                    Map<String, Object> mapParam = new HashMap<String, Object>();
                    mapParam.put("memberId", memberId);
                    mapParam.put("positionId", positionId);
                    ResumeDelivery resumeDelivery = resumeDeliveryService.getDeliveryByMemAndPosition(mapParam);
                    if (resumeDelivery != null) {//发送面试邀请后,更新投递记录状态
                        System.out.println(".........");
                        resumeDelivery.setDeliveryStatus(1);
                        resumeDelivery.setUpdateDate(new Date());
                        resumeDeliveryService.update(resumeDelivery);
                    }
                    resultMap = getResultMap("1", "发送成功！");
                } else {
                    resultMap = getResultMap("0", "发送失败！");
                }
            }

        } else {//说明是从首页列表,查看个人信息进入,进入简历详情的
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("companyId", companyId);
            map1.put("memberId", memberId);

            List<Interview> interviewList = interviewDao.getInterviewsAmongThirty(map1);
            if (interviewList != null) {
                resultMap = getResultMap("2", "30天之内不允许重复发送！");
            } else {
                Interview interview_ = new Interview();
                interview_.setCompanyId(Long.parseLong(companyId));
                interview_.setMemberId(Long.parseLong(memberId));
                interview_.setStatus(1);
                interview_.setPersonOperationStatus(1);
                interview_.setData1(0l);
                interview_.setData7(0);
                Date date = new Date();
                interview_.setUpdateDate(date);
                interview_.setCreateDate(date);
                int flag = interviewService.add(interview_);
                if (flag > 0) {
                    resultMap = getResultMap("1", "发送成功！");
                } else {
                    resultMap = getResultMap("0", "发送失败！");
                }
            }


        }

        outJson(resultMap);
    }


    /**
     * 企业删除面试邀请
     */
    @RequestMapping(value = "deleteInterview")
    public void deleteInterview() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        String[] id = ids.split(",|，");
        int flag = interviewService.updateBatch(id);
        if (flag > 0) {
            resultMap = getResultMap("1", "删除成功！");
        } else {
            resultMap = getResultMap("0", "删除失败！");
        }
        outJson(resultMap);
    }

    /**
     * 企业清空面试邀请
     */
    @RequestMapping(value = "clearOutInterview")
    public void clearOutInterview() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        int flag = interviewService.clearOutByCompanyId(companyId);
        if (flag > 0) {
            resultMap = getResultMap("1", "清空成功！");
        } else {
            resultMap = getResultMap("0", "清空失败！");
        }
        outJson(resultMap);
    }

    /**
     * 待面试，待确定列表
     */
    @RequestMapping(value = "getInterviews")
    public void getInterviews() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        String type = request.getParameter("type");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("companyId", companyId);
        map.put("type", type);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String, Object>> list = interviewService.getListPage(map);
        for (Map<String, Object> interviewMap : list) {
            dicMap.put("item", "edu");
            dicMap.put("code", interviewMap.get("education"));
            interviewMap.put("education", dictionaryService.getValue(dicMap));
            dicMap.put("item", "workyear");
            dicMap.put("code", interviewMap.get("workYear"));
            interviewMap.put("workYear", dictionaryService.getValue(dicMap));
            interviewMap.put("address", interviewMap.get("zoneName"));
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据");
        }
        outJson(resultMap);
    }


    /**
     * 修改面试邀请状态
     */
    @RequestMapping(value = "updateStatus")
    public void updateStatus() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        Interview interview = interviewService.get(Long.parseLong(id));
        interview.setData7(Integer.parseInt(status));
        interview.setUpdateDate(new Date());
        int flag = interviewService.update(interview);
        if (flag > 0) {
            resultMap = getResultMap("1", "操作成功！");
        } else {
            resultMap = getResultMap("0", "操作失败！");
        }
        outJson(resultMap);
    }


    /**
     * 求职者转正，企业发红包
     */
    @RequestMapping(value = "becomeRegular")
    public void becomeRegular() {
        outJson(interviewService.updateRegular(request));
    }


    /**
     * 求职者面试邀请记录
     */
    @RequestMapping(value = "getInterviewsByMemberId")
    public void getInterviewsByMemberId() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dicMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");

        map.put("memberId", memberId);
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));

        List<Map<String, Object>> list = interviewService.getInterviewsByMemberId(map);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> interviewMap : list) {

            String createDate_ = interviewMap.get("createDate") + "";
            try {
                Date createDate = simpleDateFormat.parse(createDate_);
                String date = simpleDateFormat.format(createDate);
                interviewMap.put("createDate", date);
            } catch (Exception e) {

            }

            //从字典表获取公司规模的值
            dicMap.put("item", "companySize");
            dicMap.put("code", interviewMap.get("companySize"));
            String value = dictionaryService.getValue(dicMap);
            interviewMap.put("companySize", value);
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取面试邀请成功", list);
        } else {
            resultMap = getResultMap("0", "暂无数据");
        }
        outJson(resultMap);
    }

    /**
     * 用户删除邀请记录
     */
    @RequestMapping(value = "deleteMemberInterviews")
    public void deleteMemberInterviews() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        String[] ids_ = ids.split(",|，");
        int flag = interviewService.deleteMemberInterviews(ids_);
        if (flag > 0) {
            resultMap = getResultMap("1", "删除成功");
        } else {
            resultMap = getResultMap("0", "删除失败，请重试");
        }
        outJson(resultMap);
    }


    /**
     * 用户清空面试邀请
     */
    @RequestMapping(value = "clearOutMemberInterviews")
    public void clearOutMemberInterviews() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String memberId = request.getParameter("memberId");
        int flag = interviewService.clearOutByMemberId(memberId);
        if (flag > 0) {
            resultMap = getResultMap("1", "清空成功");
        } else {
            resultMap = getResultMap("0", "清空失败，请重试");
        }
        outJson(resultMap);
    }

    /**
     * 求职者申请转正
     */
    @RequestMapping(value = "memberApplyPositive")
    public void memberApplyPositive() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String id = request.getParameter("interviewId");
        Interview interview = interviewService.get(Long.parseLong(id));
        long data7 = interview.getData7();
        if (data7 == 2) {
            resultMap = getResultMap("0", "您已发送职位转正申请!");
            outJson(resultMap);
            return;
        }
        interview.setData7(2);
        interview.setUpdateDate(new Date());
        int flag = interviewService.update(interview);
        if (flag > 0) {
            resultMap = getResultMap("1", "申请成功，请耐心等待公司确认");
        } else {
            resultMap = getResultMap("0", "操作失败，请重试");
        }
        outJson(resultMap);
    }
}
