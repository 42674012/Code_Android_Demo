package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.ContactPresent;
import cn.gog.conmentmanage.ui.adapter.ContactAdatper;
import cn.gog.conmentmanage.ui.view.MaskFrameLayout;
import cn.gog.conmentmanage.view.IContactView;
import common.utils.DateUtils;
import common.utils.StatusBarCompat;

public class ContactActivity extends BaseMvpActivity<ContactPresent> implements IContactView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    RecyclerView mListView;

    protected BaseQuickAdapter mAdapter;
    private List<ContactPage.UserInfoBean> mDatas;
    private int page = 1;
    @BindView(R.id.mask_view)
    MaskFrameLayout mask_view;


    @Override
    protected ContactPresent createPresenter() {
        return new ContactPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_contact);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatas = new ArrayList<>();

        UserInfo info = UserCache.get();
        String textStr= info.getUsername()+info.getOrg_name()+ DateUtils.toDateYMStr(new Date()).toString();
        for(int i=0;i<10;i++){
            TextView v = new TextView(this);
            v.setText(textStr);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            mask_view.addView(v, i, params);
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        mAdapter = new ContactAdatper(this, mDatas);
//        mLiveItemList.addItemDecoration(new SimpleDividerDecoration(getActivity()));
//        initCommonRecyclerView(mLiveItemList, messageAdapter, null);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mAdapter);
        StatusBarCompat.translucentStatusBar(this);

        page = 1;
        showLoadingDialog();
        createPresenter().contactList(page, 10);

    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
              Intent mIntent =  new Intent(ContactActivity.this, ContactDetailActivity.class);
                mIntent.putExtra("userinfo",new Gson().toJson(mDatas.get(position)));
                startActivity(mIntent);
            }
        });


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page = 1;
                showLoadingDialog();
                createPresenter().contactList(page, 10);

            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refreshLayout.setRefreshing(false);
                page = page + 1;
                showLoadingDialog();
                createPresenter().contactList(page, 10);

            }
        }, mListView);

        mAdapter.setEnableLoadMore(true);
    }

    @Override
    protected String gogTitle() {
        return null;
    }

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }


    @Override
    public void contactListSuccess(ContactPage data) {
        hideLoadingDialog();
        if (data.isIsFirstPage()) {
            mAdapter.setEnableLoadMore(true);
            refreshLayout.setRefreshing(false);
            mDatas.clear();
        }

        mAdapter.loadMoreComplete();

        if (data.isIsLastPage()) {
            mAdapter.setEnableLoadMore(false);
            mAdapter.loadMoreEnd(true);
        }
        mDatas.addAll(data.getList());
        mAdapter.notifyDataSetChanged();
    }
}
