package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IZoneDao;
import com.xgh.recruitcmbs.entity.CompanyInfo;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.entity.ResumeDelivery;
import com.xgh.recruitcmbs.entity.Zone;
import com.xgh.recruitcmbs.services.ICompanyInfoServices;
import com.xgh.recruitcmbs.services.IDictionaryService;
import com.xgh.recruitcmbs.services.IResumeDeliveryService;
import com.xgh.recruitcmbs.util.ConstantUtil;
import com.xgh.recruitcmbs.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by CQ on 2017/3/16.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/resumeDelivery/")
public class ResumeDelieveryController extends BaseController {

    private Logger logger = Logger.getLogger(ResumeDelieveryController.class);


    @Autowired
    protected IResumeDeliveryService resumeDeliveryService;

    @Autowired
    protected IDictionaryService dictionaryService;

    @Autowired
    protected IZoneDao zoneDao;

    @Autowired
    protected ICompanyInfoServices companyInfoServices;

    /**
     * 投递简历筛选
     */
    @RequestMapping(value = "screenResumeDelivery")
    public void screenResumeDelivery() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String education = request.getParameter("education");
        String workYear = request.getParameter("workYear");
        String jobType = request.getParameter("jobType");
        String zoneId = request.getParameter("zoneId");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("edu", education);//学历
        map.put("workYear", workYear);//工作年限
        map.put("jobType", jobType);//工作性质
        map.put("zoneId", zoneId);//zoneId

        List<Map<String, Object>> resumeList = resumeDeliveryService.screenResumeDelivery(map);
        if (resumeList != null && resumeList.size() > 0) {

            for (Map<String, Object> map_ : resumeList) {
                /**
                 * 根据区域id查询区域名称
                 */
                String zoneId_ = map_.get("zoneId") + "";
                Zone zone = zoneDao.get(Long.parseLong(zoneId_));
                String zoneAreaName = zone.getName();
                long pid = Long.parseLong(zone.getPid());
                zone = zoneDao.get(pid);
                String zoneName = zone.getName();

                map_.put("zoneName", zoneName + zoneAreaName);
                map_.remove("zoneId");

                /**
                 * 字典表edu对应value值查询
                 */
                logger.info("............");
                String edu = map_.get("edu") + "";
                Map<String, Object> mapDict = new HashMap<String, Object>();
                mapDict.put("item", ConstantUtil.FileUploadCode.Edu);
                mapDict.put("code", edu);
                Dictionary dictionary = dictionaryService.getNameByCode(mapDict);
                if (dictionary != null) {
                    map_.put("edu", dictionary.getValue());
                }
            }
            resultMap = getResultMap("1", "列表显示成功!", resumeList);
        } else {
            resultMap = getResultMap("0", "暂无该筛选条件职位!");
        }

