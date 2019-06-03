package common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.orhanobut.logger.Logger;

import java.util.UUID;

/**
 * Created by Administrator on 2016/9/28.
 */

public class AppUtils {


    /**
     * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     * <p>
     * 渠道标志为：
     * 1，andriod（a）
     * <p>
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        //deviceId.append("a");
        try {
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!StringUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                Logger.d("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
        }catch (Exception e){

        }
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!StringUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                Logger.d("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }

        }catch (Exception e){

        }
        try {
            //序列号（sn）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String sn = tm.getSimSerialNumber();
            if (!StringUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                Logger.d("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
        }catch (Exception e){

        }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!StringUtils.isEmpty(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                Logger.d("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }


        Logger.d("getDeviceId : ", deviceId.toString());

        return deviceId.toString();
    }


    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        SharedPreferences mShare = context.getSharedPreferences("sysCacheMap", Context.MODE_PRIVATE);
        String uuid = "";
        if (mShare != null) {
            uuid = mShare.getString("uuid", "");
        }

        if (StringUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor edit = mShare.edit();
            edit.putString("uuid", uuid);
            edit.commit();
        }

        Logger.d("getUUID : " + uuid);
        return uuid;
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        String version = "1.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        int versionCode = 1;
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


}
