<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>接口操作测试</title>
</head>
<body>

<div>1.查询企业信息</div>
<form action="recruitcmbs/companyInfo/get.htm" method="get">
    <input type="text" name="id" value="1000020" style="width:100px" maxlength="32" placeholder="企业id"/>
    <input type="submit" value="调用接口"/>
</form>

<div>1.首页头部职业分类</div>
<form action="recruitcmbs/kinds/topIndexKinds.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/kinds/topIndexKinds.htm

    </textarea>
    <input type="text" name="page" value="1"/>
    <input type="text" name="pageSize" value="4"/>
    <input type="submit" value="调用接口"/>
</form>

<div>2.首页搜索,首页列表展示</div>
<form action="recruitcmbs/companyInfo/companyResearch.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/companyResearch.htm

    </textarea>
    <%--    <input type="text" name="jobName" value="java"/>--%>
    <input type="text" name="jobId" value="15"/>
    <%-- <input type="text" name="zoneName" value="郑州"/>--%>
    <input type="text" name="companyId" value="1000001"/>
    <input type="submit" value="调用接口"/>
</form>
<div>3.投递职位筛选</div>
<form action="recruitcmbs/resumeDelivery/screenResumeDelivery.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/resumeDelivery/screenResumeDelivery.htm

    </textarea>
    <input type="text" name="education" value="2"/>
    <%--<input type="text" name="workYear" value="3"/>
       <input type="text" name="jobType" value="1"/>
       <input type="text" name="zoneId" value="201891"/>--%>
    <input type="submit" value="调用接口"/>
</form>

<div>4.企业注册</div>
<form action="recruitcmbs/companyInfo/register.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/register.htm

    </textarea>
    <input type="text" name="account" value="18639716815"/>
    <input type="text" name="mobile" value="18639716815"/>
    <input type="text" name="validationCode" value="24602"/>
    <input type="text" name="newPassword" value="123456"/>
    <input type="text" name="confirmPassword" value="123456"/>
    <input type="submit" value="调用接口"/>
</form>

<div>5.发送短信验证码</div>
<form action="recruitcmbs/shortMessage/sendMessage.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/shortMessage/sendMessage.htm

     type=0是注册调用短信接口,1是找回密码短信接口
    </textarea>
    <input type="text" name="mobile" value="18639716815"/>
    <input type="text" name="type" value="0"/>
    <input type="submit" value="调用接口"/>
</form>

<div>6.判断该登录账号是否已经注册</div>
<form action="recruitcmbs/companyInfo/isAlreadyRegister.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/isAlreadyRegister.htm

    </textarea>
    <input type="text" name="account" value="18639716815"/>
    <input type="submit" value="调用接口"/>
</form>

<div>7.判断该绑定账号是否已经注册</div>
<form action="recruitcmbs/companyInfo/mobileIsAlreadyRegister.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/mobileIsAlreadyRegister.htm

    </textarea>
    <input type="text" name="mobile" value="18639716815"/>
    <input type="submit" value="调用接口"/>
</form>

<div>8.企业登录</div>
<form action="recruitcmbs/companyInfo/login.htm" method="get">
 <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/login.htm

    </textarea>
    <input type="text" name="account" value="186397168151"/>
    <input type="text" name="password" value="1234561"/>
    <input type="submit" value="调用接口"/>
</form>

<div>9.企业找回账号密码</div>
<form action="recruitcmbs/shortMessage/findPassword.htm" method="get">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/shortMessage/findPassword.htm

    </textarea>
    <input type="text" name="mobile" value="18639716815"/>
    <input type="text" name="type" value="1"/>
    <input type="submit" value="调用接口"/>
</form>

<div>10.企业修改密码</div>
<form action="recruitcmbs/companyInfo/modifyPassword.htm" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/companyInfo/modifyPassword.htm

    </textarea>
    <input type="hidden" name="memberId" value="1000020"/>
    <input type="hidden" name="password" value="654321"/>
    <input type="hidden" name="newPassword" value="123456"/>
    <input type="hidden" name="confirmPassword" value="123456"/>
    <input type="submit" value="调用接口"/>
</form>

