package com.xgh.recruitcmbs.services;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface IHuanxinCommunicationService {

    public int saveToken(String token);

    public JSONObject getAuthToken();

    public JSONObject registerUsers(HttpServletRequest request);

    public JSONObject registerUsersList(HttpServletRequest request);

    public JSONObject getUsers(HttpServletRequest request);

    public JSONObject getUsersList(HttpServletRequest request);

    public JSONObject deleteUsers(HttpServletRequest request);

    public JSONObject resetUsersPassword(HttpServletRequest request);

    public JSONObject updateUsersNickName(HttpServletRequest request);

    public JSONObject addFriendUser(HttpServletRequest request);

    public JSONObject deleteFriendUser(HttpServletRequest request);

    public JSONObject getFriendUserList(HttpServletRequest request);

    public JSONObject addBlocksUsers(HttpServletRequest request);

    public JSONObject deleteBlocksUsers(HttpServletRequest request);

    public JSONObject getBlocksUsersList(HttpServletRequest request);

    public JSONObject getUsersStatus(HttpServletRequest request);

    public JSONObject uploadVoiceImgFiles(HttpServletRequest request) throws IOException;

    public JSONObject downloadVoiceImgFiles(HttpServletRequest request);

    public JSONObject sendTxtMessages(HttpServletRequest request);

    public JSONObject sendImgMessages(HttpServletRequest request);

    public JSONObject getChatMessages(HttpServletRequest request) throws MalformedURLException, URISyntaxException;


}
