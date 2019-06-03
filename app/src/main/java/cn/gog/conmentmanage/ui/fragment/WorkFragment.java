package cn.gog.conmentmanage.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.fragment.BaseFragment;
import cn.gog.conmentmanage.base.fragment.BaseMvpFragment;
import cn.gog.conmentmanage.base.fragment.IBackHandled;
import cn.gog.conmentmanage.model.TypeItemEntity;
import cn.gog.conmentmanage.presenter.WorkPresenter;
import cn.gog.conmentmanage.ui.activity.AddDutyActivity;
import cn.gog.conmentmanage.ui.activity.AddWritingActivity;
import cn.gog.conmentmanage.ui.activity.SearchActivity;
import cn.gog.conmentmanage.ui.activity.SearchArticleActivity;
import cn.gog.conmentmanage.ui.activity.SearchTaskActivity;
import cn.gog.conmentmanage.ui.adapter.HomeTitlePagerAdapter;
import cn.gog.conmentmanage.view.IWorkView;
import common.utils.StatusBarCompat;
import common.utils.ToastUtils;

/**
 * author:顾进
 * mail:1002606871@qq.com
 * date：2018/5/2
 * desc: 待办
 */
public class WorkFragment extends BaseMvpFragment<WorkPresenter> implements IWorkView {

    @BindView(R.id.stl_main)
    SmartTabLayout stl_main;
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    IBackHandled currentFrame;
    private HomeTitlePagerAdapter adapter;
    @BindView(R.id.vp)
    ViewPager vp;


    List<TypeItemEntity> data;

    @Override
    protected WorkPresenter createPresenter() {
        return new WorkPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_work, null);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews(View view) {
        setHasOptionsMenu(true);
        ButterKnife.bind(this, rootView);
        mToolbar.setTitle("");
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_home);
        StatusBarCompat.translucentStatusBar(getActivity());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(getActivity()), 0, 0);    //给Toolbar设置paddingTop
        }
    }

    private void bindFragments() {

        List<BaseFragment> fragments = new ArrayList<>();

        String[] titles = new String[data.size()];

        titles[0] = data.get(0).getTaskTypeName();
        titles[1] = data.get(1).getTaskTypeName();

        DutyListFragment mDutyListFragment = new DutyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", titles[0]);
        mDutyListFragment.setArguments(bundle);
        fragments.add(mDutyListFragment);

        WritingListFragment mWritingListFragment = new WritingListFragment();

        Bundle bundle1 = new Bundle();
        bundle1.putString("title", titles[1]);
        mWritingListFragment.setArguments(bundle1);
        fragments.add(mWritingListFragment);


        titles[0] = data.get(0).getTaskTypeName();
        titles[1] = data.get(1).getTaskTypeName();


        adapter = new HomeTitlePagerAdapter(getChildFragmentManager(), fragments, titles);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setAdapter(adapter);
        stl_main.setViewPager(vp);
        currentFrame = fragments.get(0);
    }

    @Override
    protected void processLogic() {

        data = new ArrayList<>();

        TypeItemEntity receiver = new TypeItemEntity();
        receiver.setTaskTypeName("任务列表");
        receiver.setType(0);
        data.add(receiver);

        TypeItemEntity applied = new TypeItemEntity();
        applied.setTaskTypeName("文章列表");
        applied.setType(1);
        data.add(applied);

        bindFragments();

    }


    @Override
    protected void setListener() {

    }


    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }
    Menu mMenu;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        this.mMenu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.add_btn:
                if(vp.getCurrentItem() == 0){
                    startActivity(new Intent(getActivity(), AddDutyActivity.class));
                }else if(vp.getCurrentItem() == 1){
                    startActivity(new Intent(getActivity(), AddWritingActivity.class));
                }
                break;
            case R.id.search_btn:
                if(vp.getCurrentItem() == 0){
                    startActivity(new Intent(getActivity(), SearchTaskActivity.class));
                }else if(vp.getCurrentItem() == 1){
                    startActivity(new Intent(getActivity(), SearchArticleActivity.class));
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}