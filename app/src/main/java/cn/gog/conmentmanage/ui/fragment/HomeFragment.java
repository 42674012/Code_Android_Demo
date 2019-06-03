package cn.gog.conmentmanage.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.fragment.BaseMvpFragment;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.LiveTokenEntity;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.HomePresenter;
import cn.gog.conmentmanage.ui.activity.AddDutyActivity;
import cn.gog.conmentmanage.ui.activity.ArticleInfoListActivity;
import cn.gog.conmentmanage.ui.activity.DutyNoticeListActivity;
import cn.gog.conmentmanage.ui.activity.LoginActivity;
import cn.gog.conmentmanage.ui.activity.MainActivity;
import cn.gog.conmentmanage.ui.activity.SearchActivity;
import cn.gog.conmentmanage.ui.activity.SystemInfoActivity;
import cn.gog.conmentmanage.ui.adapter.MsgAdatper;
import cn.gog.conmentmanage.view.IHomeView;
import cn.jpush.android.api.JPushInterface;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author:顾进
 * mail:1002606871@qq.com
 * date：2017/9/28
 * desc: 消息
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements IHomeView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.live_item_list)
    RecyclerView mLiveItemList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout ;


    protected BaseQuickAdapter messageAdapter;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews(View view) {
        ButterKnife.bind(this, rootView);
        mToolbar.setTitle("首页");
        setHasOptionsMenu(true);
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_home);
    }
    public List<MsgEntity> mDatas = new ArrayList<>();

    @Override
    protected void processLogic() {
        mDatas = new ArrayList<>();
        messageAdapter = new MsgAdatper(getActivity(), mDatas);
        mLiveItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLiveItemList.setAdapter(messageAdapter);
        StatusBarCompat.translucentStatusBar(getActivity());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(getActivity()), 0, 0);    //给Toolbar设置paddingTop
        }

        hideLoadingDialog();
        createPresenter().getNewestMsfList();

        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.ONUSERRELOGIN) { //用户重新登录
                    mDatas.clear();
                    messageAdapter.notifyDataSetChanged();
                    createPresenter().getNewestMsfList();
                }
            }
        });

        UserInfo userInfo =   UserCache.get();
        if(userInfo != null){
            createPresenter().jiguangPush(JPushInterface.getRegistrationID(getActivity()));
        }

    }


    @Override
    protected void setListener()  {
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                MsgEntity entity =  mDatas.get(position);

                if(TextUtils.equals(entity.getType() ,"任务通知")){
                    startActivity(new Intent(getActivity(), DutyNoticeListActivity.class));
                }else if(TextUtils.equals(entity.getType() ,"文章列表消息")){
                    startActivity(new Intent(getActivity(), ArticleInfoListActivity.class));
                }else if(TextUtils.equals(entity.getType() ,"系统消息")){

                    startActivity(new Intent(getActivity(), SystemInfoActivity.class));
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createPresenter().getNewestMsfList();
            }
        });
    }

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)  {}

    @Override
    public void getTokenOk(LiveTokenEntity data) {

    }

    @Override
    public void getDecodeResultOk(String data) {

    }

    @Override
    public void getNewestMsfListSuccess(List<MsgEntity> data) {
       hideLoadingDialog();
       if(refreshLayout.isRefreshing()){
           refreshLayout.setRefreshing(false);
       }
        mDatas.clear();
        mDatas.addAll(data);
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();//这句话没用啊，谁知道
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.search_btn:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.add_btn:
                startActivity(new Intent(getActivity(), AddDutyActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}