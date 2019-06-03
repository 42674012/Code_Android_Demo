package cn.gog.conmentmanage.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.ArticleMsg;
import cn.gog.conmentmanage.model.SystemInfoPage;
import cn.gog.conmentmanage.model.TaskPage;
import cn.gog.conmentmanage.presenter.ArticleInfoPresent;
import cn.gog.conmentmanage.ui.adapter.AticleMsgListAdatper;
import cn.gog.conmentmanage.view.IArticleInfoView;
import common.utils.StatusBarCompat;

public class ArticleInfoListActivity extends BaseMvpActivity<ArticleInfoPresent> implements IArticleInfoView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    RecyclerView mListView;

    AticleMsgListAdatper adapter;
    List<ArticleMsg.ListBean> mData;
    private int page;
    private int pageSize;
    private View notDataView;
    @BindView(R.id.action_panel)
    LinearLayout action_panel;
    @BindView(R.id.delete_all)
    TextView delete_all;
    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected ArticleInfoPresent createPresenter() {
        return new ArticleInfoPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_article_info_list);
    }

    @Override
    protected void refreshData() {
        page = 1;
        showLoadingDialog();
        createPresenter().pageArticleMsg(page,10);
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


        adapter = new AticleMsgListAdatper(this, mData);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(adapter);

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mListView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                page = 1;
                showLoadingDialog();
                createPresenter().pageArticleMsg(page,10);
            }
        });
        adapter.setEmptyView(notDataView);

        page = 1;
        showLoadingDialog();
        createPresenter().pageArticleMsg(page,10);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()){
                    case R.id.root:
                        Intent mIntent = new Intent(ArticleInfoListActivity.this, WritingDetailActivity.class);
                        mIntent.putExtra("articleid", mData.get(position).getArticleid());
                        startActivity(mIntent);
                        break;
                    case R.id.ischosen:
                        boolean isCHosen = mData.get(position).isChosen();
                        mData.get(position).setChosen(!isCHosen);
                        adapter.notifyDataSetChanged();
                        break;
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



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshData();

            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refreshLayout.setRefreshing(false);
                page = page + 1;
                showLoadingDialog();
                createPresenter().pageArticleMsg(page,10);

            }
        },mListView);

        adapter.setEnableLoadMore(true);

    }

    @Override
    protected String gogTitle() {
        return null;
    }

    @Override
    public void pageArticleSuccess(ArticleMsg data) {
        hideLoadingDialog();
        if (data.isIsFirstPage()) {
            refreshLayout.setRefreshing(false);
            mData.clear();
            adapter.setEnableLoadMore(true);

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
    public void deleteMsgSuccess(String data) {
        hideLoadingDialog();
        showToast(data);
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_duty, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_btn:

                if(adapter.isEdit()){
                    adapter.setEdit(false);
                    adapter.notifyDataSetChanged();
                    action_panel.setVisibility(View.GONE);
                    delete_all.setVisibility(View.VISIBLE);
                }else {
                    adapter.setEdit(true);
                    adapter.notifyDataSetChanged();
                    action_panel.setVisibility(View.VISIBLE);
                    delete_all.setVisibility(View.GONE);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.cancel_tv,R.id.delete_tv,R.id.delete_all})
    public void click(View view){
        switch (view.getId()){
            case R.id.cancel_tv:
                adapter.setEdit(false);
                adapter.notifyDataSetChanged();
                action_panel.setVisibility(View.GONE);
                delete_all.setVisibility(View.VISIBLE);
                break;
            case R.id.delete_tv:

                AlertDialog.Builder builder = new AlertDialog.Builder(ArticleInfoListActivity.this);

                //    设置Title的内容
                builder.setTitle("");
                //    设置Content来显示一个信息
                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {


                        List<String> ids = new ArrayList<>();

                        for(ArticleMsg.ListBean bena :mData){
                            if(bena.isChosen()){
                                ids.add(bena.getMsgid());
                            }
                        }

                        if(ids.size() > 0){

                            showLoadingDialog();
                            createPresenter().deletaMsgs(new Gson().toJson(ids));
                        }else {
                            showToast("请选择要删除的项");
                        }
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.show();


                break;
            case R.id.delete_all:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ArticleInfoListActivity.this);

                //    设置Title的内容
                builder1.setTitle("");
                //    设置Content来显示一个信息
                builder1.setMessage("确定清空消息吗？");
                //    设置一个PositiveButton
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        List<String> ids = new ArrayList<>();

                        for(ArticleMsg.ListBean bena :mData){
                            ids.add(bena.getMsgid());
                        }

                        if(ids.size() > 0){

                            showLoadingDialog();
                            createPresenter().deletaMsgs(new Gson().toJson(ids));
                        }else {
                            showToast("请选择要删除的项");
                        }
                    }
                });
                //    设置一个NegativeButton
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder1.show();
                break;
        }
    }
}