        outJson(resultMap);
    }


    /**
     * 企业版收件箱
     */
    @RequestMapping(value = "resumeBox")
    public void resumeBox() {

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

        List<Map<String, Object>> list = resumeDeliveryService.getListPage(map);
        for (Map<String, Object> deliveryMap : list) {
            dicMap.put("item", "edu");
            dicMap.put("code", deliveryMap.get("education"));
            deliveryMap.put("education", dictionaryService.getValue(dicMap));
            dicMap.put("item", "workyear");
            dicMap.put("code", deliveryMap.get("workYear"));
            deliveryMap.put("workYear", dictionaryService.getValue(dicMap));
            if (null != deliveryMap.get("zoneName") && !"".equals(deliveryMap.get("zoneName").toString()) && null != deliveryMap.get("address") && !"".equals(deliveryMap.get("address").toString())) {
                deliveryMap.put("address", deliveryMap.get("zoneName").toString() + deliveryMap.get("address").toString());
            } else {
                deliveryMap.put("address", "未知区域");
            }
        }
        if (list.size() > 0) {
            resultMap = getResultMap("1", "获取数据成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据");
        }
        outJson(resultMap);
    }


    /**
     * 收件箱里的不合适操作
     */
    @RequestMapping(value = "unsuit")
    public void unsuit() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        int flag = 0;

        String id = request.getParameter("id");
        map.put("id", id);

        ResumeDelivery resumeDelivery = resumeDeliveryService.get(Long.parseLong(id));
        resumeDelivery.setDeliveryStatus(4);
        resumeDelivery.setUpdateDate(new Date());
        flag = resumeDeliveryService.update(resumeDelivery);
        if (flag > 0) {
            resultMap = getResultMap("1", "操作成功！");
        } else {
            resultMap = getResultMap("0", "操作失败！");
        }
        outJson(resultMap);
    }


    /**
     * 求职者投递简历
     */
    @RequestMapping(value = "goDeliveryResume")
    public void goDeliveryResume(@ModelAttribute ResumeDelivery resumeDelivery) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        long resumeId = resumeDelivery.getResumeId();//简历id

        if (resumeId != 0) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", resumeDelivery.getMemberId());
            map.put("positionId", resumeDelivery.getPositionId());

            List<ResumeDelivery> list = resumeDeliveryService.getDeliveriesAmongThirty(map);
            if (list != null && list.size() > 0) {
                resultMap = getResultMap("-1", "30天以内不能再次投递该简历!");
                outJson(resultMap);
                return;
            }

            resumeDelivery.setIsView(0);//未被查看
            resumeDelivery.setDeliveryStatus(0);//投递状态
            resumeDelivery.setCreateDate(new Date());
            resumeDelivery.setUpdateDate(new Date());
            resumeDelivery.setStatus(1);

            final int add = resumeDeliveryService.add(resumeDelivery);
            int flag = add;
            if (flag > 0) {
                resultMap = getResultMap("1", "简历投递成功!");
            } else {
                resultMap = getResultMap("-1", "简历投递失败!");
            }
        } else {
            resultMap = getResultMap("-1", "请先编写简历!");
        }
        outJson(resultMap);

    }

    /**
     * 求职者批量投递简历
     */
    @RequestMapping(value = "batchGoToDelivery")
    public void batchGoToDelivery() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String positionIds = request.getParameter("positionId");
        String resumeId = request.getParameter("resumeId");
        String memberId = request.getParameter("memberId");

        List<ResumeDelivery> resumeDeliveryList = new ArrayList<ResumeDelivery>();

        String positionId[] = positionIds.split(",");
        if (!"".equals(positionId)) {
            List<String> list = Arrays.asList(positionId);

            if (list != null && list.size() > 0) {

                for (String id : list) {

                    ResumeDelivery resumeDelivery = new ResumeDelivery();
                    resumeDelivery.setPositionId(Long.parseLong(id));
                    resumeDelivery.setMemberId(Long.parseLong(memberId));
                    resumeDelivery.setResumeId(Long.parseLong(resumeId));
                    resumeDelivery.setCreateDate(new Date());
                    resumeDelivery.setUpdateDate(new Date());
                    resumeDelivery.setDeliveryStatus(0);
                    resumeDelivery.setIsView(0);//
                    resumeDelivery.setStatus(1);
                    resumeDelivery.setData3("0");
                    resumeDelivery.setData4("0");
                    resumeDelivery.setData5("0");

                    resumeDeliveryList.add(resumeDelivery);
                }
                int flag = resumeDeliveryService.addBatch(resumeDeliveryList);
                if (flag > 0) {
                    resultMap = getResultMap("1", "简历已成功投递所有公司!");
                } else {
                    resultMap = getResultMap("-1", "投递失败!");
                }
            }
        }
        outJson(resultMap);
    }

    /**
     * 根据会员id获取简历投递记录
     */
    @RequestMapping(value = "getDeliveriesList")
    public void getDeliveriesList() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId_ = request.getParameter("memberId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        String memberId = DateUtil.ConvertUtil(memberId_);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Map<String, Object>> resumeDeliveryList = resumeDeliveryService.getDeliveriesList(map);
        if (resumeDeliveryList != null && resumeDeliveryList.size() > 0) {

            for (Map<String, Object> map_ : resumeDeliveryList) {

                String logoPath = map_.get("logoPath") + "";
                map_.put("logoPath", ConstantUtil.SERVER_URL + logoPath);

                String zoneId = map_.get("zoneId") + "";

                Zone zone = zoneDao.get(Long.parseLong(zoneId));
                if (zone != null) {
                    map_.put("zoneName", zone.getName());
                }

                String createDate = map_.get("createDate") + "";
                try {
                    Date date = DateUtil.parse(createDate);
                    String date_ = DateUtil.formatDate(date);
                    map_.put("createDate", date_);
                } catch (Exception e) {

                }

            }
            resultMap = getResultMap("1", "投递列表显示成功!", resumeDeliveryList);
        } else {
            resultMap = getResultMap("0", "暂无数据!");
        }

        outJson(resultMap);
    }

    /**
     * 删除投递记录
     */
    @RequestMapping(value = "deleteDeliveryRecord")
    public void deleteDeliveryRecord() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String resumeDeliveryIds = request.getParameter("resumeDeliveryId");//简历投递id
        String resumeDeliveryId[] = resumeDeliveryIds.split(",");

        List<String> list = Arrays.asList(resumeDeliveryId);
        if (list != null && list.size() > 0) {
            try {
                int flag = resumeDeliveryService.batchDeleteByIdList(list);
                if (flag > 0) {
                    resultMap = getResultMap("1", "删除成功!");
                }
            } catch (Exception e) {
                resultMap = getResultMap("-1", "删除失败!");
            }
        }
        outJson(resultMap);
    }

    /**
     * 清空所有投递记录
     */
    @RequestMapping(value = "clearAllResumeDeliveryRecord")
    public void clearAllResumeDeliveryRecord() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);

        List<String> list = resumeDeliveryService.getDeliveriesListByMemberId(map);
        if (list != null && list.size() > 0) {

            int flag = resumeDeliveryService.batchDeleteByIdList(list);
            if (flag > 0) {
                resultMap = getResultMap("1", "清空所有记录成功!");
            }
        } else {
            resultMap = getResultMap("0", "暂无投递记录!");
        }

        outJson(resultMap);
    }

    /**
     * 收件箱数量
     */
    @RequestMapping(value = "getMessageBoxCount")
    public void getMessageBoxCount() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String companyId = request.getParameter("companyId");
        String deliveryStatus = request.getParameter("deliveryStatus");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("deliveryStatus", deliveryStatus);
        List<Map<String, Object>> resumeDeliveryList = resumeDeliveryService.getMessageBoxCount(map);


        Map<String, Object> map_ = new HashMap<String, Object>();
        CompanyInfo companyInfo = companyInfoServices.get(Long.parseLong(companyId));
        if (companyInfo != null) {

            map_.put("logoPath", ConstantUtil.SERVER_URL + companyInfo.getLogoPath());
            map_.put("companyName", companyInfo.getCompanyName());
            map_.put("count", resumeDeliveryList.size());
        }
        if (resumeDeliveryList != null && resumeDeliveryList.size() > 0) {
            resultMap = getResultMap("1", "显示成功", map_);
        } else {
            resultMap = getResultMap("0", "暂无投递", map_);
        }

        outJson(resultMap);
    }


}
