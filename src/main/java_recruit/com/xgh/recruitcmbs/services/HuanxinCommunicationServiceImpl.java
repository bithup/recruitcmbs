package com.xgh.recruitcmbs.services;

import com.alibaba.fastjson.JSONObject;
import com.xgh.recruitcmbs.basic.BaseService;
import com.xgh.recruitcmbs.dao.IMemberUserDao;
import com.xgh.recruitcmbs.dao.IParameterDao;
import com.xgh.recruitcmbs.entity.MemberUser;
import com.xgh.recruitcmbs.entity.Parameter;
import com.xgh.recruitcmbs.util.ConstantUtil;
import com.xgh.recruitcmbs.util.HuanxinUtil;
import com.xgh.util.FileUtil;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("huanxinCommunicationService")
public class HuanxinCommunicationServiceImpl extends BaseService implements IHuanxinCommunicationService {

    private Logger logger = Logger.getLogger(HuanxinCommunicationServiceImpl.class);

    @Autowired
    protected IMemberUserDao memberUserDao;

    @Autowired
    protected IParameterDao parameterDao;

    /**
     * 存储token
     * @param token
     * @return
     */
    public int saveToken(String token){
        int flag = 0;
        Parameter parameter = parameterDao.getParameter();
        if (parameter!=null&&parameter.getId()>0){
            parameter.setParameter(token);
            flag = parameterDao.update(parameter);
        }else {
            Parameter parameter1 = new Parameter();
            parameter1.setParameterCode("token");
            parameter1.setParameterName("token");
            parameter1.setParameter(token);
            flag = parameterDao.add(parameter1);
        }
        return flag;
    }

    /**
     * 设置用户是否注册环信的状态
     * @param userName
     * @param statusCode
     * @return
     */
    public void updateMemberHuanxinStatus(String userName, int statusCode){
        MemberUser memberUser = memberUserDao.get(Long.parseLong(userName));
        if (memberUser!=null&&memberUser.getId()>0){
            if (statusCode==HttpStatus.SC_OK){
                memberUser.setData4("1");
            }else{
                memberUser.setData4("0");
            }
            memberUser.setUpdateDate(new Date());
            memberUserDao.update(memberUser);
        }
    }

