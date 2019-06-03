package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.presenter.TaskListPresent;
import cn.gog.conmentmanage.ui.adapter.DutyListAdatper;
import cn.gog.conmentmanage.view.ITaskListView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class TaskListActivity extends BaseMvpActivity<TaskListPresent> implements ITaskListView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    RecyclerView mListView;

    BaseQuickAdapter adapter;
    List<DutyItemEntity> mData;
    private int page;
    private int pageSize;
    private View notDataView;


    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }

    @Override
    protected TaskListPresent createPresenter() {
        return new TaskListPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_task_list);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
        mData = new ArrayList<>();


        adapter = new DutyListAdatper(this, mData);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(adapter);

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mListView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        adapter.setEmptyView(notDataView);

        page = 1;
        showLoadingDialog();
        createPresenter().getTastList(page, 10,"");

        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.REFRESHTASK) {

                    page = 1;
                    createPresenter().getTastList(page, 10,"");

                }
            }
        });
    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(TaskListActivity.this, DutyDetailActivity.class);
                mIntent.putExtra("taskid", mData.get(position).getTaskid());
                startActivity(mIntent);

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page = 1;
                showLoadingDialog();
                createPresenter().getTastList(page, 10,"");

            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refreshLayout.setRefreshing(false);
                page = page + 1;
                showLoadingDialog();
                createPresenter().getTastList(page, 10,"");

            }
        }, mListView);

        adapter.setEnableLoadMore(true);
    }

    @Override
    protected String gogTitle() {
        return null;
    }

    public void onRefresh() {
        page = 1;
        createPresenter().getTastList(page, 10,"");
    }

    @Override
    public void getTastListSuccess(DutyPage data) {
        hideLoadingDialog();
        if (data.isIsFirstPage()) {
            adapter.setEnableLoadMore(true);
            refreshLayout.setRefreshing(false);
            mData.clear();
        }

        adapter.loadMoreComplete();

        if (data.isIsLastPage()) {
            adapter.setEnableLoadMore(false);
            adapter.loadMoreEnd(true);
        }
        mData.addAll(data.getList());
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_lsit, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_btn:
                startActivity(new Intent(TaskListActivity.this, AddDutyActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
