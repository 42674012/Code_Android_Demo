package cn.gog.conmentmanage.ui.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseActivity;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.ui.adapter.DutyAdatper;
import cn.gog.conmentmanage.ui.adapter.DutyRequestAdatper;
import common.utils.StatusBarCompat;

public class DutyDetailRequestActivity extends BaseActivity {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    RecyclerView list;

    List<DutyDetailModel.TaskDetailsVoListBean> data;

    DutyRequestAdatper mAdapter;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_duty_detail_request);
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
       data = gson.fromJson(dataJson,new TypeToken<List<DutyDetailModel.TaskDetailsVoListBean>>() {}.getType());

        mAdapter = new DutyRequestAdatper(this,data);
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
}
