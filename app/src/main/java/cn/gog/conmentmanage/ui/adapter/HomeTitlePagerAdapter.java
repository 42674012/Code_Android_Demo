package cn.gog.conmentmanage.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.List;

import cn.gog.conmentmanage.base.fragment.BaseFragment;


/**
 * author:顾进
 * mail:1002606871@qq.com
 * date：2017/6/11
 * desc:解决fragment的缓存问题，详见文档fragment viewpage 和FragmentPagerAdapter在fragment 增删过程中数据刷新问题
 */
public class HomeTitlePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private String[] titles;
    private FragmentManager fm;

    public HomeTitlePagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        this.fm = fm;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        String fragmentTag = fragment.getTag();
        String title = fragment.getArguments().getString("title");

        //判断是不是相同的fragment
        if (!TextUtils.equals(title, getPageTitle(position % fragments.size()).toString())) {
            FragmentTransaction ft = fm.beginTransaction();

            fragment = fragments.get(position % fragments.size());
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commitAllowingStateLoss();
        }
        return fragment;
    }
}
