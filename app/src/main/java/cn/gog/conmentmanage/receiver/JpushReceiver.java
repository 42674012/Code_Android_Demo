package cn.gog.conmentmanage.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.orhanobut.logger.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.ui.activity.ArticleInfoListActivity;
import cn.gog.conmentmanage.ui.activity.DutyNoticeListActivity;
import cn.gog.conmentmanage.ui.activity.SystemInfoActivity;
import cn.jpush.android.api.JPushInterface;
import common.manager.AppStackManager;
import common.utils.LogUtil;
import common.utils.StringUtils;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/5/22 on 19:50
 * desc:广播接收器
 */

public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Bundle bundle = intent.getExtras();
            String msg = printBundle(bundle);
            Logger.e(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + msg);

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//          透传消息
                String jsonString = bundle.getString(JPushInterface.EXTRA_EXTRA);

                if (!StringUtils.isEmpty(jsonString)) {
                    String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                    //jsonString={"newsId":"","notificationType":1,"newsType":1,"params":""}
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String newsId = jsonObject.getString("newsId");
                }

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.e(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);


            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

                Log.e("jin1",JPushInterface.EXTRA_EXTRA);
                String jsonString = bundle.getString(JPushInterface.EXTRA_EXTRA);
                if (!StringUtils.isEmpty(jsonString)) {
                    String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                    //jsonString={"newsId":"","notificationType":1,"newsType":1,"params":""}
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String type = jsonObject.getString("type");

                    processCustomMessage(GogApplication.getInstance(), type);
                }

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
            Logger.d(TAG, "[MyReceiver] Unhandled intent - " + e.getMessage());
        }

    }

    private void processCustomMessage(Context context, String type ) {
        Intent intent =new Intent();
        if(TextUtils.equals(type ,"任务通知")){
             intent.setClass(context, DutyNoticeListActivity.class);

        }else if(TextUtils.equals(type ,"文章列表消息")){
            intent.setClass(context,  ArticleInfoListActivity.class);
        }else if(TextUtils.equals(type ,"系统消息")){
            intent.setClass(context,  SystemInfoActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }





    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


}