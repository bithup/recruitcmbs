package com.xgh.recruitcmbs.util;

import com.xgh.util.PropertiesUtil;

public class ConstantUtil {

    /**
     * 缓存有效时间（秒）
     * 此处设置为7天有效时间
     */
    public final static int EXPIRY_SITE_TIME = 60 * 60 * 24 * 7;

    public static String CMBS_URL = "";

    public static String FP_URL = "";

    public static String TXT_PATH = "txt";

    public static String TEMP_PATH = "/opt/temp_path/";

    public static String SAVE_PATH = "/opt/";

    public static String SERVER_URL = "http://192.168.3.23/";

    public static String SEND_URL = "http://www.139000.com/send/gsend.asp?name={用户名}&pwd={短信密码}&dst={短信号码}&sender=&time=&txt=ccdx&msg=尊敬的客户，{短信内容}。";

    public static String SMS_ACCOUNT = "fsq_xgh";

    public static String SMS_PASSWORD = "xghwep999";

    public static String SERVER_IP = "http://192.168.3.23:8090";
    static {

        PropertiesUtil mPropertiesUtil = PropertiesUtil.getInstance("/config.properties");

        if (mPropertiesUtil.getValueByKey("fp.url") != null) {
            FP_URL = mPropertiesUtil.getValueByKey("fp.url");
        }

        if (mPropertiesUtil.getValueByKey("temp.path") != null) {
            TEMP_PATH = mPropertiesUtil.getValueByKey("temp.path");
        }

        if (mPropertiesUtil.getValueByKey("save.path") != null) {
            SAVE_PATH = mPropertiesUtil.getValueByKey("save.path");
        }

        if (mPropertiesUtil.getValueByKey("server.url") != null) {
            SERVER_URL = mPropertiesUtil.getValueByKey("server.url");
        }
        if (mPropertiesUtil.getValueByKey("txt.path") != null) {
            TXT_PATH = mPropertiesUtil.getValueByKey("txt.path");
        }

        if (mPropertiesUtil.getValueByKey("serverIp.url") != null) {
            SERVER_IP = mPropertiesUtil.getValueByKey("serverIp.url");
        }

    }

    /**
     * 获取存储的用户的相关信息的key
     */
    public enum ReqKey {

        sLock("sLock"),
        sKey("sKey"),
        sId("sId"),
        zoneCode("zoneCode"),
        //单位Id
        unitId("unitId"),
        os("os"),
        osv("osv"),
        appv("appv"),
        userKey("userKey"),
        postData("postData");

        private String _value;

        private ReqKey(String value) {
            this._value = value;
        }

        public String value() {
            return _value;
        }
    }

    /**
     * 操作结果
     */
    public enum ResultKey {

        //返回标记1、成功，0失败，-1、其他
        resultFlg("resultFlg"),
        //返回消息
        resultMsg("resultMsg"),
        //返回数据
        resultData("resultData");

        private String _value;

        private ResultKey(String value) {
            this._value = value;
        }

        public String value() {
            return _value;
        }
    }

    /**
     * 字典前缀
     */
    public class DictKeys {

        /**
         * 主表
         */
        public final static String DicMain = "dic_main";


        /**
         * 详细表
         */
        public final static String DicDetail = "dic_detail";
    }


    public enum DictClumn {
        //id
        id("id"),
        //主表Id
        dictMainId("dictMainId"),
        //编码
        code("code"),
        //对应值
        value("value"),
        //备注信息
        memo("memo"),
        //排序字段
        ord("ord");

        private String _value;

        private DictClumn(String value) {
            this._value = value;
        }

        public String value() {
            return _value;
        }
    }

    public enum DictOrderBy {
        //降序
        desc("desc"),
        //升序
        asc("asc");

        private String _value;

        private DictOrderBy(String value) {
            this._value = value;
        }

        public String value() {
            return _value;
        }
    }

    public enum FileUploadCode {
        //单位图片
        Unit("unit"),
        Shop("shop"),
        ShopLogo("shoplogo"),
        Goods("goods"),
        GoodsLogo("goodslogo"),
        Subject("subject"),
        Member("member"),
        Edu("edu"),
        MemberLogo("memberUser"),
        Teacher("teacher"),
        TeacherLogo("teacherlogo"),
        Active("active");


        private String _value = "";

        private FileUploadCode(String value) {
            this._value = value;
        }

        public String value() {
            return _value;
        }
    }
}