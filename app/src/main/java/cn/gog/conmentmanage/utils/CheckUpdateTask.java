//package cn.gog.conmentmanage.utils;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.support.v4.app.NotificationCompat;
//
//import cn.gog.conmentmanage.R;
//import cn.gog.conmentmanage.model.AppVersion;
//import cn.gog.conmentmanage.presenter.AppVersionPresenter;
//import cn.gog.conmentmanage.view.IAppUpdateView;
//import common.tmp.Constants;
//import common.utils.AppUtils;
//import common.utils.DownloadService;
//import common.utils.StringUtils;
//import common.utils.ToastUtils;
//
///**
// * 检查版本
// * Author: Zk
// * Time:2016/10/27  17:21
// */
//public class CheckUpdateTask extends AsyncTask<Void, Void, String> implements IAppUpdateView {
//
//    private ProgressDialog dialog;
//    private Context mContext;
//    private int mType;
//    private boolean mShowProgressDialog;
//    private boolean mShowToast;
//
//
//    CheckUpdateTask(Context context, int type, boolean showProgressDialog, boolean showToast) {
//
//        this.mContext = context;
//        this.mType = type;
//        this.mShowProgressDialog = showProgressDialog;
//        this.mShowToast = showToast;
//
//    }
//
//
//    protected void onPreExecute() {
//        if (mShowProgressDialog) {
//            dialog = new ProgressDialog(mContext);
//            dialog.setMessage(mContext.getString(R.string.update_dialog_checking));
//            dialog.show();
//        }
//    }
//
//
//    @Override
//    protected void onPostExecute(String result) {
//
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
//
////        if (!TextUtils.isEmpty(result)) {
////            checkUpdate();
////        }
//
//        checkUpdate();
//    }
//
//    private void checkUpdate() {
//        AppVersionPresenter presenter = new AppVersionPresenter(this);
//        presenter.getLastVersion();
//    }
//
//
//    /**
//     * Show dialog
//     */
//    private void showDialog(Context context, String description, String content, String apkUrl, boolean forcible) {
//        UpdateDialog.show(context, description, content, apkUrl, forcible);
//    }
//
//    /**
//     * Show Notification
//     */
//    private void showNotification(Context context, String content, String apkUrl) {
//        Intent myIntent = new Intent(context, DownloadService.class);
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        myIntent.putExtra(Constants.APK_DOWNLOAD_URL, apkUrl);
//        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        int smallIcon = context.getApplicationInfo().icon;
//        Notification notify = new NotificationCompat.Builder(context)
//                .setTicker(context.getString(R.string.android_auto_update_notify_ticker))
//                .setContentTitle(context.getString(R.string.android_auto_update_notify_content))
//                .setContentText(content)
//                .setSmallIcon(smallIcon)
//                .setContentIntent(pendingIntent).build();
//
//        notify.flags = Notification.FLAG_AUTO_CANCEL;
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notify);
//    }
//
//    @Override
//    protected String doInBackground(Void... args) {
//        return "";
////        return HttpUtils.get(url);
//    }
//
//
//    @Override
//    public void onDataSuccess(AppVersion appVersion) {
//
//        int localVersion = AppUtils.getVersionCode(mContext);
//        int netVersion = appVersion.getVersion();
//        //最新
//        if (localVersion >= netVersion) {
//            if (mShowToast) {
//                ToastUtils.showShort("当前已是最新版本");
//            }
//            return;
//        }
//        String detail = appVersion.getVersionInfo();
//        if (StringUtils.isNotEmpty(detail)) {
//            detail = detail.replace("\n", "<br>");
//        }
//        if (StringUtils.isEmpty(detail)) {
//            detail = "优化版本";
//        }
//        boolean forcible = appVersion.isForceUpdate();
//        String apkUrl = appVersion.getDownUrl();
//
//        if (mType == Constants.TYPE_NOTIFICATION) {
//            showNotification(mContext, detail, apkUrl);
//        } else if (mType == Constants.TYPE_DIALOG) {
//            showDialog(mContext, "", detail, apkUrl, forcible);
//        }
//
//    }
//
//    @Override
//    public void onCompleted() {
//        int i = 0;
//    }
//}
