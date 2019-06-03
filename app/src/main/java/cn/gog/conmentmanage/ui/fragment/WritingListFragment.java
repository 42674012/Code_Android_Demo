package cn.gog.conmentmanage.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.fragment.BaseMvpFragment;
import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.WritingItemEntity;
import cn.gog.conmentmanage.presenter.WaitWorkPresenter;
import cn.gog.conmentmanage.ui.activity.TaskListActivity;
import cn.gog.conmentmanage.ui.activity.WritingDetailActivity;
import cn.gog.conmentmanage.ui.activity.WritingListActivity;
import cn.gog.conmentmanage.ui.adapter.DutyListAdatper;
import cn.gog.conmentmanage.ui.adapter.WritingListAdatper;
import cn.gog.conmentmanage.view.IWaitWorkListView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by gujin on 2018/4/18.
 */

public class WritingListFragment extends BaseMvpFragment<WaitWorkPresenter> implements IWaitWorkListView,SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.listView)
    RecyclerView mListView;


    BaseQuickAdapter adapter;
    List<WritingItemEntity> mDatas;

    private int page;
    private int pageSize;
    private View notDataView;


    @Override
    protected WaitWorkPresenter createPresenter() {
        return new WaitWorkPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_wait_work, null);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);


    }

    @Override
    protected void processLogic() {

        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.ONARTICLEDELETE) {
                    page = 1;
                    createPresenter().articlePage(page,6);
                }else if(message.type == ConstanceValue.ONUSERRELOGIN){
                    mDatas.clear();
                    adapter.notifyDataSetChanged();
                    page = 1;
                    createPresenter().articlePage(page,6);
                }
            }
        });

        mDatas = new ArrayList<>();


        adapter = new WritingListAdatper(getActivity(),mDatas);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(adapter);

        notDataView =getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mListView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        adapter.setEmptyView(notDataView);

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 10, 2, 10);

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText("查看更多...");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),WritingListActivity.class);
                startActivity(intent);
            }
        });
        adapter.setFooterView(textView);

        showLoadingDialog();
        page = 1;
        createPresenter().articlePage(page,6);

    }

    @Override
    protected void setListener() {

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(getActivity(), WritingDetailActivity.class);
                mIntent.putExtra("articleid",mDatas.get(position).getArticleid());
                startActivity(mIntent);

            }
        });


        adapter.setEnableLoadMore(false);

        refreshLayout.setOnRefreshListener(this);

    }

//    @OnClick({R.id.img_nav_search, R.id.icon_category, R.id.btn_hot})
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//        }
//    }

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

        hideLoadingDialog();
    }



    @Override
    public void onRefresh() {
        mDatas.clear();
        refreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
        page = 1;
        createPresenter().articlePage(page,6);
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void articlePageSuccess(ArticlePage data) {
        hideLoadingDialog();
        mDatas.clear();

        mDatas.addAll(data.getList());
        adapter.notifyDataSetChanged();


    }
}

