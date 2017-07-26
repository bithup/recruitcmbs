package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Zone;
import com.xgh.recruitcmbs.services.IZoneService;
import com.xgh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/25 0025.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/recruitcmbs/zone/")
public class ZoneController extends BaseController {


    @Autowired
    protected IZoneService zoneService;


    /**
     * 根据城市定位显示下辖各区
     */
    @RequestMapping(value = "getZoneCodeByName")
    public void getZoneCodeByName() {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        String zoneName = request.getParameter("zoneName");
        Map map = new HashMap();
        map.put("zoneName", zoneName);
        List<Zone> zoneList = zoneService.getZones(map);
        Zone zone = new Zone();
        if (zoneList != null && zoneList.size() > 0) {
            zone = zoneList.get(0);
        }
        String zoneCode = zone.getCode();
        map.put("pcode", zoneCode);

        List<Zone> zoneNameList = zoneService.getAreaInfoByPcode(map);

        if (zoneList != null && zoneList.size() > 0) {
            Zone zone1 = new Zone();
            zone1.setId(zone.getId());
            zone1.setName("全部");
            zone1.setCode("");
            zoneNameList.add(0, zone1);
            resultMap = getResultMap("1", "下辖各区列表显示成功", zoneNameList);
        } else {
            resultMap = getResultMap("0", "下辖各区列表显示失败");
        }
        outJson(resultMap);

    }
}
