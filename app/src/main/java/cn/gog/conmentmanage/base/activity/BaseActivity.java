package cn.gog.conmentmanage.base.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.testin.agent.Bugout;

import butterknife.ButterKnife;
import cn.finalteam.rxgalleryfinal.rxbus.RxBus;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.utils.recyckeviewwrap.WrapGridLayoutManager;
import cn.gog.conmentmanage.utils.recyckeviewwrap.WrapLinearLayoutManager;
import common.bugout.BugGoutAgent;
import common.config.AppStatusConstant;
import common.config.AppStatusManager;
import common.manager.AppStackManager;
import common.model.Notice;
import common.utils.DisplayUtil;
import common.widget.LoadingDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/4/8
 * desc:Activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private PopupWindow popwindow;
    private Boolean isshowWindow;
    private String imageStr;

    /**
     * 加载对话框
     */
    private LoadingDialog loadingDialog;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BugGoutAgent.init(this, false);
        Logger.d("BaseActivity.onCreate()...");
        //修复应用被后台回收，引起的空指针异常问题
        if (AppStatusManager.getInstance().getAppStatus() == AppStatusConstant.STATUS_FORCE_KILLED) {

        }
        AppStackManager.addActivity(this);
        this.loadingDialog = new LoadingDialog(this);
        mContext = this;

        setLayoutInflaterFactory();
        initView(savedInstanceState);


    }

    public void onResume() {
        super.onResume();
        Logger.d("BaseActivity.onResume()...");
        //修复应用被后台回收，引起的空指针异常问题
        if (AppStatusManager.getInstance().getAppStatus() == AppStatusConstant.STATUS_FORCE_KILLED) {
//            Router.startActivity(this, "dcb://launch");
//            finish();
//            return;
        }

        Bugout.onResume(this);
    }



    /**
     * 显示加载对话框
     * Time: 16/3/9  10:44
     */
    public void showLoadingDialog() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 隐藏加载对话框
     * Time: 16/3/9  10:44
     */
    public void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    public void onPause() {
        super.onPause();
        Bugout.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3

        return super.dispatchTouchEvent(event);
    }

    public void setLayoutInflaterFactory() {
        LayoutInflater layoutInflater = getLayoutInflater();
        try {
//            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
//            mFactorySet.setAccessible(true);
//            mFactorySet.set(layoutInflater, false);
//            LayoutInflaterCompat.setFactory(layoutInflater, new SkinFactory(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public RecyclerView initCommonRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initHorizontalRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initGridRecyclerView(int resId, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
        RecyclerView recyclerView = (RecyclerView) findViewById(resId);
        recyclerView.setLayoutManager(new WrapGridLayoutManager(this, spanCount));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initGridRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
        recyclerView.setLayoutManager(new WrapGridLayoutManager(this, spanCount));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppStackManager.finishActivity(this);

    }

    /**
     * 初始化界面
     */
    protected void initView(Bundle savedInstanceState) {

        setStatusBarColor();

        loadViewLayout();
        ButterKnife.bind(this);
        bindViews();
        processLogic(savedInstanceState);
        setListener();
    }

    protected void setStatusBarColor() {
//        StatusBarUtils.setWindowStatusBarColor(this, Color.parseColor("#d32b11"));
    }


    protected void showLog(String log) {
        Logger.i(log);
    }


    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) findViewById(id);
    }

    /**
     * 加载布局
     */
    protected abstract void loadViewLayout();

    /**
     * 网络错误重新加载
     */
    protected abstract void refreshData();

    /**
     * find控件
     */
    protected abstract void bindViews();


    /**
     * 处理数据
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected abstract void setListener();

    protected abstract String gogTitle();

    /**
     * 界面跳转
     *
     * @param tarActivity
     */
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        startActivity(intent);
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
//        ToastUtils.showToast(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册事件通知
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void post(Notice msg) {
        RxBus.getDefault().post(msg);
    }


    public void addSubscription(Observable observable, Observer observer) {
        observable
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideLoadingDialog();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
    //通过反射获取状态栏高度，默认25dp
    protected static int getStatusBarHeight(Context context) {
        int statusBarHeight = DisplayUtil.dip2px(context, 25);
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
