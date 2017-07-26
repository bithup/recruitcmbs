<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>求职版接口操作测试</title>
</head>
<body>

<div id="isAlreadyRegister">1、求职者手机号是否已被注册</div>
<form action="/recruitcmbs/memberUser/isAlreadyRegister.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/isAlreadyRegister.htm
   //入参
        手机号：account
   //出参

    </textarea>
    <input type="hidden" name="account" value="15803882486"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="register">2、求职者注册</div>
<form action="/recruitcmbs/memberUser/register.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/register.htm
   //入参
        手机号：account
        验证码：validationCode
        密码：password
        确认密码：checkPassword
   //出参

    </textarea>
    <input type="hidden" name="account" value="15803882486"/>
    <input type="hidden" name="validationCode" value="37044"/>
    <input type="hidden" name="password" value="123456"/>
    <input type="hidden" name="checkPassword" value="123456"/>
    <input type="submit" value="调用接口"/>
</form>


<div>3.发送短信验证码</div>
<form action="recruitcmbs/shortMessage/sendMessage.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.99:8091/recruitcmbs/shortMessage/sendMessage.htm

     type=0是注册调用短信接口,1是找回密码短信接口
    </textarea>
    <input type="text" name="mobile" value="15803882486"/>
    <input type="text" name="type" value="0"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="login">4、求职者登录</div>
<form action="/recruitcmbs/memberUser/login.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/login.htm
   //入参
        手机号：account
        密码：password
   //出参

    </textarea>
    <input type="hidden" name="account" value="15803882486"/>
    <input type="hidden" name="password" value="123456"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="forgetPassword">5、求职者忘记密码</div>
<form action="/recruitcmbs/memberUser/forgetPassword.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/forgetPassword.htm
   //入参
        手机号：account
        验证码：validationCode
        密码：password
        确认密码：checkPassword
   //出参

    </textarea>
    <input type="hidden" name="account" value="15803882486"/>
    <input type="hidden" name="validationCode" value="72818"/>
    <input type="hidden" name="password" value="456789"/>
    <input type="hidden" name="checkPassword" value="456789"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="modifyPassword">6、求职者修改密码</div>
<form action="/recruitcmbs/memberUser/modifyPassword.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/modifyPassword.htm
   //入参
        手机号：memberId
        密码：password
        确认密码：checkPassword
   //出参

    </textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="password" value="123456"/>
    <input type="hidden" name="checkPassword" value="123456"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getJobObjective">7、查询求职意向</div>
<form action="recruitcmbs/jobObjective/getJobObjective.htm" enctype="multipart/form-data">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:80/recruitcmbs/jobObjective/getJobObjective.htm
     入参:
        用户id:memberId

     出参:
        求职意向id：id
        职位类型：jobType(1全职；2兼职)
        城市id:zoneId
        期望城市：zoneName
        期望职位：jobName
        职位类型id:data1
        <%--期望行业：industryId
        行业名称：industryName--%>
        期望薪资起点：salaryMin
        期望薪资终点：salaryMax
        在职状态：serviceStatus（1.离岗-随时到岗；2在职-月内到岗；3在职-考虑机会；4在职-暂不考虑；5应届生）


</textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="updateObjective">8、修改求职意向</div>
<form action="recruitcmbs/jobObjective/updateObjective.htm" enctype="multipart/form-data">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:80/recruitcmbs/jobObjective/updateObjective.htm
     入参:
        求职意向id：id
        职位类型：jobType(1全职；2兼职)
        期望城市：zoneName
        期望职位：jobName
        职位类型id:data1
        <%--期望行业：industryId
        行业名称：industryName--%>
        期望薪资起点：salaryMin
        期望薪资终点：salaryMax
        在职状态：serviceStatus（1.离岗-随时到岗；2在职-月内到岗；3在职-考虑机会；4在职-暂不考虑；5应届生）

     出参:


</textarea>
    <input type="hidden" name="id" value="4"/>
    <input type="hidden" name="jobType" value="1"/>
    <input type="hidden" name="zoneName" value="北京"/>
    <input type="hidden" name="jobName" value="java"/>
    <input type="hidden" name="data1" value="15"/>
    <%--<input type="hidden" name="industryId" value="5"/>
    <input type="hidden" name="industryName" value="金融"/>--%>
    <input type="hidden" name="salaryMin" value="2"/>
    <input type="hidden" name="salaryMax" value="3"/>
    <input type="hidden" name="serviceStatus" value="2"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getIndexSearch">9、求职版首页搜索</div>
