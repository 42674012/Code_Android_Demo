/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package cn.gog.conmentmanage.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;

import common.config.AppStatusConstant;
import common.config.AppStatusManager;

//import com.testin.agent.Bugout;

/**
 * Author: Gujin
 * Time: 18/4/18  15:43
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("BaseFragmentActivity.onCreate()...");
        Logger.d("AppStatusManager.getInstance().getAppStatus():" + AppStatusManager.getInstance().getAppStatus());
        //修复应用被后台回收，引起的空指针异常问题
        if (AppStatusManager.getInstance().getAppStatus() == AppStatusConstant.STATUS_FORCE_KILLED) {
            finish();
            return;
        }
    }


    public void onResume() {
        super.onResume();
        Logger.d("BaseFragmentActivity.onResume()...");
        //修复应用被后台回收，引起的空指针异常问题
        if (AppStatusManager.getInstance().getAppStatus() == AppStatusConstant.STATUS_FORCE_KILLED) {

            finish();
            return;
        }

        //注：回调 1
//        Bugout.onResume(this);
    }

    public void onPause() {
        super.onPause();

//        Bugout.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
//        Bugout.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }


}
