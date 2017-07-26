package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.dao.IStoreTalentsDao;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.entity.StoreTalents;
import com.xgh.recruitcmbs.services.IDictionaryService;
import com.xgh.recruitcmbs.services.IStoreTalentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BSX on 2017/3/14.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/storeTalents")
public class StoreTalentsController extends BaseController {

    @Autowired
    protected IStoreTalentsService storeTalentsService;

    @Autowired
    protected IDictionaryService dictionaryService;


    /**
     * 企业收藏列表
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
        List<Map<String, Object>> list = storeTalentsService.getListPage(map);

        for (Map<String, Object> talentMap : list) {
            dicMap.put("item", "edu");
            dicMap.put("code", talentMap.get("education"));
            talentMap.put("education", dictionaryService.getValue(dicMap));
            dicMap.put("item", "workyear");
            dicMap.put("code", talentMap.get("workYear"));
         /* String workyear = dictionaryService.getValue(dicMap);*/
            talentMap.put("workYear", dictionaryService.getValue(dicMap));
            talentMap.put("address", talentMap.get("zoneName") + "");

        }
        if (list != null && list.size() > 0) {
            resultMap = getResultMap("1", "获取收藏列表成功！", list);
        } else {
            resultMap = getResultMap("0", "暂无数据！");
        }
        outJson(resultMap);
    }

    /**
     * 企业添加收藏
     */
    @RequestMapping(value = "addStoreTalents")
    public void addStoreTalents() {

        long storeTalentId = 0;

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        String memberId = request.getParameter("memberId");
        map.put("companyId", companyId);
        map.put("memberId", memberId);
        List<StoreTalents> storeList = storeTalentsService.isExist(map);
        int flag = 0;
        if (storeList.size() > 0) {
            StoreTalents storeTalents = storeList.get(0);
            if (storeTalents.getStatus() == 0) {
                storeTalents.setStatus(1);
                flag = storeTalentsService.update(storeTalents);
                if (flag > 0) {
                    resultMap = getResultMap("1", "收藏成功！", storeTalentId);
                } else {
                    resultMap = getResultMap("0", "收藏失败！");
                }
            } else {
                storeTalents.setStatus(0);
                flag = storeTalentsService.update(storeTalents);
                if (flag > 0) {
                    resultMap = getResultMap("1", "取消收藏成功！", storeTalentId);
                } else {
                    resultMap = getResultMap("0", "取消收藏失败！");
                }
            }

        } else {
            StoreTalents storeTalents = new StoreTalents();
            storeTalents.setCompanyId(Long.parseLong(companyId));
            storeTalents.setMemberId(Long.parseLong(memberId));
            Date date = new Date();
            storeTalents.setCreateDate(date);
            storeTalents.setUpdateDate(date);
            storeTalents.setStatus(1);
            flag = storeTalentsService.add(storeTalents);
            storeTalentId = storeTalents.getId();
            if (flag > 0) {
                resultMap = getResultMap("1", "添加收藏成功！", storeTalentId);
            } else {
                resultMap = getResultMap("0", "添加收藏失败！");
            }
        }

        outJson(resultMap);
    }

    /**
     * 取消收藏简历,恢复收藏简历
     */
    @RequestMapping(value = "cancelStoreTalents")
    public void cancelStoreTalents() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String storeId = request.getParameter("id");
        StoreTalents talents = storeTalentsService.get(Long.parseLong(storeId));
        talents.setUpdateDate(new Date());
        talents.setStatus(0);
        int flag = storeTalentsService.update(talents);
        if (flag > 0) {
            resultMap = getResultMap("1", "取消收藏成功！", talents.getId());
        } else {
            resultMap = getResultMap("0", "取消收藏失败！");
        }
        outJson(resultMap);
    }


    /**
     * 企业删除收藏
     */
    @RequestMapping(value = "deleteStoreTalents")
    public void deleteStoreTalents() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        String[] id = ids.split(",|，");
        int flag = storeTalentsService.updateBatch(id);
        if (flag > 0) {
            resultMap = getResultMap("1", "删除成功！");
        } else {
            resultMap = getResultMap("0", "删除失败！");
        }
        outJson(resultMap);
    }


    /**
     * 企业清空收藏
     */
    @RequestMapping(value = "clearOutStoreTalents")
    public void clearOutStoreTalents() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        int flag = storeTalentsService.clearOutByCompanyId(companyId);
        if (flag > 0) {
            resultMap = getResultMap("1", "清空成功！");
        } else {
            resultMap = getResultMap("0", "清空失败！");
        }
        outJson(resultMap);
    }
}