    /**
     * 获取token
     * @return
     */
    public JSONObject getAuthToken(){
        JSONObject json = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/token";
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("Content-Type", "application/json");
        jsonParam.put("grant_type", "client_credentials");
        jsonParam.put("client_id", HuanxinUtil.client_id);
        jsonParam.put("client_secret", HuanxinUtil.client_secret);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity((entity));
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            json.put("statusCode",statusCode);
            //获取token成功后存入数据库中
            if (statusCode== HttpStatus.SC_OK){
                saveToken(String.valueOf(json.get("access_token")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 注册用户
     * @param request
     * @return
     */
    public JSONObject registerUsers(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users";
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", userName);
        jsonParam.put("password", password);
        jsonParam.put("nickname", nickName);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            json.put("statusCode",statusCode);
            //设置用户是否注册环信的状态
            updateMemberHuanxinStatus(userName,statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }


    /**
     * 批量注册用户
     * @param request
     * @return
     */
    public JSONObject registerUsersList(HttpServletRequest request){
        JSONObject json = null;
        String[] strings = new String[3];
        strings[0] = request.getParameter("users1");
        strings[1] = request.getParameter("users2");
        strings[2] = request.getParameter("users3");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users";
        HttpPost post = new HttpPost(url);
        ArrayList<JSONObject> jsonParam = new ArrayList<JSONObject>();
        for (int i=0;i<strings.length;i++){
            JSONObject jsonParam_ = new JSONObject();
            String[] usersList = strings[i].split(",");
            jsonParam_.put("username", usersList[0]);
            jsonParam_.put("password", usersList[1]);
            if (usersList.length==3){
                jsonParam_.put("nickname", usersList[2]);
            }
            jsonParam.add(jsonParam_);
        }
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    public JSONObject getUsers(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName;
        HttpGet get = new HttpGet(url);
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 获取用户列表
     * @return
     */
    public JSONObject getUsersList(HttpServletRequest request){
        JSONObject json = null;
        String limit = request.getParameter("limit");
        String cursor = request.getParameter("cursor");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/?limit="+limit+"&cursor="+cursor;
        HttpGet get = new HttpGet(url);
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 删除用户
     * @return
     */
    public JSONObject deleteUsers(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName;
        HttpDelete delete = new HttpDelete(url);
        delete.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(delete);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 重置用户密码
     * @param request
     * @return
     */
    public JSONObject resetUsersPassword(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        String newPassword = request.getParameter("newPassword");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName+"/password";
        HttpPut put = new HttpPut(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("newpassword", newPassword);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        put.setEntity(entity);
        put.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(put);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 修改用户昵称
     * @param request
     * @return
     */
    public JSONObject updateUsersNickName(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        String nickName = request.getParameter("nickName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName;
        HttpPut put = new HttpPut(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("nickname", nickName);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        put.setEntity(entity);
        put.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(put);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 添加好友
     * @return
     */
    public JSONObject addFriendUser(HttpServletRequest request){
        JSONObject json = null;
        String ownerUserName = request.getParameter("ownerUserName");
        String friendUserName = request.getParameter("friendUserName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+ownerUserName+"/contacts/users/"+friendUserName;
        HttpPost post = new HttpPost(url);
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 删除好友
     * @return
     */
    public JSONObject deleteFriendUser(HttpServletRequest request){
        JSONObject json = null;
        String ownerUserName = request.getParameter("ownerUserName");
        String friendUserName = request.getParameter("friendUserName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+ownerUserName+"/contacts/users/"+friendUserName;
        HttpDelete delete = new HttpDelete(url);
        delete.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(delete);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 获取好友列表
     * @return
     */
    public JSONObject getFriendUserList(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName+"/contacts/users";
        HttpGet get = new HttpGet(url);
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 加入黑名单
     * @param request
     * @return
     */
    public JSONObject addBlocksUsers(HttpServletRequest request){
        JSONObject json = null;
        String ownerUserName = request.getParameter("ownerUserName");
        String blocksUserName = request.getParameter("blocksUserName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+ownerUserName+"/blocks/users";
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("target_type", "users");
        //把被添加方用户名放入数组中
        String[] blocksUserNameList = blocksUserName.split(",");
        jsonParam.put("usernames", blocksUserNameList);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 移除黑名单
     * @return
     */
    public JSONObject deleteBlocksUsers(HttpServletRequest request){
        JSONObject json = null;
        String ownerUserName = request.getParameter("ownerUserName");
        String blocksUserName = request.getParameter("blocksUserName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+ownerUserName+"/blocks/users/"+blocksUserName;
        HttpDelete delete = new HttpDelete(url);
        delete.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(delete);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 获取黑名单列表
     * @param request
     * @return
     */
    public JSONObject getBlocksUsersList(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName+"/blocks/users";
        HttpGet get = new HttpGet(url);
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 获取用户在线状态
     * @param request
     * @return
     */
    public JSONObject getUsersStatus(HttpServletRequest request){
        JSONObject json = null;
        String userName = request.getParameter("userName");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/users/"+userName+"/status";
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-Type", "application/json");
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 上传语音/图片文件
     * @param request
     * @return
     * @throws IOException
     */
    public JSONObject uploadVoiceImgFiles(HttpServletRequest request) throws IOException {
        JSONObject json = null;
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        //获取到图片存入指定的位置，然后得到绝对路径
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator fileNames = multipartRequest.getFileNames();
        String name = (String) fileNames.next();
        MultipartFile myfile = multipartRequest.getFile(name);
        String fileName = myfile.getOriginalFilename();
        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
        String relative_path = formatdate.format(new Date());
        String realPath = ConstantUtil.SAVE_PATH + "/" + "huanXin" + "/" + relative_path;
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        FileUtil.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, fileName));

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/chatfiles";
        HttpPost post = new HttpPost(url);
        post.addHeader("restrict-access","true");
        post.addHeader("Authorization", "Bearer "+token);
        MultipartEntity entity = new MultipartEntity();
        FileBody fileBody = new FileBody(new File(realPath+fileName));
        entity.addPart("file",fileBody);
        post.setEntity(entity);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 下载语音/图片文件
     * @param request
     * @return
     */
    public JSONObject downloadVoiceImgFiles(HttpServletRequest request){
        JSONObject json = null;
        String UUID = request.getParameter("UUID");
        String secret = request.getParameter("secret");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/chatfiles/"+UUID;
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("thumbnail","true");
        jsonParam.put("share-secret", secret);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Accept", "application/octet-stream");
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 发送文本消息
     * @param request
     * @return
     */
    public JSONObject sendTxtMessages(HttpServletRequest request){
        JSONObject json = null;
        String sengUserName = request.getParameter("sengUserName");
        String receiveUserName = request.getParameter("receiveUserName");
        String messages = request.getParameter("messages");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/messages";
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("target_type", "users");
        //单发或群发时把接收方用户名放入数组中
        String[] receiveUserList = receiveUserName.split(",");
        jsonParam.put("target", receiveUserList);
        JSONObject jsonParam1 = new JSONObject();
        jsonParam1.put("type","txt");
        jsonParam1.put("msg",messages);
        jsonParam.put("msg",jsonParam1);
        jsonParam.put("from",sengUserName);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 发送图片消息
     * @param request
     * @return
     */
    public JSONObject sendImgMessages(HttpServletRequest request){
        JSONObject json = null;
        String sengUserName = request.getParameter("sengUserName");
        String receiveUserName = request.getParameter("receiveUserName");
        String fileName = request.getParameter("fileName");
        String UUID = request.getParameter("UUID");
        String secret = request.getParameter("secret");
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+HuanxinUtil.app_name+"/messages";
        HttpPost post = new HttpPost(url);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("target_type", "users");
        //单发或群发时把接收方用户名放入数组中
        String[] receiveUserList = receiveUserName.split(",");
        jsonParam.put("target", receiveUserList);
        JSONObject jsonParam1 = new JSONObject();
        jsonParam1.put("type","img");
        jsonParam1.put("url","https://a1.easemob.com/easemob-demo/chatdemoui/chatfiles/"+UUID);
        jsonParam1.put("filename",fileName);
        jsonParam1.put("secret",secret);
        JSONObject jsonParam1_ = new JSONObject();
        jsonParam1_.put("width",480);
        jsonParam1_.put("height",720);
        jsonParam1.put("size",jsonParam1_);
        jsonParam.put("msg",jsonParam1);
        jsonParam.put("from",sengUserName);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        post.setEntity(entity);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(post);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }

    /**
     * 分页获取聊天记录
     * @param request
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    public JSONObject getChatMessages(HttpServletRequest request) throws MalformedURLException, URISyntaxException {
        JSONObject json = null;
        Parameter parameter = parameterDao.getParameter();
        String token = parameter.getParameter();

/*        //获取token
        JSONObject tokenJson = getAuthToken();
        String token = String.valueOf(tokenJson.get("access_token"));*/

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://a1.easemob.com/"+HuanxinUtil.org_name+"/"+ HuanxinUtil.app_name+"/chatmessages?ql=select+*+where+timestamp>1403164734226";
        //解决String类型url中存在特殊符号
        URL url_ = new URL(url);
        URI uri = new URI(url_.getProtocol(), url_.getHost(), url_.getPath(), url_.getQuery(), null);
        HttpGet get = new HttpGet(uri);
        get.addHeader("Content-Type", "application/json");
        get.addHeader("Authorization", "Bearer "+token);
        try {
            //发送请求，返回结果
            HttpResponse httpResponse = httpClient.execute(get);
            //如果服务器成功地返回响应
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            json = (JSONObject) JSONObject.parse(responseContent);
            json.put("statusCode",httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            httpClient.getConnectionManager().shutdown();
            return json;
        }
    }


}