<div>11.企业发布,修改职位</div>
<form action="recruitcmbs/position/add.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/add.htm

    </textarea>

    <input type="hidden" name="companyId" value="1000020"/>
    <input type="hidden" name="jobName" value="java研发工程师"/>
    <input type="hidden" name="jobKindsId" value="15"/>
    <input type="hidden" name="salaryMin" value="6k"/>
    <input type="hidden" name="salaryMax" value="8k"/>
    <input type="hidden" name="expiryDate" value="2016-12-01 10:00:00">
    <input type="hidden" name="qualification" value="1"/>
    <input type="hidden" name="experience" value="2"/>
    <input type="hidden" name="jobType" value="1"/>
    <input type="hidden" name="zoneName" value="北京"/>
    <input type="hidden" name="recruitingNum" value="10"/>
    <input type="hidden" name="jobDescription" value="完成分配的代码编写工作"/>
    <input type="hidden" name="positionLabel" value="1,2,3,4,5"/>
    <input type="submit" value="调用接口"/>
</form>


<div>12.企业职位列表</div>
<form action="recruitcmbs/position/getPositionList.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/getPositionList.htm
    1.正常职位 2.暂停
    </textarea>
    <input type="hidden" name="companyId" value="1000020"/>
    <input type="hidden" name="positionStatus" value="2"/>
    <input type="submit" value="调用接口"/>
</form>

<div>13.企业失效职位列表</div>
<form action="recruitcmbs/position/getExpiryPositionList.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/getExpiryPositionList.htm

    </textarea>
    <input type="hidden" name="companyId" value="1000020"/>
    <input type="submit" value="调用接口"/>
</form>

<div>14.暂停企业职位</div>
<form action="recruitcmbs/position/modifyPositionStatus.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/modifyPositionStatus.htm
     type:1暂停职位,2恢复职位
    </textarea>
    <input type="hidden" name="positionIds" value="1000001,1000007,1000008"/>
    <input type="hidden" name="type" value="2"/>
    <input type="submit" value="调用接口"/>
</form>

<div>15.批量删除企业职位</div>
<form action="recruitcmbs/position/batchDeleteByIdList.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/batchDeleteByIdList.htm

    </textarea>
    <input type="hidden" name="positionIds" value="1000001,1000007,1000008"/>
    <input type="submit" value="调用接口"/>
</form>

<div>16.根据职位id查询职位列表,筛选求职者列表
</div>
<form action="recruitcmbs/position/getDeliverJobListByPositionId.htm" enctype="multipart/form-data" method="post">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
    接口地址：http://192.168.3.23:8090/recruitcmbs/position/getDeliverJobListByPositionId.htm

    </textarea>
    <input type="hidden" name="positionId" value="1000001"/>
    <input type="hidden" name="workYear" value="3"/>
    <input type="hidden" name="education" value="2"/>
    <input type="hidden" name="jobType" value="1"/>
    <input type="hidden" name="zoneId" value="200196"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getResume">1、求职者详情</div>
<form action="/recruitcmbs/resume/getResume.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/resume/getResume.htm

   //入参
        用户名:resumeId（不可为空）
        企业id:companyId(未登陆时可传空)
        投递id:id(如果从企业版收件箱来的需要传，其他不传)
  //出参
        resume:简历
        memberUser：用户信息
        eduExperience：教育经历
        workExperience：工作经历
        jobObjective：求职意向
        selfEvaluation：自我评价
        isStoreTalents:是否收藏（0.未收藏；1.收藏）

    </textarea>
    <input type="hidden" name="resumeId" value="3">
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="id" value="7">
    <input type="submit" value="调用接口"/>
</form>

<div id="getListPage">2、企业收藏列表</div>
<form action="/recruitcmbs/storeTalents/getListPage.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/storeTalents/getListPage.htm

   //入参
        企业id:companyId（不可为空）
        page
        pagesize
  //出参
            收藏id:id,
			企业id:companyId,
			求职者id:memberId,
			收藏时间：createDate,
			最高学历：education,
			详细地址：address,
			生日：birthday,
			求职者邮箱：email,
			求职者头像路径：headRealPath,
			姓名： realName,
			性别：sex,
			电话：telephone,
			工作年限：workYear,
			区域名称：zoneName,
			简历id resumeId,
			职位名称：jobName,
			职位分类id:kindsId

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="pagesize" value="10">
    <input type="submit" value="调用接口"/>