<form action="/recruitcmbs/index/getIndexSearch.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getIndexSearch.htm
   //入参
        查询条件：condition
        经度：gpsLongitude
        纬度：gpsLatitude
        城市名称：zoneName
        用户id：memberId（如果登陆传memberId,未登录传zoneName）
        page
        pagesize

   //出参
        企业id:companyId,
        企业名称：companyName,
        企业规模：companySize,
        纬度：gpsLatitude,
        经度：gpsLongitude,
        头像地址：logoRealPath,
        区域id:zoneId,
        区域名称：zoneName,
        详细地址:address,
        职位id:positionId,
        职位名称：jobName,
        薪资：salary
        创建时间：createDate


    </textarea>
    <input type="hidden" name="condition" value="象过河软件"/>
    <input type="hidden" name="zoneName" value="郑州"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="gpsLongitude" value="121.491909"/>
    <input type="hidden" name="gpsLatitude" value="31.233234"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getHotCompanies">10、热门企业</div>
<form action="/recruitcmbs/index/getHotCompanies.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getHotCompanies.htm
   //入参

   //出参
        企业id:companyId
        企业名称：companyName
        被投递次数：count

    </textarea>
    <input type="submit" value="调用接口"/>
</form>


<div id="getHotPositions">11、热门职位</div>
<form action="/recruitcmbs/index/getHotPositions.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getHotPositions.htm
   //入参

   //出参
        职位类型id:jobKindsId
        职位类型名称：jobKindsName
        被投递次数：count

    </textarea>
    <input type="submit" value="调用接口"/>
</form>


<div id="getSubjects">12、轮播图</div>
<form action="/recruitcmbs/index/getSubjects.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getSubjects.htm
   //入参

   //出参
        轮播图打开地址：sujectUrl
        轮播图路径：relativePath
    </textarea>
    <input type="submit" value="调用接口"/>
</form>


<div id="getIndexKinds">13、首页一级分类</div>
<form action="/recruitcmbs/index/getIndexKinds.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getIndexKinds.htm
   //入参

   //出参
        分类id：id
        分类名称：name
        图片路径：realUrl
    </textarea>
    <input type="submit" value="调用接口"/>
</form>


<div id="getPositionsList">14、根据分类id查职位</div>
<form action="recruitcmbs/position/getPositionsList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/position/getPositionsList.htm
   //入参
       职位分类id： kindsId
        经度：gpsLongitude
        纬度：gpsLatitude
        城市名称：zoneName
        用户id:memberId(登陆传memberId,未登录传zoneName)
        page
        pagesize

   //出参
         企业id:companyId,
        企业名称：companyName,
        企业规模：companySize,
        纬度：gpsLatitude,
        经度：gpsLongitude,
        头像地址：logoRealPath,
        区域id:zoneId,
        区域名称：zoneName,
        详细地址:address,
        职位id:positionId,
        职位名称：jobName,
        薪资：salary
        创建时间：createDate

    </textarea>
    <input type="hidden" name="kindsId" value="15"/>
    <input type="hidden" name="zoneName" value="郑州"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="gpsLongitude" value="121.491909"/>
    <input type="hidden" name="gpsLatitude" value="31.233234"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getIndexCompanies">15、首页公司列表</div>
<form action="/recruitcmbs/index/getIndexCompanies.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/index/getIndexCompanies.htm
   //入参
        城市名称：zoneName
        用户id:memberId(如果用户登陆传memberId,未登录传zoneName)
        page
        pagesize

   //出参
            企业id: companyId,
			企业地址：address,
			企业名称：companyName,
			企业规模：comapnySize,
			头像路径：logoRealPath,
			行业名称：industryName
            职位名称：position
            职位个数：positionNum

    </textarea>
    <input type="hidden" name="zoneName" value="郑州"/>
    <input type="hidden" name="zoneName" value="1000003"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="get">16、公司详情</div>
<form action="recruitcmbs/companyInfo/get.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/companyInfo/get.htm
   //入参
        企业id:id

   //出参

    </textarea>
    <input type="hidden" name="id" value="1000020"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getCompanyPositions">17、公司热招职位</div>
<form action="recruitcmbs/companyInfo/getCompanyPositions.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/companyInfo/getCompanyPositions.htm
   //入参
        企业id:id
        page
        pagesize

   //出参

    </textarea>
    <input type="hidden" name="id" value="1000020"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getPositionsListPage">18、职位列表分页查询</div>
