//package cn.gog.conmentmanage.utils;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.webkit.WebView;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import cn.gog.conmentmanage.R;
//import cn.gog.conmentmanage.ui.view.popupwindow.ScreenUtils;
//import common.event.ApkDownloadProcessEvent;
//import common.tmp.Constants;
//import common.utils.DownloadService;
//import common.utils.NetworkUtil;
//
//import static cn.gog.conmentmanage.R.style.showDialog;
//
///**
// * <p>
// * <p>
// * <p>
// * Author: Zk
// * Time:2016/10/27  15:53
// */
//public class UpdateDialog {
//
//    static class UpdateDialogHandler {
//
//        private ProgressDialog mypDialog;
//
//        @Subscribe(threadMode = ThreadMode.MAIN)
//        public void finishLoadConfigData(ApkDownloadProcessEvent apkDownloadProcessEvent) {
//            mypDialog.setProgress(apkDownloadProcessEvent.getProcessValue());
//            if (apkDownloadProcessEvent.getProcessValue() >= 100) {
//                EventBus.getDefault().unregister(UpdateDialogHandler.this);
//                mypDialog.dismiss();
//            }
//        }
//
//        public void showProcessDialog(Context context) {
//
//            this.mypDialog = new ProgressDialog(context);
//            //实例化
//            mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            mypDialog.setProgress(0);
//            mypDialog.setMax(100);
//            //点击对话框外面,对话框不消失
//            mypDialog.setCanceledOnTouchOutside(false);
//            mypDialog.setCancelable(false);
//            //     mypDialog.setTitle("下载进度");
//            mypDialog.setButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                    EventBus.getDefault().unregister(UpdateDialogHandler.this);
//                }
//            });
//
//            //设置ProgressDialog 的一个Button
//            mypDialog.setIndeterminate(false);
//            //设置ProgressDialog 的进度条是否不明确
//            mypDialog.setCancelable(false);
//            //设置ProgressDialog 是否可以按退回按键取消
//            mypDialog.show();
//        }
//    }
//
//    public static void showPrcessDialog(final Context context) {
//        final UpdateDialogHandler updateDialogHandler = new UpdateDialogHandler();
//
//        EventBus.getDefault().register(updateDialogHandler);
//
//        updateDialogHandler.showProcessDialog(context);
//    }
//
//    static void show(final Context context, String description, final String content, final String downloadUrl, final boolean forcible) {
//        if (isContextValid(context)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);
////            builder.setTitle(description);
////            builder.setMessage(Html.fromHtml(content))
////                    .setPositiveButton(R.string.android_auto_update_dialog_btn_download, new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
////                            goToDownload(context, downloadUrl);
////                            showPrcessDialog(context);
////                        }
////                    })
////                    .setNegativeButton(R.string.android_auto_update_dialog_btn_cancel, new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int id) {
////                            if (forcible) {
////                                //如果强制更新，则退出
////                                System.exit(0);
////                            }
////                        }
////                    });
//
//            final AlertDialog dialog = builder.create();
//            dialog.show();
//            Window window = dialog.getWindow();
//            LayoutInflater inflater = LayoutInflater.from(context);
//            View contentview = inflater.inflate(R.layout.dialog_check_version, null);
//            window.setContentView(contentview);
//            ImageView img_colose = (ImageView) contentview.findViewById(R.id.img_colose);
////            TextView txt_content = (TextView) contentview.findViewById(R.id.txt_content);
//            WebView web_content = (WebView) contentview.findViewById(R.id.web_content);
//            Button btn_down = (Button) contentview.findViewById(R.id.btn_down);
//            web_content.loadData(content, "text/html; charset=UTF-8", null);//这种写法可以正确解码
//            if (!forcible) {
//                img_colose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//
//            btn_down.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    NetworkInfo networkInfo = NetworkUtil.getNetworkInfo(context);
//                    //wifi状态下自动下载
//                    if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                        //下载
//                        goToDownload(context, downloadUrl);
//                        showPrcessDialog(context);
//                        dialog.dismiss();
//
//                    } else {
//                        //提示dialog
//                        myShowDialog(context, downloadUrl);
//                        dialog.dismiss();
//                    }
//                }
//            });
//            //点击对话框外面,对话框不消失
//            dialog.setCanceledOnTouchOutside(false);
//
//            if (forcible) {
//                dialog.setCancelable(false);
//                } else{
//                dialog.setCancelable(true);
//            }
//        }
//    }
//
//    // 弹出的对话框
//    private static Dialog mDialog;
//    private static Button mDialogSubmit, mDialogCancel;
//
//    /**
//     * 退出
//     */
//    public static void myShowDialog(Context context, String downloadUrl) {
//        mDialog = new Dialog(context, showDialog);
//        mDialog.setContentView(getDialogView(context, downloadUrl));
//        mDialog.setCancelable(true);
//        mDialog.setCanceledOnTouchOutside(true);
//        // 增加一些配置
//        Window dialogWindow = mDialog.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = ScreenUtils.getScreenWidth(context) * 85 / 100; // 宽度
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//        lp.alpha = 1.0f; // 透明度
//
//        mDialog.show();
//    }
//
//    private static View getDialogView(final Context context, final String downloadUrl) {
//        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_network_choice, null);
//
//        mDialogSubmit = (Button) dialogView.findViewById(R.id.appoint_dialog_ok);
//        mDialogCancel = (Button) dialogView.findViewById(R.id.appoint_dialog_cancel);
//        mDialogSubmit.setText("确定");
//        mDialogCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//
//        mDialogSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //下载
//                goToDownload(context, downloadUrl);
//                showPrcessDialog(context);
//                mDialog.dismiss();
//            }
//        });
//
//        return dialogView;
//    }
//
//
//    private static boolean isContextValid(Context context) {
//        return context instanceof Activity && !((Activity) context).isFinishing();
//    }
//
//
//    private static void goToDownload(Context context, String downloadUrl) {
//        Intent intent = new Intent(context.getApplicationContext(), DownloadService.class);
//        intent.putExtra(Constants.APK_DOWNLOAD_URL, downloadUrl);
//        context.startService(intent);
//    }
//
//}
