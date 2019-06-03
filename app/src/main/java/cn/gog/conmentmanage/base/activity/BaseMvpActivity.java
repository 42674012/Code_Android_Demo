package cn.gog.conmentmanage.base.activity;

import android.os.Bundle;

import common.presenter.BasePresenter;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/4/8
 * desc:MvpActivity 基类
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
//        BugGoutAgent.init(this, true);
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        //注：回调 1
//        Bugout.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
