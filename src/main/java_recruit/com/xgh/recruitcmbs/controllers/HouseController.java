package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Dictionary;
import com.xgh.recruitcmbs.entity.House;
import com.xgh.recruitcmbs.entity.Zone;
import com.xgh.recruitcmbs.services.IDictionaryService;
import com.xgh.recruitcmbs.services.IHouseService;
import com.xgh.recruitcmbs.services.IZoneService;
import com.xgh.recruitcmbs.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by BSX on 2017/3/31.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/recruitcmbs/house/")
public class HouseController extends BaseController {

    @Autowired
    protected IHouseService houseService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IDictionaryService dictionaryService;

    /**
     * 求职者收藏职位
     */
    @RequestMapping(value = "addHouse")
    public void addHouse() {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        String positionId = request.getParameter("positionId");

        map.put("memberId", memberId);
        map.put("positionId", positionId);
        List<House> list = houseService.isExist(map);
        Date date = new Date();

        if (list.size() > 0) {
            House house = list.get(0);
            house.setStatus(0);
            house.setUpdateDate(date);
            int flg = houseService.update(house);
            if (flg > 0) {
                resultMap = getResultMap("1", "取消收藏!");
            }

        } else {
            House house = new House();
            house.setMemberId(Long.parseLong(memberId));
            house.setPositionId(Long.parseLong(positionId));
            house.setCreateDate(date);
            house.setUpdateDate(date);
            house.setStatus(1);
            int flag = houseService.add(house);
            if (flag > 0) {
                resultMap = getResultMap("1", "收藏成功!");
            }

        }

        outJson(resultMap);
    }

    /**
     * 职位收藏列表显示
     */
    @RequestMapping(value = "getPositionHouseList")
    public void getPositionHouseList() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pageSize));

        List<Map<String, Object>> houseList = houseService.getPositionHouseList(map);
        if (houseList != null && houseList.size() > 0) {

            for (Map<String, Object> map_ : houseList) {
                String zoneId = map_.get("zoneId") + "";
                Zone zone = zoneService.get(Long.parseLong(zoneId));
                if (zone != null) {
                    String zoneName = zone.getName();//区域名称
                    map_.put("zoneName", zoneName);
                }

                Map<String, Object> dicMap = new HashMap<String, Object>();
                dicMap.put("code", map_.get("companySize") + "");
                dicMap.put("item", "companySize");
                Dictionary dictionary = dictionaryService.getNameByCode(dicMap);
                if (dictionary != null) {
                    String value = dictionary.getValue();
                    map_.put("companySize", value);
                }

                String logoPath = map_.get("logoPath") + "";
                map_.put("logoPath", ConstantUtil.SERVER_URL + logoPath);
            }

            resultMap = getResultMap("1", "收藏列表显示成功!", houseList);
        } else {
            resultMap = getResultMap("-1", "收藏列表显示失败!");
        }
        outJson(resultMap);

    }

    /**
     * 删除职位收藏
     */
    @RequestMapping(value = "deleteHousePosition")
    public void deleteHousePosition() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String houseIds = request.getParameter("houseIds");//收藏id

        String houseId[] = houseIds.split(",");//收藏id

        List<String> list = Arrays.asList(houseId);//string字符串转换为list
        if (list != null && list.size() > 0) {

            int flag = houseService.batchDeleteByIdList(list);//批量删除
            if (flag > 0) {
                resultMap = getResultMap("1", "删除成功!");
            } else {
                resultMap = getResultMap("-1", "删除失败!");
            }
        }

        outJson(resultMap);
    }

    /**
     * 清空所有
     */
    @RequestMapping(value = "clearAll")
    public void clearAll() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String memberId = request.getParameter("memberId");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);

        List<String> houseList = houseService.getAllByMemberId(map);
        if (houseList != null && houseList.size() > 0) {
            int flag = houseService.batchDeleteByIdList(houseList);//清空所有
            if (flag > 0) {
                resultMap = getResultMap("1", "已经成功清除所有!");
            } else {
                resultMap = getResultMap("-1", "清除失败!");
            }
        } else {
            resultMap = getResultMap("-1", "暂时没有收藏职位!");
        }

        outJson(resultMap);
    }


}
