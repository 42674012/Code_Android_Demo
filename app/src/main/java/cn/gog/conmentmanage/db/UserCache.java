package cn.gog.conmentmanage.db;

import android.text.TextUtils;
import com.google.gson.Gson;

import java.util.List;

import cn.gog.conmentmanage.model.UserInfo;


/**
 * 类描述：用户实体缓存
 * 创建人：gujin
 * 创建时间：2018/4/19 17:33
 */
public class UserCache {


    /**
     * 缓存用户信息
     */
    private static UserInfo user;
    /**
     * 用户ID
     */
    public static final String USERINFOKEY = "user_info_key";
    public static final String USERSEARCHKEY = "user_search_key";
    public static final String MSGSWITCH = "msg_switch_key";
    public static final String GESTURE_SWITCH_KEY = "gesture_switch_key";


    /**
     * 保存登陆用户信息
     *
     * @param user
     */
    public static void put(UserInfo user) {
        Gson gson =new Gson();
        PrefCache.putData(USERINFOKEY, gson.toJson(user));
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static UserInfo get() {

        String userInfoJson = (String) PrefCache.getData(USERINFOKEY, "");
        if (!TextUtils.isEmpty(userInfoJson)) {

            Gson gson = new Gson();
            user =  gson.fromJson(userInfoJson,UserInfo.class);

        }else{
            user = null;
        }
        return user;
    }

    public static void clear() {
        PrefCache.putData(USERINFOKEY, "");
        user = null;
    }

    public static String getSearchHistory(){
        String userInfoJson = (String) PrefCache.getData(USERSEARCHKEY, "");
        return userInfoJson;
    }
    /**
     * 保存登陆用户信息
     *
     * @param
     */
    public static void putSearchHistory(List<String> keys) {
        Gson gson =new Gson();
        PrefCache.putData(USERSEARCHKEY, gson.toJson(keys));
    }


    public static Boolean getMysSwitch(){
        Boolean switchStr = (Boolean) PrefCache.getData(MSGSWITCH, true);
        return switchStr;
    }

    public static void putMysSwitch(boolean isOn) {

        PrefCache.putData(MSGSWITCH, isOn);
    }

    public static Boolean getGesSwitch(){
        Boolean switchStr = (Boolean) PrefCache.getData(GESTURE_SWITCH_KEY, false);
        return switchStr;
    }

    public static void putGesSwitch(boolean isOn) {

        PrefCache.putData(GESTURE_SWITCH_KEY, isOn);
    }
}
