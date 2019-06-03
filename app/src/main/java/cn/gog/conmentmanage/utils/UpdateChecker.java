//package cn.gog.conmentmanage.utils;
//
//import android.content.Context;
//import android.util.Log;
//import common.tmp.Constants;
//
///**
// * <p>
// * <p>
// * <p>
// * Author: Zk
// * Time:2016/10/27  17:25
// */
//public class UpdateChecker {
//
//    public static void checkForDialog(Context context, boolean showToast) {
//        if (context != null) {
//            new CheckUpdateTask(context, Constants.TYPE_DIALOG, true, showToast).execute();
//        } else {
//            Log.e(Constants.TAG, "The arg context is null");
//        }
//    }
//
//
//    public static void checkForNotification(Context context) {
//        if (context != null) {
//            new CheckUpdateTask(context, Constants.TYPE_NOTIFICATION, false, false).execute();
//        } else {
//            Log.e(Constants.TAG, "The arg context is null");
//        }
//
//    }
//
//
//}
