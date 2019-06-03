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
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.presenter.DutyListPresent;
import cn.gog.conmentmanage.ui.activity.DutyDetailActivity;
import cn.gog.conmentmanage.ui.activity.DutyNoticeListActivity;
import cn.gog.conmentmanage.ui.activity.TaskListActivity;
import cn.gog.conmentmanage.ui.adapter.DutyListAdatper;
import cn.gog.conmentmanage.view.IDutyListView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by gujin on 2018/4/18.
 */

public class DutyListFragment extends BaseMvpFragment<DutyListPresent> implements IDutyListView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.listView)
    RecyclerView mListView;



    BaseQuickAdapter adapter;
    List<DutyItemEntity> mData;


    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private int page;
    private int pageSize;
    private View notDataView;


    @Override
    protected DutyListPresent createPresenter() {
        return new DutyListPresent(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_duty_work, null);
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

        mData = new ArrayList<>();


        adapter = new DutyListAdatper(getActivity(), mData);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(adapter);

        notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mListView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        adapter.setEmptyView(notDataView);

        page = 1;
        showLoadingDialog();
        createPresenter().getTastList(page, 6);

        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.REFRESHTASK) {

                    page = 1;
                    createPresenter().getTastList(page, 6);

                }else if(message.type == ConstanceValue.ONUSERRELOGIN){
                    mData.clear();
                    adapter.notifyDataSetChanged();
                    page = 1;
                    createPresenter().getTastList(page, 6);
                }
            }
        });

        TextView textView = new TextView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 10, 2, 10);

        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER);
        textView.setText("查看更多...");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TaskListActivity.class);
                startActivity(intent);
            }
        });
        adapter.setFooterView(textView);
    }

    @Override
    protected void setListener() {
        refreshLayout.setOnRefreshListener(this);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(getActivity(), DutyDetailActivity.class);
                mIntent.putExtra("taskid", mData.get(position).getTaskid());
                startActivity(mIntent);

            }
        });


        adapter.setEnableLoadMore(false);

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
        mData.clear();
        adapter.notifyDataSetChanged();
        page = 1;
        showLoadingDialog();
        createPresenter().getTastList(page, 6);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void getTastListSuccess(DutyPage data) {
        hideLoadingDialog();
        refreshLayout.setRefreshing(false);

        mData.clear();
        mData.addAll(data.getList());
        adapter.notifyDataSetChanged();

    }
}

