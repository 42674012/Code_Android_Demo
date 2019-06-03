package cn.gog.conmentmanage.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import cn.gog.conmentmanage.utils.recyckeviewwrap.WrapGridLayoutManager;
import cn.gog.conmentmanage.utils.recyckeviewwrap.WrapLinearLayoutManager;
import common.presenter.BasePresenter;
import common.utils.DisplayUtil;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date:2017/4/8
 * desc:MvpFragment基类
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (mvpPresenter == null) mvpPresenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected void lazyLoad() {
        if (mvpPresenter == null) mvpPresenter = createPresenter();
        super.lazyLoad();
    }
    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (mvpPresenter != null) {
//            hideLoadingDialog();
//            mvpPresenter.detachView();
//        }
    }

//    protected UserInfo user;

    public RecyclerView initCommonRecyclerView(RecyclerView recyclerView , BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(getActivity()));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public RecyclerView initHorizontalRecyclerView(BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        return initHorizontalRecyclerView(null, adapter, decoration);
    }

    public RecyclerView initHorizontalRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration) {
        return initHorizontalRecyclerView(recyclerView, adapter, decoration, false);
    }

    public RecyclerView initHorizontalRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, boolean reverseLayout) {
//        if (recyclerView == null)
//            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, reverseLayout));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

//    public RecyclerView initGridRecyclerView(BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
//        return initGridRecyclerView((RecyclerView) rootView.findViewById(R.id.recyclerView), adapter, decoration, spanCount);
//    }

    public RecyclerView initGridRecyclerView(RecyclerView recyclerView, BaseQuickAdapter adapter, RecyclerView.ItemDecoration decoration, int spanCount) {
        recyclerView.setLayoutManager(new WrapGridLayoutManager(getActivity(), spanCount));
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }



//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        user = BaseApplication.getInstance().getUserInfo();
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    @Override
//    public void onResume() {
//        user = BaseApplication.getInstance().getUserInfo();
//        super.onResume();
//    }
//    public boolean checkLogin() {
//        if (user == null) {
//            intent2Activity(LoginActivity.class);
//            return false;
//        }
//        return true;
//    }
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
