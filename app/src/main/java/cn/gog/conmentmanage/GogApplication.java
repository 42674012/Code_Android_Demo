package cn.gog.conmentmanage;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;

import cn.gog.conmentmanage.db.UserCache;
import cn.jpush.android.api.JPushInterface;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/4/8
 * desc:App 应用
 */
public class GogApplication extends MultiDexApplication {
    private static GogApplication instance;
    public static boolean isStarted = false;
    public static boolean isAlive = false;
    public static String userAgent = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        isStarted = true;
        initX5();
        //初始化fresco
        Fresco.initialize(this);

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        String JPushID = JPushInterface.getRegistrationID(this);
        Log.e("JPushID",JPushID);

        if(UserCache.getMysSwitch()){
            JPushInterface.resumePush(this);
        }else {
            JPushInterface.stopPush(this);
        }
    }
    private void initX5() {

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {

                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }




    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }


    public static GogApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        if (instance == null)
            throw new IllegalStateException();
        return instance.getApplicationContext();
    }

}