</form>

<div id="addStoreTalents">3、企业添加收藏</div>
<form action="/recruitcmbs/storeTalents/addStoreTalents.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/storeTalents/addStoreTalents.htm

   //入参
        企业id:companyId（不可为空）
        用户id:memberId(不可为空)
  //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="memberId" value="1000002">
    <input type="submit" value="调用接口"/>
</form>

<div id="deleteStoreTalents">4、企业删除收藏</div>
<form action="/recruitcmbs/storeTalents/deleteStoreTalents.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/storeTalents/deleteStoreTalents.htm

   //入参
        收藏id:ids(不可为空,多个时以逗号分隔，如：1,2,3,4)
  //出参

    </textarea>
    <input type="hidden" name="ids" value="1">
    <input type="submit" value="调用接口"/>
</form>


<div id="clearOutStoreTalents">5、企业清空收藏</div>
<form action="/recruitcmbs/storeTalents/clearOutStoreTalents.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/storeTalents/clearOutStoreTalents.htm

   //入参
        企业id:companyId(不可为空)
  //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="submit" value="调用接口"/>
</form>


<div id="getListPage">6、企业邀请记录</div>
<form action="/recruitcmbs/interview/getListPage.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/getListPage.htm

   //入参
        企业id:companyId（不可为空）
        page
        pagesize
  //出参
            邀请记录id:id,
			企业id:companyId,
			求职者id:memberId,
			邀请时间：createDate,
			最高学历：education,
			详细地址：address,
			生日：birthday,
			求职者邮箱：email,
			求职者头像路径：headRealPath,
			姓名： realName,
			性别：sex,
			电话：telephone,
			工作年限：workYear,
			区域名称：zoneName,
			简历id resumeId,
			职位名称：jobName,
			职位分类id:kindsId

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="pagesize" value="10">
    <input type="submit" value="调用接口"/>
</form>


<div id="addInterview">7、企业发送面试邀请</div>
<form action="/recruitcmbs/interview/addInterview.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/addInterview.htm

   //入参
        企业id:companyId（不可为空）
        用户id:memberId(不可为空)
  //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="memberId" value="1000003">
    <input type="submit" value="调用接口"/>
</form>


<div id="deleteInterview">8、企业删除面试邀请</div>
<form action="/recruitcmbs/interview/deleteInterview.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/deleteInterview.htm

   //入参
        收藏id:ids(不可为空,多个时以逗号分隔，如：1,2,3,4)
  //出参

    </textarea>
    <input type="hidden" name="ids" value="1">
    <input type="submit" value="调用接口"/>
</form>


<div id="clearOutInterview">9、企业清空面试邀请</div>
<form action="/recruitcmbs/interview/clearOutInterview.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/clearOutInterview.htm

   //入参
        企业id:companyId(不可为空)
  //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="submit" value="调用接口"/>
</form>


<div id="resumeBox">10、收件箱（待处理、不合适）</div>
<form action="recruitcmbs/resumeDelivery/resumeBox.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/resumeDelivery/resumeBox.htm

   //入参
        企业id:companyId(不可为空)
        类型：type(0、未处理4、不合适)
        page
        pagesize
  //出参
            简历投递id:id,
			投递状态:deliveryStatus(0未处理4不合适),
            是否查看：isView(1已查看0未查看),
			求职者id:memberId,
            简历id:resumeId,
            职位id:positionId
			投递时间：createDate,
			最高学历：education,
			详细地址：address,
			生日：birthday,
			求职者邮箱：email,
			求职者头像路径：headRealPath,
			姓名： realName,
			性别：sex,
			电话：telephone,
			工作年限：workYear,
			区域名称：zoneName,
			简历id resumeId,
			职位名称：jobName,
			职位分类id:kindsId

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="type" value="0">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="pagesize" value="10">
    <input type="submit" value="调用接口"/>
</form>

<div id="unsuit">11、收件箱不合适操作</div>
<form action="/recruitcmbs/resumeDelivery/unsuit.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/resumeDelivery/unsuit.htm

   //入参
        简历投递id:id
  //出参

    </textarea>
    <input type="hidden" name="id" value="2">
    <input type="submit" value="调用接口"/>
