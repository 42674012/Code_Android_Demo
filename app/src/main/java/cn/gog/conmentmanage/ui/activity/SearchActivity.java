package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import cn.gog.conmentmanage.model.SearchEntity;
import cn.gog.conmentmanage.presenter.SearchPresenter;
import cn.gog.conmentmanage.ui.adapter.SearchAdatper;
import cn.gog.conmentmanage.ui.adapter.SearchHistoryDataAdapter;
import cn.gog.conmentmanage.ui.view.FlowLayout;
import cn.gog.conmentmanage.view.ISearchView;

/**
 * 新闻搜索
 */
public class SearchActivity extends BaseMvpActivity<SearchPresenter> implements ISearchView {

    @BindView(R.id.et_searchtext_search)
    EditText et_searchtext_search;

    @BindView(R.id.ib_searchtext_delete)
    ImageView ib_searchtext_delete;

    @BindView(R.id.buttonback)
    TextView bt_text_search_back;

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.clear_history)
    ImageView clearHistory;


    @BindView(R.id.resultList)
    RecyclerView resultList;
    private String backtitle = "取消";
    private String   searchtitle = "搜索";

    private View msearchTopView;
    private ArrayList<String> historyDataList = new ArrayList<String>();
    private SearchHistoryDataAdapter historyDataAdapter;
    private String keyWords;
    private int pageIndex;

    private LayoutInflater mInflater;

    @BindView(R.id.history_grid)
    FlowLayout historyGrid;
    SearchAdatper adatper;

    private List<SearchEntity.MsgBean> mDatas;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

    }
    public void initData() {
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

                     mDatas.clear();
                     adatper.notifyDataSetChanged();
                    createPresenter().searhByKey(str);
                }
            });
            historyGrid.addView(tv);
        }
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        msearchTopView = findViewById(R.id.msearch_top);
        backtitle = "取消";
        searchtitle ="搜索";

        String historyDataListStr = UserCache.getSearchHistory();

        if(TextUtils.isEmpty(historyDataListStr)){
            historyDataList = new ArrayList<>();
        }else {
            historyDataList = new Gson().fromJson(historyDataListStr, new
                    TypeToken<List<String>>() {
            }.getType());
        }

//              historyDataAdapter = new SearchHistoryDataAdapter(this, historyDataList);
//        historyGrid.setAdapter(historyDataAdapter);
        initData();
        mDatas = new ArrayList<>();

        adatper = new SearchAdatper(this,mDatas);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        resultList.setAdapter(adatper);
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

                    mDatas.clear();
                    adatper.notifyDataSetChanged();
                    createPresenter().searhByKey(et_searchtext_search.getText().toString());
                    et_searchtext_search.setText("");

                }
            }
        });
        et_searchtext_search.addTextChangedListener( new MyTextWatcher());
        ib_searchtext_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_searchtext_search.setText("");
            }
        });

        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyDataList.clear();
                UserCache.putSearchHistory(historyDataList);
                initData();
            }
        });

        adatper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchEntity.MsgBean msgBean = mDatas.get(position);

                if(!TextUtils.isEmpty(msgBean.getArticleid())){
                    Intent mIntent = new Intent(SearchActivity.this, WritingDetailActivity.class);
                    mIntent.putExtra("articleid", msgBean.getArticleid());
                    startActivity(mIntent);
                }else{
                    if(!TextUtils.isEmpty(msgBean.getTaskid())){
                        Intent mIntent = new Intent(SearchActivity.this, DutyDetailActivity.class);
                        mIntent.putExtra("taskid", msgBean.getTaskid());
                        startActivity(mIntent);
                    }else{
                        if(!TextUtils.isEmpty(msgBean.getSystemmsgid())){
                            Intent mIntent = new Intent(SearchActivity.this, PersonInfoActivity.class);

                            startActivity(mIntent);

                        }

                    }

                }

            }
        });
    }

    @Override
    protected String gogTitle() {
        return "新闻搜索";
    }



    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();

    }

    @Override
    protected SearchPresenter createPresenter() {
        if (null == mvpPresenter) {
            mvpPresenter = new SearchPresenter(this);
        }
        return mvpPresenter;
    }


    @Override
    public void onBackPressed() {

    }
    @Override
    public void searchSuccess(List<SearchEntity> data) {
        scrollView.setVisibility(View.GONE);
        resultList.setVisibility(View.VISIBLE);
        hideLoadingDialog();

        for(SearchEntity entity:data){
            SearchEntity.MsgBean msgBean = new SearchEntity.MsgBean();
            msgBean.setLabel(entity.getType()+"_1");
            mDatas.add(msgBean);
            mDatas.addAll(entity.getMsg());
        }
       adatper.notifyDataSetChanged();
    }
}