<form action="recruitcmbs/position/getPositionsListPage.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/position/getPositionsListPage.htm
   //入参
        用户id:memberId(登录时传)
        城市名称：zoneName（未登陆时传）
        经度：gpsLongitude
        纬度：gpsLatitude
        page
        pagesize

   //出参
        企业id:companyId,
        企业名称：companyName,
        企业规模：companySize,
        纬度：gpsLatitude,
        经度：gpsLongitude,
        头像地址：logoRealPath,
        区域id:zoneId,
        区域名称：zoneName,
        详细地址:address,
        职位id:positionId,
        职位名称：jobName,
        薪资：salary
        创建时间：createDate

    </textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="zoneName" value="郑州"/>
    <input type="hidden" name="gpsLongitude" value="121.491909"/>
    <input type="hidden" name="gpsLatitude" value="31.233234"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="positionDetail">19、职位详情</div>
<form action="recruitcmbs/position/positionDetail.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/position/positionDetail.htm
   //入参
        职位id:positionId
        用户id:memberId(未登录传空)


   //出参

    </textarea>
    <input type="hidden" name="positionId" value="1000008"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="addHouse">20、收藏职位</div>
<form action="/recruitcmbs/house/addHouse.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/house/positionDetail.htm
   //入参
        职位id:positionId
        用户id:memberId


   //出参

    </textarea>
    <input type="hidden" name="positionId" value="1000008"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="screenPosition">21、职位筛选</div>
<form action="/recruitcmbs/position/screenPosition.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/position/screenPosition.htm
   //入参
        经度：longitude（距离为空时可为空）
        纬度：latitude（距离为空时可为空）
        距离:distance
        薪资:screenSalary
        公司规模：companySize
        工作经验：jobyear
        学历：qualification
        招聘人数：recruitingNum
        职位性质：jobType
        用户id:memberId(未登录时传空)

   //出参

    </textarea>
    <input type="hidden" name="longitude" value="113.606274">
    <input type="hidden" name="latitude" value="34.798449">
    <input type="hidden" name="distance" value="3"/>
    <input type="hidden" name="screenSalary" value="3"/>
    <input type="hidden" name="companySize" value="3"/>
    <input type="hidden" name="jobyear" value="3"/>
    <input type="hidden" name="qualification" value="3"/>
    <input type="hidden" name="recruitingNum" value="3"/>
    <input type="hidden" name="jobType" value="1"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="addShieldCompany">22、添加屏蔽</div>
<form action="recruitcmbs/shieldCompany/addShieldCompany.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/shieldCompany/addShieldCompany.htm
   //入参
        企业id:positionId
        用户id:memberId

   //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getShieldList">23、查询屏蔽企业列表</div>
<form action="/recruitcmbs/shieldCompany/getShieldList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/shieldCompany/getShieldList.htm
   //入参
        用户id:memberId
        page
        pagesize

   //出参

    </textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="deleteShieldCompany">24、删除屏蔽</div>
<form action="/recruitcmbs/shieldCompany/deleteShieldCompany.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/shieldCompany/deleteShieldCompany.htm
   //入参
        屏蔽id:id

   //出参

    </textarea>
    <input type="hidden" name="id" value="1"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getInterviewsByMemberId">25、求职者面试邀请记录</div>
<form action="/recruitcmbs/interview/getInterviewsByMemberId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/getInterviewsByMemberId.htm
   //入参
        用户id:memberId
        page
        pagesize

   //出参

    </textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pagesize" value="10"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="deleteMemberInterviews">26、求职者删除面试邀请记录</div>
<form action="/recruitcmbs/interview/deleteMemberInterviews.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/deleteMemberInterviews.htm
   //入参
        邀请记录id:ids(如果多条删除id间以","进行分隔)

   //出参

    </textarea>
    <input type="hidden" name="ids" value="2,3"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="clearOutMemberInterviews">27、求职者清空面试邀请记录</div>
<form action="/recruitcmbs/interview/clearOutMemberInterviews.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/clearOutMemberInterviews.htm
   //入参
       用户id:memberId

   //出参

    </textarea>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="memberApplyPositive">28、求职者申请转正</div>
<form action="/recruitcmbs/interview/memberApplyPositive.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/memberApplyPositive.htm
   //入参
       面试邀请id:interviewId

   //出参

    </textarea>
    <input type="hidden" name="interviewId" value="3"/>
    <input type="submit" value="调用接口"/>
</form>
<div id="memberApplyPositive">29、简历预览</div>
<form action="/recruitcmbs/resume/resumeInit.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/resume/resumeInit.htm
   //入参
       面试邀请id:interviewId
   //出参
    </textarea>
    <input type="hidden" name="memberId" value="1000008"/>
    <input type="hidden" name="resumeId" value="8"/>

    <input type="submit" value="调用接口"/>
</form>

</body>
</html>
