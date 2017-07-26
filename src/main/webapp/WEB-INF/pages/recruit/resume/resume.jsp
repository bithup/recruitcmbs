<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <title>简历详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="/resources/ui/site/css/reset.css">
    <link rel="stylesheet" href="/resources/ui/site/css/resume_pre.css">
    <script src="/resources/ui/site/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/ui/site/js/ort.js"></script>
</head>
<body>
<!-- 头部开始 -->
<%--<div class="head ver">
    <a href="javascript:history.go(-1)" class="return"></a>
    简历详情
</div>--%>
<!-- 内容开始 -->
<div class="content">
    <!-- 添加头像开始 -->
    <div class="my_pic">
        <div class="wrap_">
            <h4>头像</h4>
            <div class="pic_wrap">
                <i><img src="${memberUser.headPath}"></i><br>
                <span></span>
            </div>
        </div>
    </div>
    <!-- 基本信息开始 -->
    <div class="my_base">
        <div class="wrap">
            <h4>基本信息</h4>
            <ul>
                <li><b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</b><span>${memberUser.realName}</span></li>
                <li><b>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</b><span>${memberUser.sex}</span></li>
                <%--<c:choose>
                    <c:when test="${memberUser.sex==1}">
                        <li><b>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</b><span>男</span></li>
                    </c:when>
                    <c:otherwise>
                        <li><b>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</b><span>女</span></li>
                    </c:otherwise>
                </c:choose>--%>
                <li><b>出生日期：</b><span>${memberUser.birthday}</span></li>
                <li><b>最高学历：</b><span>${memberUser.education}</span></li>
                <li><b>工作年限：</b><span>${memberUser.workYear}年</span></li>
                <li><b>所在城市：</b><span>${memberUser.zoneName}</span></li>
                <li><b>联系电话：</b><span>${memberUser.telephone}</span></li>
                <li><b>联系邮箱：</b><span>${memberUser.email}</span></li>
            </ul>
        </div>
    </div>
    <!-- 工作经历开始 -->
    <div class="my_base">
        <div class="wrap">
            <h4>工作经历</h4>
            <ul>
                <c:forEach items="${workExperienceList}" var="c">
                    <li><b>公司名称：</b><span>${c.companyName}</span></li>
                    <li><b>起止时间：</b><span>${c.startDate}_${c.endDate}</span></li>
                    <li><b>职位类别：</b><span>${c.jobKindsName}</span></li>
                    <li><b>职位名称：</b><span>${c.jobName}</span></li>
                    <li><b>职位薪金：</b><span>${c.monthlyPay}</span></li>
                    <li><b>职责描述：</b><span>${c.jobIntro}</span>
                    </li>
                    <br>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- 教育背景开始 -->
    <div class="my_base">
        <div class="wrap">
            <h4>教育背景</h4>
            <ul><c:forEach items="${eduExperienceList}" var="c">
                <li><b>学校名称：</b><span>${c.schoolName}</span></li>
                <li><b>专业名称：</b><span>${c.major}</span></li>
                <li><b>学历学位：</b><span>${c.data3}</span></li>
                <li><b>起止时间：</b><span>${c.eduBeginDate}_${c.eduEndDate}</span></li>
                <br>
            </c:forEach>
            </ul>
        </div>
    </div>
    <!-- 期望工作开始 -->
    <div class="my_base">
        <div class="wrap">
            <h4>期望工作</h4>
            <ul>
                <li><b>期望职位：</b><span>${jobObjective.jobName}</span></li>
                <li><b>期望城市：</b><span>${jobObjective.zoneName}</span></li>
                <li><b>在职状态：</b><span>${jobObjective.serviceStatus}</span></li>
                <li><b>期望薪资：</b><span>${jobObjective.salaryMin}-${jobObjective.salaryMax}</span></li>
                <li><b>工作性质：</b><span>${jobObjective.jobType}</span></li>
            </ul>
        </div>
    </div>
    <!-- 自我评价开始 -->
    <div class="my_base">
        <div class="wrap">
            <h4>自我评价</h4>
            <ul>
                <li><span class="self_info">${memberUser.selfEvaluation}</span></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
