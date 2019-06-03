package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.model.SearchEntity;
import cn.gog.conmentmanage.presenter.SearchPresenter;
import cn.gog.conmentmanage.presenter.TaskListPresent;
import cn.gog.conmentmanage.ui.adapter.DutyListAdatper;
import cn.gog.conmentmanage.ui.adapter.SearchAdatper;
import cn.gog.conmentmanage.ui.adapter.SearchHistoryDataAdapter;
import cn.gog.conmentmanage.ui.view.FlowLayout;
import cn.gog.conmentmanage.view.ISearchView;
import cn.gog.conmentmanage.view.ITaskListView;

/**
 * 新闻搜索
 */
public class SearchTaskActivity extends BaseMvpActivity<TaskListPresent> implements ITaskListView {

    @BindView(R.id.et_searchtext_search)
    EditText et_searchtext_search;

    @BindView(R.id.ib_searchtext_delete)
    ImageView ib_searchtext_delete;


    @BindView(R.id.buttonback)
    TextView bt_text_search_back;

    private View msearchTopView;
    List<DutyItemEntity> mData;

    @BindView(R.id.listView)
    RecyclerView resultList;
    private String backtitle = "取消";
    private String   searchtitle = "搜索";
    BaseQuickAdapter adapter;

    private int page=1;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private String keyWords="";

    @BindView(R.id.history_grid)
    FlowLayout historyGrid;

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.clear_history)
    ImageView clearHistory;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_search_task);
        ButterKnife.bind(this);

    }

    @Override
    protected void refreshData() {

    }
    private ArrayList<String> historyDataList = new ArrayList<String>();
    private LayoutInflater mInflater;
    @Override
    protected void bindViews() {


        initData();

        msearchTopView = findViewById(R.id.msearch_top);
        backtitle = "取消";
        searchtitle ="搜索";

        mData = new ArrayList<>();
        adapter = new DutyListAdatper(this, mData);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.setAdapter(adapter);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {



    }


    //文本观察者
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        //当文本改变时候的操作
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            //如果编辑框中文本的长度大于0就显示删除按钮否则不显示
            keyWords =  s.toString();
            if (s.length() > 0) {
                ib_searchtext_delete.setVisibility(View.VISIBLE);
                bt_text_search_back.setText(searchtitle);

            } else {

                ib_searchtext_delete.setVisibility(View.GONE);
                bt_text_search_back.setText(backtitle);
            }
        }

    }


    @Override
    protected void setListener()  {

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        et_searchtext_search.addTextChangedListener( new SearchTaskActivity.MyTextWatcher());
        ib_searchtext_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_searchtext_search.setText("");
            }
        });

        bt_text_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals("取消",bt_text_search_back.getText().toString())){
                    finish();
                }else {
                    boolean isContain = false;
                    for(String bena :historyDataList){
                        if(TextUtils.equals(bena,et_searchtext_search.getText().toString())){
                            isContain = true;
                        }
                    }
                    if(!isContain){
                        historyDataList.add(et_searchtext_search.getText().toString());
                        UserCache.putSearchHistory(historyDataList);
                        initData();
                    }


                    showLoadingDialog();
                    mData.clear();
                    adapter.notifyDataSetChanged();
                     keyWords =et_searchtext_search.getText().toString();
                    createPresenter().getTastList(page, 10,keyWords);
                 

                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(SearchTaskActivity.this, DutyDetailActivity.class);
                mIntent.putExtra("taskid", mData.get(position).getTaskid());
                startActivity(mIntent);

            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page = 1;
                showLoadingDialog();
                createPresenter().getTastList(page, 10,keyWords);

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
        }, resultList);

        TextView textView = new TextView(this);
        textView.setText("没有结果");
        adapter.setEmptyView(textView);
        adapter.setEnableLoadMore(true);

        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyDataList.clear();
                UserCache.putSearchHistory(historyDataList);
                initData();
            }
        });

    }

    private void initData() {
        String historyDataListStr = UserCache.getSearchHistory();

        if(TextUtils.isEmpty(historyDataListStr)){
            historyDataList = new ArrayList<>();
        }else {
            historyDataList = new Gson().fromJson(historyDataListStr, new
                    TypeToken<List<String>>() {
                    }.getType());
        }
        mInflater = getLayoutInflater();
        historyGrid.removeAllViews();
        /**
         * 找到搜索标签的控件
         */
        for ( int i = 0; i < historyDataList.size(); i++) {

            TextView tv = (TextView) mInflater.inflate(
                    R.layout.search_label_tv, historyGrid, false);
            tv.setText(historyDataList.get(i));
            final String str = tv.getText().toString();
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mData.clear();
                    adapter.notifyDataSetChanged();
                    keyWords =str;
                    showLoadingDialog();
                    createPresenter().getTastList(page, 10,keyWords);
                }
            });
            historyGrid.addView(tv);
        }
    }

    @Override
    protected String gogTitle() {
        return "";
    }



    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();

    }

    @Override
    protected TaskListPresent createPresenter() {
        if (null == mvpPresenter) {
            mvpPresenter = new TaskListPresent(this);
        }
        return mvpPresenter;
    }


    @Override
    public void onBackPressed() {

    }
    @Override
    public void getTastListSuccess(DutyPage data) {
        scrollView.setVisibility(View.GONE);
        resultList.setVisibility(View.VISIBLE);
        hideLoadingDialog();
        refreshLayout.setRefreshing(false);
        if (data.isIsFirstPage()) {
            adapter.setEnableLoadMore(true);

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
}