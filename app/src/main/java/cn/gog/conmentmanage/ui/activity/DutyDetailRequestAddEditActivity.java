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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.TaskTypeEntity;
import cn.gog.conmentmanage.presenter.RequestAddEditPresent;
import cn.gog.conmentmanage.ui.adapter.MySpinnerAdapter;
import cn.gog.conmentmanage.view.IRequestAddEditView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.RxBus;
import common.utils.StatusBarCompat;

public class DutyDetailRequestAddEditActivity extends BaseMvpActivity<RequestAddEditPresent>  implements AdapterView.OnItemSelectedListener,IRequestAddEditView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.site_name)
    EditText site_name;
    @BindView(R.id.site_url)
    EditText site_url;
    @BindView(R.id.article_title)
    EditText article_title;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.task_sum)
    EditText task_sum;
    @BindView(R.id.task_type)
    TextView task_type;


    @BindView(R.id.type_list)
    Spinner speaker_list;
    List<TaskTypeEntity> mData;
    private MySpinnerAdapter adapter;
    private TaskTypeEntity model;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_duty_detail_request_edit);
    }

    @Override
    protected void refreshData() {

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
        adapter = new MySpinnerAdapter(this, mData);
        speaker_list.setAdapter(adapter);
        speaker_list.setOnItemSelectedListener(this);
        createPresenter().getTaskType();
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

    @OnClick({R.id.cancel, R.id.save})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.save:
                if(judeAll()){
                    saveTask();
                }
                break;
        }
    }



    private boolean judeAll() {

        if(TextUtils.isEmpty(site_name.getText().toString())){
            showToast("请输入网站名称");
            return  false;
        }

        if(TextUtils.isEmpty(site_url.getText().toString())){
            showToast("请输入链接地址");
            return  false;
        }

        if(TextUtils.isEmpty(article_title.getText().toString())){
            showToast("文章名称");
            return  false;
        }


        if(TextUtils.isEmpty(description.getText().toString())){
            showToast("要求描述");
            return  false;
        }
        if(TextUtils.isEmpty(task_sum.getText().toString())){
            showToast("任务数量");
            return  false;
        }

        if(model == null){
            showToast("请检查你的网络设置");
            createPresenter().getTaskType();
            return  false;
        }
        return true;
    }

    private void saveTask() {
        DutyDetailModel.TaskDetailsVoListBean bean = new DutyDetailModel.TaskDetailsVoListBean();
        bean.setArticle_title(article_title.getText().toString());
        bean.setDescription(description.getText().toString());
        bean.setSite_name(site_name.getText().toString());
        bean.setSite_url(site_url.getText().toString());
        bean.setTask_sum(Integer.parseInt(task_sum.getText().toString()));
        bean.setType_name(model.getType_name());
        bean.setTypeid(model.getTypeid());

        Gson gson = new Gson();
        String jsonStr = gson.toJson(bean);

        Notice notice=new Notice();
        notice.type= ConstanceValue.ADDTASKTYPE;
        notice.content = jsonStr;
        RxBus.getDefault().post(notice);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

         model =  mData.get(i);
        task_type.setText(model.getType_name());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDataSuccess(TaskTypeEntity data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected RequestAddEditPresent createPresenter() {
        return new RequestAddEditPresent(this);
    }

    @Override
    public void getTaskTypeSuccess(List<TaskTypeEntity> data) {
        mData.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
