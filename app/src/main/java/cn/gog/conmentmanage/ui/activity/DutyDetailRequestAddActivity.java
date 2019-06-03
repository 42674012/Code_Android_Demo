package cn.gog.conmentmanage.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.presenter.AddRequestPresent;
import cn.gog.conmentmanage.ui.adapter.DutyRequestAdatper;
import cn.gog.conmentmanage.view.IAddRequestView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DutyDetailRequestAddActivity extends BaseMvpActivity<AddRequestPresent> implements IAddRequestView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    RecyclerView list;


    List<DutyDetailModel.TaskDetailsVoListBean> mDatas;

    DutyRequestAdatper mAdapter;


    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected AddRequestPresent createPresenter() {
        return null;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_duty_detail_request_add);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String  dataJson =   getIntent().getStringExtra("list");

        Gson gson = new Gson();
        mDatas = new ArrayList<>();

        if(!TextUtils.isEmpty(dataJson)){
            List<DutyDetailModel.TaskDetailsVoListBean> listBena =   gson.fromJson(dataJson,new TypeToken<List<DutyDetailModel.TaskDetailsVoListBean>>() {}.getType());
            mDatas.addAll(listBena);
        }
        mAdapter = new DutyRequestAdatper(this,mDatas);
        mAdapter.setShowDelete(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mAdapter);

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
                if (message.type == ConstanceValue.ADDTASKTYPE) {

                    Gson gson = new Gson();
                    DutyDetailModel.TaskDetailsVoListBean bean = gson.fromJson(message.content.toString(),DutyDetailModel.TaskDetailsVoListBean.class);

                    if(bean != null){
                        mDatas.add(bean);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice=new Notice();
                notice.type= ConstanceValue.ADDTASKTYPELIST;
                notice.content = new Gson().toJson(mDatas);
                RxBus.getDefault().post(notice);
                finish();
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("删除");

                mDatas.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected String gogTitle() {
        return null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_request, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_btn:
               startActivity(new Intent(DutyDetailRequestAddActivity.this,DutyDetailRequestAddEditActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