</form>


<div id="getInterviews">12、待面试、待确认</div>
<form action="/recruitcmbs/interview/getInterviews.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/getInterviews.htm

   //入参
        企业id:companyId(不可为空)
        类型：type(0、待面试1、待确认)
        page
        pagesize
  //出参
            面试邀请id:id,
			企业id:companyId,
			求职者id:memberId,
			邀请时间：createDate,
			最高学历：education,
			详细地址：address,
			生日：birthday,
			求职者邮箱：email,
			求职者头像路径：headRealPath,
			姓名： realName,
			性别：sex,
			电话：telephone,
			工作年限：workYear,
			区域名称：zoneName,
			简历id resumeId,
			职位名称：jobName,
			职位分类id:kindsId

    </textarea>
    <input type="hidden" name="companyId" value="1000020">
    <input type="hidden" name="type" value="0">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="pagesize" value="10">
    <input type="submit" value="调用接口"/>
</form>


<div id="updateStatus">13、待面试、待确定合适/不合适操作</div>
<form action="/recruitcmbs/interview/updateStatus.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/updateStatus.htm

   //入参
        面试邀请id:id
        状态：status(1、待面试合适；4、待面试/待确定不合适)
  //出参

    </textarea>
    <input type="hidden" name="id" value="1">
    <input type="hidden" name="status" value="1">
    <input type="submit" value="调用接口"/>
</form>

<div id="becomeRegular">14、企业版转正操作</div>
<form action="/recruitcmbs/interview/becomeRegular.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/interview/becomeRegular.htm

   //入参
        面试邀请id:id
  //出参

    </textarea>
    <input type="hidden" name="id" value="1">
    <input type="submit" value="调用接口"/>
</form>

<div id="boundPhoneNum">15、绑定手机号</div>
<form action="recruitcmbs/companyInfo/boundPhoneNum.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/companyInfo/boundPhoneNum.htm

   //入参
        企业id:id
        手机号:phoneNum
        验证码：validationCode

  //出参

    </textarea>
    <input type="hidden" name="id" value="2">
    <input type="hidden" name="phoneNum" value="15803882486">
    <input type="hidden" name="validationCode" value="452315">
    <input type="submit" value="调用接口"/>
</form>


<div id="getIndustryList">16、行业列表</div>
<form action="/recruitcmbs/industry/getIndustryList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/industry/getIndustryList.htm

   //入参

  //出参

    </textarea>
    <input type="submit" value="调用接口"/>
</form>


<div id="perfectCompanyInfo">17、修改企业信息</div>
<form action="/recruitcmbs/companyInfo/perfectCompanyInfo.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/companyInfo/perfectCompanyInfo.htm
   //入参
        公司id:id
        公司logo:logoPic
        公司名称：companyName
        公司规模：companySize
        行业id:industryId
        行业名称：industryName
        微信号：weixinNum
        接收简历邮箱：email
        联系电话：telephone
        公司简介：intro
        官网地址：companyUrl
        城市名称: zoneName
        详细地址：address
        经度：gpsLongitude
        纬度：gpsLatitude

  //出参

    </textarea>
    <input type="hidden" name="id" value="1000020">
    <input type="hidden" name="companyName" value="象过河软件">
    <input type="hidden" name="zoneName" value="郑州">
    <input type="hidden" name="address" value="河南省郑州市中原区西三环大学科技园">
    <input type="hidden" name="companySize" value="3">
    <input type="hidden" name="industryId" value="4">
    <input type="hidden" name="industryName" value="计算机软件">
    <input type="hidden" name="weixinNum" value="15803882486">
    <input type="hidden" name="email" value="1205776447@qq.com">
    <input type="hidden" name="telephone" value="15803882486">
    <input type="hidden" name="intro" value="专业进销存管理软件">
    <input type="hidden" name="companyUrl" value="www.fsq.com">
    <input type="hidden" name="gpsLongitude" value="113.606274">
    <input type="hidden" name="gpsLatitude" value="34.798449">
    <input type="file" name="logoPic"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getZoneCodeByName">18、根据城市名称显示下辖各区</div>
