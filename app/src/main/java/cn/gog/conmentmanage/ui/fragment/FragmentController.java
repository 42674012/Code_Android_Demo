package cn.gog.conmentmanage.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import cn.gog.conmentmanage.base.fragment.BaseFragment;
import cn.gog.conmentmanage.base.fragment.IBackHandled;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/12/8
 * desc: 导航页面控制器
 */
public class FragmentController {

    private int containerId;
    private FragmentManager fm;
    private ArrayList<BaseFragment> fragments;

    private IBackHandled currentFrame;

    private static FragmentController controller;
    private static boolean isReload;


    public IBackHandled getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(BaseFragment currentFrame) {
        this.currentFrame = currentFrame;
    }

    public static FragmentController getInstance(FragmentActivity activity, int containerId, boolean isReload) {
        FragmentController.isReload = isReload;
        if (controller == null) {
            controller = new FragmentController(activity, containerId);
        }
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    private FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        if (isReload) {
            fragments.add(new HomeFragment());
            fragments.add(new WorkFragment());
            fragments.add(new MeFragment());

            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                ft.add(containerId, fragments.get(i), "" + i);
            }
            ft.commit();

        } else {
            for (int i = 0; i < 5; i++) {
                BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(i + "");
                fragments.add(fragment);
            }
        }
    }

    public void showFragment(int position) {
        hideFragments();
        BaseFragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
        currentFrame = fragment;
        Context context = fragment.getActivity();
        Activity activity = fragment.getActivity();
//        if (null != context) {
//            if (0 == position) {
//                //StatusBarUtils.setWindowStatusBarColor(activity, ContextCompat.getColor(context, R.color.colorPrimary));
//                StatusBarUtils.setWindowStatusBarColor(fragment.getActivity(), ContextCompat.getColor(activity, R.color.home_nav));
//            }
//            else if (2 == position) {
//                //StatusBarUtils.setWindowStatusBarColor(activity, ContextCompat.getColor(context, R.color.colorPrimary));
//                StatusBarUtils.setWindowStatusBarColor(fragment.getActivity(),  ContextCompat.getColor(activity, R.color.colorPrimary));
//            } else {
//                StatusBarUtils.setWindowStatusBarColor(activity, ContextCompat.getColor(context, R.color.black));
//            }
//        }
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}