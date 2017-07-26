package com.xgh.recruitcmbs.controllers;

import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.entity.Recommend;
import com.xgh.recruitcmbs.services.IRecommendService;
import com.xgh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好创意 创意 2.挑毛病 3.吐槽
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/recruitcmbs/recommend/")
public class RecommendController extends BaseController {
    @Autowired
    protected IRecommendService recommendService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    /**
     * 获取列表页面
     */
    @RequestMapping(value = "getRecommendList")
    public void getRecommendList() {
        Map<String, Object> postMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String kind = request.getParameter("kind");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        postMap.put("page", Integer.parseInt(page));
        postMap.put("pageSize", Integer.parseInt(pageSize));
        if (StringUtil.notEmpty(kind) && StringUtil.isNumeric(kind)) {
            postMap.put("kind", Integer.parseInt(kind));
        } else {
            resultMap.put("resultFlag", 3);
            resultMap.put("resultMsg", "类型不正确");
            resultMap.put("resultData", null);
            outJson(resultMap);
        }
        List<Map<String, Object>> list =
                this.recommendService.getRecommendList(postMap);
        if (list != null && list.size() > 0) {
            resultMap.put("resultFlag", 1);
            resultMap.put("resultMsg", "获取列表成功");
            resultMap.put("resultData", list);
        } else {
            resultMap.put("resultFlag", 0);
            resultMap.put("resultMsg", "暂无数据");
            resultMap.put("resultData", null);
        }
        outJson(resultMap);
    }

    /**
     * 添加操作
     */
    @RequestMapping(value = "addRecommend")
    public void addRecommend(@ModelAttribute Recommend recommend) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (recommend != null) {
            //校验参数开始====================start
            if (StringUtil.isEmpty(recommend.getContext())) {
                resultMap = getResultMap("3", "内容不能为空");
                outJson(resultMap);
            }
            if (recommend.getKind() <= 0) {
                resultMap = getResultMap("3", "类型不正确");
                outJson(resultMap);
            }
     /*       if (StringUtil.isEmpty(recommend.getMemberid())) {
                resultMap.put("resultFlag", 3);
                resultMap.put("resultMsg", "用户id不正确");
                outJson(resultMap);
            }*/
            //校验参数结束====================end
            int flag = 0;
            flag = this.recommendService.add(recommend);
            if (flag == 1) {
                resultMap = getResultMap("1", "添加成功");
            } else {
                resultMap = getResultMap("0", "添加失败");
            }
        } else {
            resultMap = getResultMap("3", "参数名bu正确，请参考接口文档");
        }
        outJson(resultMap);
    }
}
