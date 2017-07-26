<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>环信即时通讯接口</title>
</head>
<body>

<div id="getAuthToken">1.获取环信APP管理员Token</div>
<form action="recruitcmbs/huanxinCommunication/getAuthToken.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 50px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getAuthToken.htm
     </textarea>
    <br/>
    <input type="submit" value="获取环信APP管理员Token"/>
</form>

<div id="registerUsers">2.注册用户</div>
<form action="recruitcmbs/huanxinCommunication/registerUsers.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/registerUsers.htm
         入参：
         userName：用户名
         password：密码
         nickName：昵称
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="1000008">
    <input type="hidden" name="password" value="123456">
    <input type="hidden" name="nickName" value="">
    <input type="submit" value="注册用户"/>
</form>

<div id="registerUsersList">3.批量注册用户</div>
<form action="recruitcmbs/huanxinCommunication/registerUsersList.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/registerUsersList.htm
         入参：
         users1：用户1(userName,password,nickName)
         users2：用户2(userName,password,nickName)
         users3：用户3(userName,password,nickName)
     </textarea>
    <br/>
    <input type="hidden" name="users1" value="u1,111,u1">
    <input type="hidden" name="users2" value="u2,222">
    <input type="hidden" name="users3" value="u3,333,u3">
    <input type="submit" value="批量注册用户"/>
</form>

<div id="getUsers">4.获取用户信息</div>
<form action="recruitcmbs/huanxinCommunication/getUsers.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getUsers.htm
         入参：
         userName：用户名
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="10000000209">
    <input type="submit" value="获取用户信息"/>
</form>

<div id="getUsersList">5.获取用户列表</div>
<form action="recruitcmbs/huanxinCommunication/getUsersList.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getUsersList.htm
         入参：
         limit：获取数量
         cursor：获取下一页的游标
     </textarea>
    <br/>
    <input type="hidden" name="limit" value="10">
    <input type="hidden" name="cursor" value="">
    <input type="submit" value="获取用户列表"/>
</form>

<div id="deleteUsers">6.删除用户</div>
<form action="recruitcmbs/huanxinCommunication/deleteUsers.htm" enctype="multipart/form-data" method="delete">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/deleteUsers.htm
         入参：
         userName：用户名
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="lichenGpenG">
    <input type="submit" value="删除用户"/>
</form>

<div id="resetUsersPassword">7.重置用户密码</div>
<form action="recruitcmbs/huanxinCommunication/resetUsersPassword.htm" enctype="multipart/form-data" method="put">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/resetUsersPassword.htm
         入参：
         userName：用户名
         newPassword：新密码
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="13323972780">
    <input type="hidden" name="newPassword" value="9527">
    <input type="submit" value="重置用户密码"/>
</form>

<div id="updateUsersNickName">8.修改用户昵称</div>
<form action="recruitcmbs/huanxinCommunication/updateUsersNickName.htm" enctype="multipart/form-data" method="put">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/updateUsersNickName.htm
         入参：
         userName：用户名
         nickName：昵称
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="lichenGpenG">
    <input type="hidden" name="nickName" value="九五二七">
    <input type="submit" value="修改用户昵称"/>
</form>

<div id="addFriendUser">9.添加好友</div>
<form action="recruitcmbs/huanxinCommunication/addFriendUser.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/addFriendUser.htm
         入参：
         ownerUserName：添加方用户名
         friendUserName：被添加方用户名
     </textarea>
    <br/>
    <input type="hidden" name="ownerUserName" value="13323972780">
    <input type="hidden" name="friendUserName" value="u1">
    <input type="submit" value="添加好友"/>
</form>

<div id="deleteFriendUser">10.删除好友</div>
<form action="recruitcmbs/huanxinCommunication/deleteFriendUser.htm" enctype="multipart/form-data" method="delete">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/deleteFriendUser.htm
         入参：
         ownerUserName：添加方用户名
         friendUserName：被添加方用户名
     </textarea>
    <br/>
    <input type="hidden" name="ownerUserName" value="13323972780">
    <input type="hidden" name="friendUserName" value="lichenGpenG">
    <input type="submit" value="删除好友"/>
</form>

<div id="getFriendUserList">11.获取好友列表</div>
<form action="recruitcmbs/huanxinCommunication/getFriendUserList.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getFriendUserList.htm
         入参：
         userName：用户名
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="13323972780">
    <input type="submit" value="获取好友列表"/>
</form>

<div id="addBlocksUsers">12.加入黑名单</div>
<form action="recruitcmbs/huanxinCommunication/addBlocksUsers.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/addBlocksUsers.htm
         入参：
         ownerUserName：添加方用户名
         blocksUserName：被添加方用户名(多个用户时用户名用 , 分开)
     </textarea>
    <br/>
    <input type="hidden" name="ownerUserName" value="13323972780">
    <input type="hidden" name="blocksUserName" value="u2,u3">
    <input type="submit" value="加入黑名单"/>