<form action="/recruitcmbs/zone/getZoneCodeByName.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http:http://192.168.3.99:8091/recruitcmbs/zone/getZoneCodeByName.htm
   //入参
   zoneName:城市名称
   //出参
   id:区域id
   code:区域编码
   name:区域名称
    </textarea>
    <input type="hidden" name="zoneName" value="郑州"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getByPid">19、根据父id查询职位分类列表</div>
<form action="/recruitcmbs/kinds/getByPid.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/kinds/getByPid.htm
   //入参
   parentId:父id（第一级为0）
   //出参
   id:分类id
   name:分类名称
    </textarea>
    <input type="hidden" name="parentId" value="0"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getWalletBalance">20、钱包</div>
<form action="/recruitcmbs/fundAccount/getWalletBalance.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/fundAccount/getWalletBalance.htm
   //入参
        用户id:dataId
        用户类型：dataType(1.求职者；2.企业)
   //出参
        钱包余额:purseBalance
    </textarea>
    <input type="hidden" name="dataId" value="1000020"/>
    <input type="hidden" name="dataType" value="2"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getItemList">21、字典数据接口</div>
<form action="recruitcmbs/dictionary/getItemList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/dictionary/getItemList.htm
   //入参
        字典类目：item
   //出参
        code:键
        value:值
    </textarea>
    <input type="hidden" name="item" value="edu"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getTransactionList">22、交易明细</div>
<form action="recruitcmbs/transaction/getTransactionList.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/transaction/getTransactionList.htm
   //入参
        用户id：dataId
        用户类型：dataType
   //出参

    </textarea>
    <input type="hidden" name="dataId" value="1000020"/>
    <input type="hidden" name="dataType" value="2"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="uploadCredentials">23、上传企业资质</div>
<form action="recruitcmbs/companyInfo/uploadCredentials.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/companyInfo/uploadCredentials.htm
   //入参
        企业id：companyId
        企业资质文件：credentialsPic
   //出参

    </textarea>
    <input type="hidden" name="companyId" value="1000020"/>
    <input type="file" name="credentialsPic"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="addRecommend">24、我有好创意,我想挑毛病,我要吐槽一下
