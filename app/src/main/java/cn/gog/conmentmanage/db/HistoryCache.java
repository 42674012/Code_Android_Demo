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
public class HistoryCache {



    public static final String USERSEARCHKEY = "user_search_key";


    public static String getSearchHistory(){
        String userInfoJson = (String) PrefCache.getData(USERSEARCHKEY, "");
        return userInfoJson;
    }
    /**
     * 保存登陆用户信息
     *
     * @param user
     */
    public static void putSearchHistory(List<String> keys) {
        Gson gson =new Gson();
        PrefCache.putData(USERSEARCHKEY, gson.toJson(keys));
    }

}
