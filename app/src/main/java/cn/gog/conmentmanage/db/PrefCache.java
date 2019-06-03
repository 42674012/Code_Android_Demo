package cn.gog.conmentmanage.db;

import cn.gog.conmentmanage.GogApplication;
import common.utils.SPs;

/**
 * 类描述：SharedPreferences 数据管理类
 * 创建人：陈永祥
 * 创建时间：2017/10/11 12:01:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PrefCache {

    public static void putData(String key, Object object) {

        SPs.put(GogApplication.getAppContext(), key, object);
    }

    public static Object getData(String key, Object defaultObject) {

        return SPs.get(GogApplication.getAppContext(), key, defaultObject);
    }

    public static void removeData(String key) {
        SPs.remove(GogApplication.getAppContext(), key);
    }

    public static void clearData() {
        SPs.clear(GogApplication.getAppContext());
    }

    public boolean contains(String key) {

        return SPs.contains(GogApplication.getAppContext(), key);
    }
}
