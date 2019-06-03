package cn.gog.conmentmanage.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.tencent.smtt.sdk.DownloadListener;

/**
 * Created by gujin on 2017/11/23.
 * 处理webview 链接下载
 */

public class MyWebViewDownLoadListener implements DownloadListener {

    private Context mContext;

    public MyWebViewDownLoadListener(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

        // TODO: 2017/11/23 处理下载
        
        Log.e("tag", "url="+url);
        Log.e("tag", "userAgent="+userAgent);
        Log.e("tag", "contentDisposition="+contentDisposition);
        Log.e("tag", "mimetype="+mimetype);
        Log.e("tag", "contentLength="+contentLength);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }
}