</form>

<div id="deleteBlocksUsers">13.移除黑名单</div>
<form action="recruitcmbs/huanxinCommunication/deleteBlocksUsers.htm" enctype="multipart/form-data" method="delete">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/deleteBlocksUsers.htm
         入参：
         ownerUserName：移除方用户名
         blocksUserName：被移除方用户名
     </textarea>
    <br/>
    <input type="hidden" name="ownerUserName" value="13323972780">
    <input type="hidden" name="blocksUserName" value="u3">
    <input type="submit" value="移除黑名单"/>
</form>

<div id="getBlocksUsersList">14.获取黑名单列表</div>
<form action="recruitcmbs/huanxinCommunication/getBlocksUsersList.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getBlocksUsersList.htm
         入参：
         userName：用户名
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="13323972780">
    <input type="submit" value="获取黑名单列表"/>
</form>

<div id="getUsersStatus">15.获取用户在线状态</div>
<form action="recruitcmbs/huanxinCommunication/getUsersStatus.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getUsersStatus.htm
         入参：
         userName：用户名
     </textarea>
    <br/>
    <input type="hidden" name="userName" value="u1">
    <input type="submit" value="获取用户在线状态"/>
</form>

<div id="uploadVoiceImgFiles">16.上传语音/图片文件</div>
<form action="recruitcmbs/huanxinCommunication/uploadVoiceImgFiles.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/uploadVoiceImgFiles.htm
         入参：
         file：上传文件
     </textarea>
    <br/>
    <input type="file" name="file"/>
    <input type="submit" value="上传语音/图片文件"/>
</form>

<div id="downloadVoiceImgFiles">17.下载语音/图片文件(此功能不能用)</div>
<form action="recruitcmbs/huanxinCommunication/downloadVoiceImgFiles.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/downloadVoiceImgFiles.htm
         入参：
         UUID：成功上传文件返回的UUID
         secret：成功上传文件返回的secret
     </textarea>
    <br/>
    <input type="hidden" name="UUID" value="46689840-36e5-11e7-aa64-97ca3b9208c6"/>
    <input type="hidden" name="secret" value="RmiYSjblEeecBOGnVbUilPcrRmf4B_YPdgP2WKKiPZGynpmF"/>
    <input type="submit" value="下载语音/图片文件"/>
</form>

<div id="sendTxtMessages">17.发送文本消息</div>
<form action="recruitcmbs/huanxinCommunication/sendTxtMessages.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/sendTxtMessages.htm
         入参：
         sengUserName：发送方用户名
         receiveUserName：接收方用户名(群发时用户名用 , 分开)
         messages：消息
     </textarea>
    <br/>
    <input type="hidden" name="sengUserName" value="13323972780">
    <input type="hidden" name="receiveUserName" value="9527,9527,9527,9527,9527">
    <input type="hidden" name="messages" value="9527">
    <input type="submit" value="发送文本消息"/>
</form>

<div id="sendImgMessages">18.发送图片消息</div>
<form action="recruitcmbs/huanxinCommunication/sendImgMessages.htm" enctype="multipart/form-data" method="post">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/sendImgMessages.htm
         入参：
         sengUserName：发送方用户名
         receiveUserName：接收方用户名(群发时用户名用 , 分开)
         fileName：文件名
         UUID：成功上传文件返回的UUID
         secret：成功上传文件返回的secret
     </textarea>
    <br/>
    <input type="hidden" name="sengUserName" value="13323972780">
    <input type="hidden" name="receiveUserName" value="9527,9527,9527,9527,9527">
    <input type="hidden" name="fileName" value="9527.jsp">
    <input type="hidden" name="UUID" value="46689840-36e5-11e7-aa64-97ca3b9208c6">
    <input type="hidden" name="secret" value="RmiYSjblEeecBOGnVbUilPcrRmf4B_YPdgP2WKKiPZGynpmF">
    <input type="submit" value="发送图片消息"/>
</form>

<div id="getChatMessages">19.获取聊天记录(此功能不能用)</div>
<form action="recruitcmbs/huanxinCommunication/getChatMessages.htm" enctype="multipart/form-data" method="get">
     <textarea rows="3" cols="20" style="width: 700px;height: 100px">
         接口地址：http://192.168.3.101:8090/recruitcmbs/huanxinCommunication/getChatMessages.htm
         入参：
         cursor：获取下一页聊天记录的游码
     </textarea>
    <br/>
    <input type="hidden" name="cursor" value="">
    <input type="submit" value="获取聊天记录"/>
</form>


</body>
</html>
