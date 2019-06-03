package cn.gog.conmentmanage.base.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.ui.activity.LoginActivity;
import common.model.Notice;
import common.utils.RxBus;
import common.utils.StringUtils;
import common.utils.ToastUtils;
import common.widget.LoadingDialog;
import io.reactivex.Observable;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/4/8
 * desc:Fragment基类
 */
public abstract class BaseFragment extends Fragment implements IBackHandled {
    protected Activity mContext;
    protected boolean isFirst = true;
    protected View rootView;


    /**
     * 加载对话框
     */
    private LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        try {
//            Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
//            mFactorySet.setAccessible(true);
//            mFactorySet.set(inflater, false);
//            LayoutInflaterCompat.setFactory(inflater, new SkinFactory((AppCompatActivity) getActivity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        this.loadingDialog = new LoadingDialog(getActivity());
        View view = loadViewLayout(inflater, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (StringUtils.isEmpty(this.getTag())) {
            throw new RuntimeException("Fragment tag不能为空!");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (StringUtils.isEmpty(this.getTag())) {
            throw new RuntimeException("Fragment tag不能为空!");
        }

    }

//    /**
//     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
//     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
//     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
//     */
//    protected abstract boolean onBackPressed();

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


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        rootView = view;
        initView(view);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) rootView.findViewById(id);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (isFirst) {
            lazyLoad();
            isFirst = false;
        }
    }

    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }

    /**
     * 初始化界面
     *
     * @param view
     */
    private void initView(View view) {
        bindViews(view);
        processLogic();
        setListener();
    }

    /**
     * 加载布局
     */
    protected abstract View loadViewLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * 网络错误重新加载
     */
    protected abstract void refreshData();

    /**
     * find控件
     *
     * @param view
     */
    protected abstract void bindViews(View view);

    /**
     * 处理数据
     */
    protected abstract void processLogic();

    /**
     * 设置监听
     */
    protected abstract void setListener();

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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLog(String msg) {
        Logger.i(msg);
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
        try {
            RxBus.getDefault().post(msg);
        }catch (Exception e){

        }

    }

    protected void login() {
        Intent mIntent = new Intent(getActivity(), LoginActivity.class);
        startActivity(mIntent);
    }

    @Override
    public boolean onBackPressed() {
        hideLoadingDialog();
        return true;
    }

    private View mLoadView = null;


}
