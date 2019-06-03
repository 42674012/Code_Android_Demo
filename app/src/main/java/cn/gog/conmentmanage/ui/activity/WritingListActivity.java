package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.model.WritingItemEntity;
import cn.gog.conmentmanage.presenter.WritingListPresent;
import cn.gog.conmentmanage.ui.adapter.WritingListAdatper;
import cn.gog.conmentmanage.view.IWritingListView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WritingListActivity extends BaseMvpActivity<WritingListPresent> implements IWritingListView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.listView)
    RecyclerView mListView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    WritingListAdatper adapter;
    List<WritingItemEntity> mDatas;

    @BindView(R.id.edit_pannel)
    LinearLayout edit_pannel;

    private int page;
    private int pageSize;
    private View notDataView;

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected WritingListPresent createPresenter() {
        return new WritingListPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_writing_list);
    }

    @Override
    protected void refreshData() {
        page = 1;
        showLoadingDialog();
        createPresenter().articlePage(page, 10,"");
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
        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.ONARTICLEDELETE) {
                    page = 1;
                    createPresenter().articlePage(page, 10,"");
                }
            }
        });

        mDatas = new ArrayList<>();


        adapter = new WritingListAdatper(this, mDatas);
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

        showLoadingDialog();
        page = 1;
        createPresenter().articlePage(page, 10,"");

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
                Intent mIntent = new Intent(WritingListActivity.this, WritingDetailActivity.class);
                mIntent.putExtra("articleid", mDatas.get(position).getArticleid());
                startActivity(mIntent);

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
                createPresenter().articlePage(page, 10,"");

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
        createPresenter().articlePage(page, 10,"");
    }

    @Override
    public void articlePageSuccess(ArticlePage data) {
        hideLoadingDialog();
        if (data.isIsFirstPage()) {
            adapter.setEnableLoadMore(true);
            refreshLayout.setRefreshing(false);
            mDatas.clear();
        }

        adapter.loadMoreComplete();

        if (data.isIsLastPage()) {
            adapter.setEnableLoadMore(false);
            adapter.loadMoreEnd(true);
        }
        mDatas.addAll(data.getList());
        adapter.notifyDataSetChanged();


    }

    @Override
    public void batchAgreeSuccess(String data) {
        refreshData();
    }

    @Override
    public void batchDisagreeSuccess(String data) {
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_btn:

                if(!adapter.isEdit()){
                    adapter.setEdit(!adapter.isEdit());
                    adapter.notifyDataSetChanged();
                    edit_pannel.setVisibility(View.VISIBLE);
                }else {
                    edit_pannel.setVisibility(View.GONE);
                    adapter.setEdit(false);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.add_btn:
                startActivity(new Intent(WritingListActivity.this,AddWritingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.caina,R.id.nocaina})
    public void click(View view){

        switch (view.getId()){
            case R.id.caina:
                adapter.setEdit(false);
                batchAgree();
                break;
            case R.id.nocaina:
                adapter.setEdit(false);
                batchDisagree();
                break;
        }
    }

    private void batchAgree() {
        List<String> chsoen = new ArrayList<>();

        boolean isOk = true;

        for(WritingItemEntity bean :mDatas){
            if(bean.isChosen()){
                if (!TextUtils.equals(bean.getResponse_stateStr(),"待审核"))
                {  isOk = false;
                }
                chsoen.add(bean.getArticleid());
            }
        }
        if(isOk){
             createPresenter().batchAgree(new Gson().toJson(chsoen));

        }else{
            showToast("只有待审核状态下才可以被采纳");
        }

    }
    private void batchDisagree() {

        List<String> chsoen = new ArrayList<>();

        boolean isOk = true;

        for(WritingItemEntity bean :mDatas){
            if(bean.isChosen()){
                if (!TextUtils.equals(bean.getResponse_stateStr(),"待审核"))
                {  isOk = false;
                }
                chsoen.add(bean.getArticleid());
            }
        }

        if(isOk){
            createPresenter().batchDisagree(new Gson().toJson(chsoen));
        }else{
            showToast("只有未审核状态下才可以设置不采纳");
        }
    }
}