</div>
<form action="/recruitcmbs/recommend/addRecommend.htm" enctype="multipart/form-data" method="POST">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:8091/recruitcmbs/recommend/addRecommend.htm
     入参:
     (memberid ;//提交建议,吐槽者id not null)
     (kind;//类型  1.创意 2.挑毛病 3.吐槽 not null)
     (context;//'内容' not null)
     (remark;//'回复' 可以传空)
    (data3;//用户类型1、求职者；2、企业 not null)
     出参:

</textarea>
    <input type="hidden" name="context" value="我的反馈内容"/>
    <input type="hidden" name="kind" value="1"/>
    <input type="hidden" name="remart" value="加薪加薪加薪"/>
    <input type="hidden" name="memberId" value="1000003"/>
    <input type="hidden" name="data3" value="1"/>
    <input type="submit" value="挑毛病&吐槽&建议添加"/>
</form>

<div id="getRecommendList">25、我有好创意,我想挑毛病,我要吐槽一下列表展示</div>
<form action="/recruitcmbs/recommend/getRecommendList.htm" enctype="multipart/form-data">
<textarea rows="3" cols="20" style="width: 700px;height: 200px">
     接口地址：http://192.168.3.99:8091/recruitcmbs/recommend/getRecommendList.htm
     入参:
     page:
     pageSize:
     kind:
     1.创意 2.挑毛病 3.吐槽
     出参:
     (memberid ;//提交建议,吐槽者)
     (kind;//类型  1.创意 2.挑毛病 3.吐槽)
     (context;//'内容')
     (remark;//'回复')

</textarea>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="pageSize" value="10"/>
    <input type="hidden" name="kind" value="1"/>
    <input type="submit" value="挑毛病&吐槽&建议列表接口"/>
</form>


<div id="addRecharge">26、充值</div>
<form action="recruitcmbs/recharge/addRecharge.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/recharge/addRecharge.htm
   //入参
        企业id：memberId
        充值金额：rechargeAmount
   //出参
        充值记录id（类似于原来的订单id）

    </textarea>
    <input type="hidden" name="memberId" value="1000020"/>
    <input type="hidden" name="rechargeAmount" value="1000"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="getCourseListByMemId24">27、显示会员绑定的银行卡列表</div>
<form action="/recruitcmbs/bankNo/getBankNoByMemId.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/bankNo/getBankNoByMemId.htm
   //入参
        memberId:会员id
   //出参
    </textarea>
    <input type="text" name="memberId" value="1000020" width="32" maxlength="32"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId25">28、默认绑定银行卡</div>
<form action="/recruitcmbs/bankNo/setDefaultBankNo.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/bankNo/setDefaultBankNo.htm
   //入参
        memberId:会员id
        bankNoId:银行卡号id
   //出参
    </textarea>
    <input type="text" name="memberId" value="1000020"/>
    <input type="text" name="bankNoId" value="1"/>
    <input type="submit" value="调用接口"/>
</form>

<div id="getCourseListByMemId26">29、解除绑定的银行卡</div>
<form action="/recruitcmbs/bankNo/unbindBanking.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/bankNo/unbindBanking.htm
   //入参
        bankNoId:银行卡号id
   //出参
    </textarea>
    <input type="text" name="bankNoId" value="1"/>
    <input type="submit" value="调用接口"/>
</form>


<div id="alipayMoneyApp">30、App支付宝支付</div>
<form action="/recruitcmbs/alipay/alipayMoneyApp.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/alipay/alipayMoneyApp.htm
   //入参
       rechargeId://充值id

   //出参

    </textarea>
    <input type="hidden" name="rechargeId" value="1">
    <input type="submit" value="调用接口"/>
</form>

<div id="alipayMoneyApp">31、生成二维码</div>
<form action="/recruitcmbs/memberUser/getQrCodeUrl.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/getQrCodeUrl.htm
   //入参
       promotionId://
   //出参

    </textarea>
    <input type="hidden" name="promotionId" value="100085">
    <input type="submit" value="调用接口"/>
</form>

<div id="alipayMoneyApp">32、设置修改支付密码</div>
<form action="/recruitcmbs/memberUser/getQrCodeUrl.htm" enctype="multipart/form-data" method="POST">
    <textarea rows="3" cols="20" style="width: 700px;height: 200px">
   接口地址：http://192.168.3.99:8091/recruitcmbs/memberUser/getQrCodeUrl.htm
   //入参
       promotionId://
   //出参

    </textarea>
    <input type="hidden" name="promotionId" value="100085">
    <input type="submit" value="调用接口"/>
</form>


<form name="epauthpay" action="/add/pay/xyPay.htm" method="get">
    <input type="text" name="order_no" width="32" maxlength="32"
           placeholder="商户订单号"/>
    <input type="text" name="order_amount" value="1.00" style="width:100px" maxlength="32" placeholder="订单金额"/>
    <input type="text" name="order_title" value="SDK测试订单" width="32" maxlength="12" placeholder="订单标题"/>
    <input type="text" name="order_desc" value="欢迎使用收付直通车" width="32" maxlength="32" placeholder="订单描述"/>
    <input type="hidden" name="redirect_type" value="ep_authpay"/><br/>
    <input type="submit" value="点此跳转至无签约快捷支付"/>
</form>


<div id="mobile_Login">快捷支付认证(同步)</div>
<textarea rows="3" cols="20" style="width: 700px;height: 100px">
    入参：
    acct_type：账号类型(默认为0)
    card_no：账号
    user_name：姓名
    member_id:用户id
    redirect_type：ep_auth
</textarea>

<form name="epauth" action="add/pay/xyPay.htm" method="post">
    <input type="text" name="acct_type" value="0" style="width:100px" maxlength="32" placeholder="账号类型"/>
    <input type="text" name="card_no" value="6217002550001258003" width="32" maxlength="20" placeholder="账号"/>
    <input type="text" name="user_name" value="陈强" width="32" maxlength="32" placeholder="姓名"/>
    <input type="text" name="member_id" value="10000000000" width="32" maxlength="20" placeholder="用户id"/>
    <input type="hidden" name="redirect_type" value="ep_auth"/><br/>
    <input type="submit" value="点此跳转至快捷认证"/>
</form>


</body>
</html>
