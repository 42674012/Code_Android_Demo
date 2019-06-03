package cn.gog.conmentmanage.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import common.ContextHolder;
import common.utils.LogUtil;
import common.vo.LocationResp;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/14 on 14:23
 * desc:
 */

public class SharedPreferencesUtils {

    static final String share_key_adinfo = "adinfo";
    static final String share_key_red = "red";
    static final String share_key_address = "address";
    static LocationResp locationResp = null;
//    static final String inviteCodeKey = "inviteCode";

//    public static String getInviteCode( ){
//        try {
//            return  getObjectFromShare(inviteCodeKey).toString();
//        } catch (Exception ex) {
//            LogUtil.e(ex.getMessage(), true);
//        }
//        return "";
//    }
//    public static void setInvideCode( String invideCode){
//        setObjectToShare(invideCode, inviteCodeKey);
//    }
    public static LocationResp readLastLocation() {
        try {
            return (LocationResp) getObjectFromShare(share_key_address);
        } catch (Exception ex) {
            LogUtil.e(ex.getMessage(), true);
        }
        return null;
    }

    public static void saveLastLocation(LocationResp resp) {
        locationResp = resp;
        setObjectToShare(resp, share_key_address);
    }

    public static String readAdInfo() {
        try {
            return  (String)getObjectFromShare(share_key_adinfo);
        } catch (Exception ex) {
            LogUtil.e(ex.getMessage(), true);
        }
        return null;
    }

    public static boolean saveAdInfo(String adInfo) {
        try {
            return setObjectToShare(adInfo, share_key_adinfo);
        } catch (Exception ex) {
            LogUtil.e(ex.getMessage(), true);
        }
        return false;
    }

    public static boolean saveRedInfo(int redAdInfo) {
        try {
            return setObjectToShare(redAdInfo, share_key_red);
        } catch (Exception ex) {
            LogUtil.e(ex.getMessage(), true);
        }
        return false;
    }

    public static int  readRedAdInfo() {
        try {
            return  (int)getObjectFromShare(share_key_red);
        } catch (Exception ex) {
            LogUtil.e(ex.getMessage(), true);
        }
        return 0;
    }
    private static boolean setObjectToShare(Serializable object, String key) {
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(ContextHolder.getContext());
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(),
                Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(key, objectStr);
        return editor.commit();
    }

    private static Object getObjectFromShare(String key) {
        SharedPreferences sharePre = PreferenceManager
                .getDefaultSharedPreferences(ContextHolder.getContext());
        try {
            String wordBase64 = sharePre.getString(key, "");
            // 将base64格式字符串还原成byte数组
            if (wordBase64 == null || wordBase64.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            // 将byte数组转换成product对象
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
