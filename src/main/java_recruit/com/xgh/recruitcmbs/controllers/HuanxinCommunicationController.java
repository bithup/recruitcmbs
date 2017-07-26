package com.xgh.recruitcmbs.controllers;

import com.alibaba.fastjson.JSONObject;
import com.xgh.recruitcmbs.basic.BaseController;
import com.xgh.recruitcmbs.services.IHuanxinCommunicationService;
import com.xgh.recruitcmbs.services.IMemberUserService;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value = "recruitcmbs/huanxinCommunication/")
public class HuanxinCommunicationController extends BaseController {

    private Logger logger = Logger.getLogger(HuanxinCommunicationController.class);

    @Autowired
    protected IHuanxinCommunicationService huanxinCommunicationService;

    @Autowired
    protected IMemberUserService memberUserService;

    /**
     * 获取token
     */
    @RequestMapping(value = "getAuthToken")
    public void getAuthToken() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
        if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token",tokenJson.get("access_token"));
            map.put("expiresIn",tokenJson.get("expires_in"));
            map.put("application",tokenJson.get("application"));
            resultMap = getResultMap("1", "获取token成功",map);
        }else if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "client_id或client_secret错误");
        }else {
            resultMap = getResultMap("0", "获取token失败");
        }
        outJson(resultMap);
    }

    /**
     * 注册
     */
    @RequestMapping(value = "registerUsers")
    public void registerUsers() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject userJson = huanxinCommunicationService.registerUsers(request);
        if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "注册用户成功",userJson);
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "用户已存在或用户名、密码为空或用户名不合法");
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行注册操作
                JSONObject userJson_ = huanxinCommunicationService.registerUsers(request);
                if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "注册用户成功",userJson_);
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-1", "用户已存在或用户名、密码为空或用户名不合法");
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新注册");
                }else {
                    resultMap = getResultMap("0", "注册用户失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新注册");
            }
        }else {
            resultMap = getResultMap("0", "注册用户失败");
        }
        outJson(resultMap);
    }

    /**
     * 批量注册
     */
    @RequestMapping(value = "registerUsersList")
    public void registerUsersList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject userJson = huanxinCommunicationService.registerUsersList(request);
        if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "批量注册用户成功",userJson);
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "用户已存在或用户名、密码为空或用户名不合法");
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行批量注册操作
                JSONObject userJson_ = huanxinCommunicationService.registerUsersList(request);
                if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "批量注册用户成功",userJson_);
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-1", "用户已存在或用户名、密码为空或用户名不合法");
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新批量注册");
                }else {
                    resultMap = getResultMap("0", "批量注册用户失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新批量注册");
            }
        }else {
            resultMap = getResultMap("0", "批量注册用户失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "getUsers")
    public void getUsers() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject userJson = huanxinCommunicationService.getUsers(request);
        if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "获取用户信息成功",userJson);
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行获取用户信息操作
                JSONObject userJson_ = huanxinCommunicationService.getUsers(request);
                if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "获取用户信息成功",userJson_);
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新获取用户信息");
                }else {
                    resultMap = getResultMap("0", "获取用户信息失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新获取用户信息");
            }
        }else {
            resultMap = getResultMap("0", "获取用户信息失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "getUsersList")
    public void getUsersList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject userListJson = huanxinCommunicationService.getUsersList(request);
        if (Integer.parseInt(String.valueOf(userListJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "获取用户列表成功",userListJson);
        }else if (Integer.parseInt(String.valueOf(userListJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行获取用户列表操作
                JSONObject userListJson_ = huanxinCommunicationService.getUsersList(request);
                if (Integer.parseInt(String.valueOf(userListJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "获取用户列表成功",userListJson_);
                }else if (Integer.parseInt(String.valueOf(userListJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-1", "token无效，请重新获取用户列表");
                }else {
                    resultMap = getResultMap("0", "获取用户列表失败");
                }
            }else {
                resultMap = getResultMap("-1", "重新获取token失败，请重新获取用户列表");
            }
        }else {
            resultMap = getResultMap("0", "获取用户列表失败");
        }
        outJson(resultMap);
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "deleteUsers")
    public void deleteUsers() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject userJson = huanxinCommunicationService.deleteUsers(request);
        if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "删除用户成功");
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(userJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行删除用户操作
                JSONObject userJson_ = huanxinCommunicationService.deleteUsers(request);
                if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "删除用户成功");
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(userJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新删除用户");
                }else {
                    resultMap = getResultMap("0", "删除用户失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新删除用户");
            }
        }else {
            resultMap = getResultMap("0", "删除用户失败");
        }
        outJson(resultMap);
    }

    /**
     * 重置用户密码
     */
    @RequestMapping(value = "resetUsersPassword")
    public void resetUsersPassword() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject resetUsersPasswordJson = huanxinCommunicationService.resetUsersPassword(request);
        if (Integer.parseInt(String.valueOf(resetUsersPasswordJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "重置用户密码成功");
        }else if (Integer.parseInt(String.valueOf(resetUsersPasswordJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(resetUsersPasswordJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行重置用户密码操作
                JSONObject resetUsersPasswordJson_ = huanxinCommunicationService.resetUsersPassword(request);
                if (Integer.parseInt(String.valueOf(resetUsersPasswordJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "重置用户密码成功");
                }else if (Integer.parseInt(String.valueOf(resetUsersPasswordJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(resetUsersPasswordJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新重置用户密码");
                }else {
                    resultMap = getResultMap("0", "重置用户密码失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新重置用户密码");
            }
        }else {
            resultMap = getResultMap("0", "重置用户密码失败");
        }
        outJson(resultMap);
    }

    /**
     * 修改用户昵称
     */
    @RequestMapping(value = "updateUsersNickName")
    public void updateUsersNickName() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject usersNickNameJson = huanxinCommunicationService.updateUsersNickName(request);
        if (Integer.parseInt(String.valueOf(usersNickNameJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "修改用户昵称成功");
        }else if (Integer.parseInt(String.valueOf(usersNickNameJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(usersNickNameJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行修改用户昵称操作
                JSONObject usersNickNameJson_ = huanxinCommunicationService.updateUsersNickName(request);
                if (Integer.parseInt(String.valueOf(usersNickNameJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "修改用户昵称成功");
                }else if (Integer.parseInt(String.valueOf(usersNickNameJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(usersNickNameJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新修改用户昵称");
                }else {
                    resultMap = getResultMap("0", "修改用户昵称失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新修改用户昵称");
            }
        }else {
            resultMap = getResultMap("0", "修改用户昵称失败");
        }
        outJson(resultMap);
    }

    /**
     * 添加好友
     */
    @RequestMapping(value = "addFriendUser")
    public void addFriendUser() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject addFriendUserJson = huanxinCommunicationService.addFriendUser(request);
        if (Integer.parseInt(String.valueOf(addFriendUserJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "添加好友成功");
        }else if (Integer.parseInt(String.valueOf(addFriendUserJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "添加方或被添加方用户不存在");
        }else if (Integer.parseInt(String.valueOf(addFriendUserJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行添加好友操作
                JSONObject addFriendUserJson_ = huanxinCommunicationService.addFriendUser(request);
                if (Integer.parseInt(String.valueOf(addFriendUserJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "添加好友成功");
                }else if (Integer.parseInt(String.valueOf(addFriendUserJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "添加方或被添加方用户不存在");
                }else if (Integer.parseInt(String.valueOf(addFriendUserJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新添加好友");
                }else {
                    resultMap = getResultMap("0", "添加好友失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新添加好友");
            }
        }else {
            resultMap = getResultMap("0", "添加好友失败");
        }
        outJson(resultMap);
    }

    /**
     * 删除好友
     */
    @RequestMapping(value = "deleteFriendUser")
    public void deleteFriendUser() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject deleteFriendUserJson = huanxinCommunicationService.deleteFriendUser(request);
        if (Integer.parseInt(String.valueOf(deleteFriendUserJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "删除好友成功");
        }else if (Integer.parseInt(String.valueOf(deleteFriendUserJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "删除方或被删除方用户不存在");
        }else if (Integer.parseInt(String.valueOf(deleteFriendUserJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行删除好友操作
                JSONObject deleteFriendUserJson_ = huanxinCommunicationService.deleteFriendUser(request);
                if (Integer.parseInt(String.valueOf(deleteFriendUserJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "删除好友成功");
                }else if (Integer.parseInt(String.valueOf(deleteFriendUserJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "删除方或被删除方用户不存在");
                }else if (Integer.parseInt(String.valueOf(deleteFriendUserJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新删除好友");
                }else {
                    resultMap = getResultMap("0", "删除好友失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新删除好友");
            }
        }else {
            resultMap = getResultMap("0", "删除好友失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取好友列表
     */
    @RequestMapping(value = "getFriendUserList")
    public void getFriendUserList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject getFriendUserListJson = huanxinCommunicationService.getFriendUserList(request);
        if (Integer.parseInt(String.valueOf(getFriendUserListJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "获取好友列表成功",getFriendUserListJson);
        }else if (Integer.parseInt(String.valueOf(getFriendUserListJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(getFriendUserListJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行获取好友列表操作
                JSONObject getFriendUserListJson_ = huanxinCommunicationService.getFriendUserList(request);
                if (Integer.parseInt(String.valueOf(getFriendUserListJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "获取好友列表成功",getFriendUserListJson_);
                }else if (Integer.parseInt(String.valueOf(getFriendUserListJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(getFriendUserListJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新获取好友列表");
                }else {
                    resultMap = getResultMap("0", "获取好友列表失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新获取好友列表");
            }
        }else {
            resultMap = getResultMap("0", "获取好友列表失败");
        }
        outJson(resultMap);
    }

    /**
     * 加入黑名单
     */
    @RequestMapping(value = "addBlocksUsers")
    public void addBlocksUsers() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject addBlocksUsersJson = huanxinCommunicationService.addBlocksUsers(request);
        if (Integer.parseInt(String.valueOf(addBlocksUsersJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "加入黑名单成功");
        }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "添加方用户不存在");
        }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-2", "被添加方用户不存在");
        }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行加入黑名单操作
                JSONObject addBlocksUsersJson_ = huanxinCommunicationService.addBlocksUsers(request);
                if (Integer.parseInt(String.valueOf(addBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "加入黑名单成功");
                }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "添加方用户不存在");
                }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-2", "被添加方用户不存在");
                }else if (Integer.parseInt(String.valueOf(addBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-3", "token无效，请重新加入黑名单");
                }else {
                    resultMap = getResultMap("0", "加入黑名单失败");
                }
            }else {
                resultMap = getResultMap("-3", "重新获取token失败，请重新加入黑名单");
            }
        }else {
            resultMap = getResultMap("0", "加入黑名单失败");
        }
        outJson(resultMap);
    }

    /**
     * 从黑名单移除
     */
    @RequestMapping(value = "deleteBlocksUsers")
    public void deleteBlocksUsers() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject deleteBlocksUsersJson = huanxinCommunicationService.deleteBlocksUsers(request);
        if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "移除黑名单成功");
        }else if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "移除方用户或被移除方用户不存在");
        }else if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行从黑名单移除操作
                JSONObject deleteBlocksUsersJson_ = huanxinCommunicationService.deleteBlocksUsers(request);
                if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "移除黑名单成功");
                }else if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "移除方用户或被移除方用户不存在");
                }else if (Integer.parseInt(String.valueOf(deleteBlocksUsersJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新从黑名单移除");
                }else {
                    resultMap = getResultMap("0", "移除黑名单失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新从黑名单移除");
            }
        }else {
            resultMap = getResultMap("0", "移除黑名单失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取黑名单列表
     */
    @RequestMapping(value = "getBlocksUsersList")
    public void getBlocksUsersList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject blocksUsersListJson = huanxinCommunicationService.getBlocksUsersList(request);
        if (Integer.parseInt(String.valueOf(blocksUsersListJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "获取黑名单列表成功",blocksUsersListJson);
        }else if (Integer.parseInt(String.valueOf(blocksUsersListJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(blocksUsersListJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行获取黑名单列表操作
                JSONObject blocksUsersListJson_ = huanxinCommunicationService.getBlocksUsersList(request);
                if (Integer.parseInt(String.valueOf(blocksUsersListJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "获取黑名单列表成功",blocksUsersListJson_);
                }else if (Integer.parseInt(String.valueOf(blocksUsersListJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(blocksUsersListJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新获取黑名单列表");
                }else {
                    resultMap = getResultMap("0", "获取黑名单列表失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新获取黑名单列表");
            }
        }else {
            resultMap = getResultMap("0", "获取黑名单列表失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取用户在线状态
     */
    @RequestMapping(value = "getUsersStatus")
    public void getUsersStatus(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject usersStatusJson = huanxinCommunicationService.getUsersStatus(request);
        if (Integer.parseInt(String.valueOf(usersStatusJson.get("statusCode")))== HttpStatus.SC_OK) {
            Map<String,Object> map = new HashMap<String, Object>();
            String stliu = String.valueOf(usersStatusJson.get("data"));
            map.put("status",stliu);
            resultMap = getResultMap("1", "获取用户在线状态成功",map);
        }else if (Integer.parseInt(String.valueOf(usersStatusJson.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
            resultMap = getResultMap("-1", "用户不存在");
        }else if (Integer.parseInt(String.valueOf(usersStatusJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行获取用户在线状态操作
                JSONObject usersStatusJson_ = huanxinCommunicationService.getUsersStatus(request);
                if (Integer.parseInt(String.valueOf(usersStatusJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    Map<String,Object> map = new HashMap<String, Object>();
                    String stliu = String.valueOf(usersStatusJson_.get("data"));
                    map.put("status",stliu);
                    resultMap = getResultMap("1", "获取用户在线状态成功",map);
                }else if (Integer.parseInt(String.valueOf(usersStatusJson_.get("statusCode")))== HttpStatus.SC_NOT_FOUND){
                    resultMap = getResultMap("-1", "用户不存在");
                }else if (Integer.parseInt(String.valueOf(usersStatusJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新获取用户在线状态");
                }else {
                    resultMap = getResultMap("0", "获取用户在线状态失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新获取用户在线状态");
            }
        }else {
            resultMap = getResultMap("0", "获取用户在线状态失败");
        }
        outJson(resultMap);
    }

    /**
     * 上传语音、图片
     * @throws IOException
     */
    @RequestMapping(value = "uploadVoiceImgFiles")
    public void uploadVoiceImgFiles() throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject voiceImgJson = huanxinCommunicationService.uploadVoiceImgFiles(request);
        if (Integer.parseInt(String.valueOf(voiceImgJson.get("statusCode")))== HttpStatus.SC_OK) {
            String entities = String.valueOf(voiceImgJson.get("entities"));
            resultMap = getResultMap("1", "上传语音/图片成功",entities);
        }else if (Integer.parseInt(String.valueOf(voiceImgJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行上传语音、图片操作
                JSONObject voiceImgJson_ = huanxinCommunicationService.uploadVoiceImgFiles(request);
                if (Integer.parseInt(String.valueOf(voiceImgJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    String entities = String.valueOf(voiceImgJson_.get("entities"));
                    resultMap = getResultMap("1", "上传语音/图片成功",entities);
                }else if (Integer.parseInt(String.valueOf(voiceImgJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-1", "token无效，请重新上传语音、图片");
                }else {
                    resultMap = getResultMap("0", "上传语音/图片失败");
                }
            }else {
                resultMap = getResultMap("-1", "重新获取token失败，请重新上传语音、图片");
            }
        }else {
            resultMap = getResultMap("0", "上传语音/图片失败");
        }
        outJson(resultMap);
    }

    /**
     * 下载语音、图片
     */
    @RequestMapping(value = "downloadVoiceImgFiles")
    public void downloadVoiceImgFiles() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject voiceImgJson = huanxinCommunicationService.downloadVoiceImgFiles(request);
        if (Integer.parseInt(String.valueOf(voiceImgJson.get("statusCode")))== HttpStatus.SC_OK) {
            String entities = String.valueOf(voiceImgJson.get("entities"));
            resultMap = getResultMap("1", "下载语音/图片成功",entities);
        }else if (Integer.parseInt(String.valueOf(voiceImgJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行下载语音、图片操作
                JSONObject voiceImgJson_ = huanxinCommunicationService.downloadVoiceImgFiles(request);
                if (Integer.parseInt(String.valueOf(voiceImgJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    String entities = String.valueOf(voiceImgJson_.get("entities"));
                    resultMap = getResultMap("1", "下载语音/图片成功",entities);
                }else if (Integer.parseInt(String.valueOf(voiceImgJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-1", "token无效，请重新下载语音、图片");
                }else {
                    resultMap = getResultMap("0", "下载语音/图片失败");
                }
            }else {
                resultMap = getResultMap("-1", "重新获取token失败，请重新下载语音、图片");
            }
        }else {
            resultMap = getResultMap("0", "下载语音/图片失败");
        }
        outJson(resultMap);
    }

    /**
     * 发送文本消息
     */
    @RequestMapping(value = "sendTxtMessages")
    public void sendTxtMessages() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject txtMessagesJson = huanxinCommunicationService.sendTxtMessages(request);
        if (Integer.parseInt(String.valueOf(txtMessagesJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "发送文本消息成功");
        }else if (Integer.parseInt(String.valueOf(txtMessagesJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "消息结构错误");
        }else if (Integer.parseInt(String.valueOf(txtMessagesJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行发送文本消息操作
                JSONObject txtMessagesJson_ = huanxinCommunicationService.sendTxtMessages(request);
                if (Integer.parseInt(String.valueOf(txtMessagesJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "发送文本消息成功");
                }else if (Integer.parseInt(String.valueOf(txtMessagesJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-1", "消息结构错误");
                }else if (Integer.parseInt(String.valueOf(txtMessagesJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新发送文本消息");
                }else {
                    resultMap = getResultMap("0", "发送文本消息失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新发送文本消息");
            }
        }else {
            resultMap = getResultMap("0", "发送文本消息失败");
        }
        outJson(resultMap);
    }

    /**
     * 发送图片消息
     */
    @RequestMapping(value = "sendImgMessages")
    public void sendImgMessages() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject imgMessagesJson = huanxinCommunicationService.sendImgMessages(request);
        if (Integer.parseInt(String.valueOf(imgMessagesJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "发送图片消息成功");
        }else if (Integer.parseInt(String.valueOf(imgMessagesJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "消息结构错误");
        }else if (Integer.parseInt(String.valueOf(imgMessagesJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行下载语音、图片操作
                JSONObject imgMessagesJson_ = huanxinCommunicationService.sendImgMessages(request);
                if (Integer.parseInt(String.valueOf(imgMessagesJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "发送图片消息成功");
                }else if (Integer.parseInt(String.valueOf(imgMessagesJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-1", "消息结构错误");
                }else if (Integer.parseInt(String.valueOf(imgMessagesJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新发送图片信息");
                }else {
                    resultMap = getResultMap("0", "发送图片消息失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新发送图片信息");
            }
        }else {
            resultMap = getResultMap("0", "发送图片消息失败");
        }
        outJson(resultMap);
    }

    /**
     * 获取聊天记录
     * @throws MalformedURLException
     * @throws URISyntaxException
     */
    @RequestMapping(value = "getChatMessages")
    public void getChatMessages() throws MalformedURLException, URISyntaxException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject chatMessagesJson = huanxinCommunicationService.getChatMessages(request);
        if (Integer.parseInt(String.valueOf(chatMessagesJson.get("statusCode")))== HttpStatus.SC_OK) {
            resultMap = getResultMap("1", "获取聊天记录成功");
        }else if (Integer.parseInt(String.valueOf(chatMessagesJson.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
            resultMap = getResultMap("-1", "消息结构错误");
        }else if (Integer.parseInt(String.valueOf(chatMessagesJson.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
            //token无效时重新获取，存放在数据库中
            JSONObject tokenJson = huanxinCommunicationService.getAuthToken();
            if (Integer.parseInt(String.valueOf(tokenJson.get("statusCode")))== HttpStatus.SC_OK) {
                //重新获取token后再次执行下载语音、图片操作
                JSONObject chatMessagesJson_ = huanxinCommunicationService.getChatMessages(request);
                if (Integer.parseInt(String.valueOf(chatMessagesJson_.get("statusCode")))== HttpStatus.SC_OK) {
                    resultMap = getResultMap("1", "获取聊天记录成功");
                }else if (Integer.parseInt(String.valueOf(chatMessagesJson_.get("statusCode")))== HttpStatus.SC_BAD_REQUEST){
                    resultMap = getResultMap("-1", "消息结构错误");
                }else if (Integer.parseInt(String.valueOf(chatMessagesJson_.get("statusCode")))== HttpStatus.SC_UNAUTHORIZED){
                    resultMap = getResultMap("-2", "token无效，请重新获取聊天记录");
                }else {
                    resultMap = getResultMap("0", "获取聊天记录失败");
                }
            }else {
                resultMap = getResultMap("-2", "重新获取token失败，请重新获取聊天记录");
            }
        }else {
            resultMap = getResultMap("0", "获取聊天记录失败");
        }
        outJson(resultMap);
    }


}