package cn.gog.conmentmanage.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.presenter.AddRequestOrgPresent;
import cn.gog.conmentmanage.ui.adapter.OrgAdapter;
import cn.gog.conmentmanage.view.IAddRequestOrgView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.RxBus;
import common.utils.StatusBarCompat;

public class AddRequestOrgActivity extends BaseMvpActivity<AddRequestOrgPresent> implements IAddRequestOrgView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    RecyclerView list;


    OrgAdapter mAdapter;
    private List<OrgEntity> mDatas;


    private List<OrgEntity> mCDatas;


      String  taskid;

    @Override
    protected AddRequestOrgPresent createPresenter() {
        return new AddRequestOrgPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_add_request);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskid = getIntent().getStringExtra("taskid");

        mDatas = new ArrayList<>();
        mAdapter = new OrgAdapter(this, mDatas);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mAdapter);

        String listJson = getIntent().getStringExtra("list");

        Gson gson = new Gson();
        List<OrgEntity> orgList = gson.fromJson(listJson, new TypeToken<List<OrgEntity>>() {
        }.getType());
        if(orgList !=null){
            mCDatas = orgList;
        }else {

            mCDatas= new ArrayList<>();
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
        showLoadingDialog();
        createPresenter().childrenOrg();
    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


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
    public void childrenOrgSuccess(List<OrgEntity> data) {
        hideLoadingDialog();
        mDatas.clear();
        mDatas.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void assignToOrgSuccess(String data) {
        showToast(data);
        finish();
    }

    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_jieshou, menu);
        this.menu = menu;
        if(!TextUtils.isEmpty(taskid)){
            menu.getItem(0).setTitle("任务派发");
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_btn:

                if(TextUtils.isEmpty(taskid)){
                    generateChosens();
                    Notice notice=new Notice();
                    notice.type= ConstanceValue.ADDJIESHOULIST;
                    notice.content = new Gson().toJson(mCDatas);
                    RxBus.getDefault().post(notice);
                    finish();
                }else {

                    generateChosens();

                    if(mCDatas.size() ==0 ){
                        showToast("请选择机构");
                    }else{
                        List<String> orgs = new ArrayList<>();
                       for(OrgEntity entity :mCDatas){
                           orgs.add(entity.getOrgid());
                       }
                        createPresenter().assignToOrg(taskid,new Gson().toJson(orgs));

                    }

                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateChosens() {

        for(OrgEntity entity : mDatas){
            if (entity.getChosen()){
                mCDatas.add(entity);
            }
        }
    }
}
