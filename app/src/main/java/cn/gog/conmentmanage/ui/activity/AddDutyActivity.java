package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.presenter.AddDutyPresent;
import cn.gog.conmentmanage.ui.adapter.StringAdapter;
import cn.gog.conmentmanage.view.IAddDutyView;
import common.model.Notice;
import common.timeselector.DateListener;
import common.timeselector.TimeConfig;
import common.timeselector.TimeSelectorDialog;
import common.utils.ConstanceValue;
import common.utils.DateUtils;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import common.utils.ToastUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddDutyActivity extends BaseMvpActivity<AddDutyPresent> implements IAddDutyView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.last_response_time)
    RadioGroup last_response_time;
    @BindView(R.id.dingshifankui)
    RadioButton dingshifankui;
    @BindView(R.id.zhouqi)
    RadioButton zhouqi;


    @BindView(R.id.feedback_type_deadline_l)
    LinearLayout feedback_type_deadline_l;

    @BindView(R.id.deadline_l)
    LinearLayout deadline_l;

    @BindView(R.id.recycle_ll)
    LinearLayout recycle_ll;

    @BindView(R.id.feedback_type_deadline)
    TextView feedback_type_deadline;


    List<DutyDetailModel.TaskDetailsVoListBean> mDatas;
    private int feedback_type =0;
    private Date feedback_type_deadline_date;


    @BindView(R.id.deadline)
    TextView deadline;

    @BindView(R.id.task_urgency_degree_list)
    RecyclerView task_urgency_degree_list;
    StringAdapter task_urgency_degree_list_adapter;

    List<String> task_urgency_degree_list_str;

    private Date deadline_date;
    private int task_urgency_degree = -1;
    @BindView(R.id.task_urgency_degree_tv)
    TextView task_urgency_degree_tv;


    @BindView(R.id.task_importance_degree_list)
    RecyclerView task_importance_degree_list;
    StringAdapter task_importance_degree_list_adapter;

    List<String> task_importance_degree_list_str;


    @BindView(R.id.task_importance_degree_tv)
    TextView task_importance_degree_tv;
    private int task_importance_degree = -1;

    @BindView(R.id.custom_l)
    LinearLayout custom_l;
    @BindView(R.id.custom_tv)
    TextView custom_tv;
    @BindView(R.id.recycle)
    RadioGroup recycle;


    private int custom_feedback_cycle = 0 ;
    private int feedback_cycle =1 ;

    @BindView(R.id.responseOrgList)
    TextView responseOrgList;

    private List<OrgEntity> orgEntityList;
    @BindView(R.id.custom_edit)
    EditText custom_edit;
    @BindView(R.id.is_long)
    RadioGroup is_long;
    private boolean task_long_term;

    @BindView(R.id.task_name)
    EditText task_name;
    private boolean iscustom ;

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected AddDutyPresent createPresenter() {
        return new AddDutyPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_add_duty);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatas = new ArrayList<>();


        task_urgency_degree_list_str = new ArrayList<>();
        task_urgency_degree_list_str.add("一般");
        task_urgency_degree_list_str.add("紧急");
        task_urgency_degree_list_str.add("非常紧急");
        task_urgency_degree_list_adapter = new StringAdapter(this, task_urgency_degree_list_str);

        task_urgency_degree_list.setLayoutManager(new LinearLayoutManager(this));
        task_urgency_degree_list.setAdapter(task_urgency_degree_list_adapter);


        task_importance_degree_list_str = new ArrayList<>();
        task_importance_degree_list_str.add("一般");
        task_importance_degree_list_str.add("重要");
        task_importance_degree_list_str.add("非常重要");

        task_importance_degree_list_adapter = new StringAdapter(this, task_importance_degree_list_str);
        task_importance_degree_list.setLayoutManager(new LinearLayoutManager(this));
        task_importance_degree_list.setAdapter(task_importance_degree_list_adapter);

        orgEntityList = new ArrayList<>();
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
                if (message.type == ConstanceValue.ADDTASKTYPELIST) {

                    Gson gson = new Gson();
                    List<DutyDetailModel.TaskDetailsVoListBean> listBena = gson.fromJson(message.content.toString(), new TypeToken<List<DutyDetailModel.TaskDetailsVoListBean>>() {
                    }.getType());

                    if (listBena != null) {
                        mDatas.clear();
                        mDatas.addAll(listBena);
                    }
                } else if (message.type == ConstanceValue.ADDJIESHOULIST) {
                    Gson gson = new Gson();
                    List<OrgEntity> orgList = gson.fromJson(message.content.toString(), new TypeToken<List<OrgEntity>>() {
                    }.getType());

                    if (orgList != null) {
                        orgEntityList.clear();
                        orgEntityList.addAll(orgList);

                        StringBuffer buffer = new StringBuffer();
                        for (OrgEntity bean : orgEntityList) {
                            buffer.append(bean.getOrg_name()).append("  ");
                        }
                        responseOrgList.setText(buffer.toString());

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
                finish();
            }
        });


        last_response_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.dingshifankui:
                        feedback_type = 0;
                        recycle_ll.setVisibility(View.GONE);
                        feedback_type_deadline_l.setVisibility(View.VISIBLE);

                        break;
                    case R.id.zhouqi:
                        feedback_type = 1;
                        recycle_ll.setVisibility(View.VISIBLE);
                        feedback_type_deadline_l.setVisibility(View.GONE);
                        break;
                }

            }
        });

        task_urgency_degree_list_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                task_urgency_degree = position;
                task_urgency_degree_list.setVisibility(View.GONE);
                task_urgency_degree_tv.setText(task_urgency_degree_list_str.get(position));
            }
        });

        task_importance_degree_list_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                task_importance_degree = position;
                task_importance_degree_list.setVisibility(View.GONE);
                task_importance_degree_tv.setText(task_importance_degree_list_str.get(position));
            }
        });


        recycle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.one_day:
                        feedback_cycle = 1;
                        custom_l.setVisibility(View.GONE);
                        iscustom = false;
                        break;
                    case R.id.one_week:
                        feedback_cycle = 7;
                        custom_l.setVisibility(View.GONE);
                        iscustom = false;
                        break;
                    case R.id.one_mouth:
                        feedback_cycle = 30;
                        iscustom = false;
                        custom_l.setVisibility(View.GONE);
                        break;
                    case R.id.custom:
                        iscustom = true;
                        custom_l.setVisibility(View.VISIBLE);
                        break;

                }

            }
        });

        is_long.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.long_task:
                        task_long_term = true;
                        dingshifankui.setEnabled(false);
                        zhouqi.setChecked(true);
                        deadline.setText("");

                        break;
                    case R.id.short_task:
                        task_long_term = false;
                        dingshifankui.setEnabled(true);
                        dingshifankui.setChecked(true);

                        break;
                }
            }
        });

        custom_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                custom_feedback_cycle =Integer.parseInt(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected String gogTitle() {
        return null;
    }

    @OnClick({R.id.task_details_l, R.id.feedback_type_deadline_l,
            R.id.deadline_l, R.id.task_urgency_degree_l,
            R.id.task_importance_degre_l, R.id.add_request,
            R.id.save, R.id.publish
    })
    public void click(View view) {
        switch (view.getId()) {
            case R.id.task_details_l:
                Intent mIntent = new Intent(AddDutyActivity.this, DutyDetailRequestAddActivity.class);
                mIntent.putExtra("list", new Gson().toJson(mDatas));
                startActivity(mIntent);
                break;
            case R.id.feedback_type_deadline_l:
                showTimeDialog();
                break;
            case R.id.deadline_l:

                if(!task_long_term ){
                    showTimeDialog1();
                }

                break;
            case R.id.task_urgency_degree_l:

                if (task_urgency_degree_list.getVisibility() == View.GONE) {
                    task_urgency_degree_list.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.task_importance_degre_l:

                if (task_importance_degree_list.getVisibility() == View.GONE) {
                    task_importance_degree_list.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.add_request:
                Intent mJIntent = new Intent(AddDutyActivity.this, AddRequestOrgActivity.class);
                mJIntent.putExtra("list", new Gson().toJson(orgEntityList));
                startActivity(mJIntent);
                break;

            case R.id.save:

                if(judgeAll()){

                    int  daySum = feedback_cycle;

                    if (feedback_type == 1 && iscustom){
                        daySum = custom_feedback_cycle;
                    }
                        createPresenter().taskAdd(task_name.getText().toString(),
                                task_urgency_degree,
                                task_importance_degree,
                                task_long_term,
                                deadline_date,
                                feedback_type,
                                feedback_type_deadline_date,
                                daySum,
                                new Gson().toJson(orgEntityList),
                                new Gson().toJson(mDatas)
                                );
                }
                break;

            case R.id.publish:

                if(judgeAll()){
                    int  daySum = feedback_cycle;
                    if (feedback_type == 1 && iscustom){
                        daySum = custom_feedback_cycle;
                    }
                    createPresenter().taskAddAndPublish(task_name.getText().toString(),
                            task_urgency_degree,
                            task_importance_degree,
                            task_long_term,
                            deadline_date,
                            feedback_type,
                            feedback_type_deadline_date,
                            daySum,
                            new Gson().toJson(orgEntityList),
                            new Gson().toJson(mDatas)
                    );
                }
                break;
        }
    }

    private boolean judgeAll() {

        if(TextUtils.isEmpty(task_name.getText().toString())){
            showToast("请输入任务名称");
            return  false;
        }
        if(task_urgency_degree == -1){
            showToast("请选择任务紧急情况");
            return  false;
        }
        if(task_importance_degree == -1){
            showToast("请选择任务重要情况");
            return  false;
        }

        if(!task_long_term){
            if(TextUtils.isEmpty(deadline.getText().toString())){
                showToast("请输入选择截止时间");
                return  false;
            }else{
                if(deadline_date.before(new Date())  ){
                    showToast("截止时间应晚于创建时间");
                    return  false;
                }
            }
        }


        if(task_long_term){

            if(feedback_type == 1 && iscustom && TextUtils.isEmpty(custom_edit.getText().toString())){

                    showToast("请输入自定义反馈周期");
                    return  false;
                }


        }else {
            if(feedback_type == 1){
                if(iscustom && TextUtils.isEmpty(custom_edit.getText().toString())){
                    showToast("请输入自定义反馈周期");
                    return  false;
                }

            }else{

                if (TextUtils.isEmpty(feedback_type_deadline.getText().toString())) {
                    showToast("请输入限时反馈时间");
                    return false;
                }else {
                    if(feedback_type_deadline_date.before(new Date())  )
                    {
                        showToast("最后反馈时间应晚于创建时间");
                        return  false;
                    }
                }
                if (deadline_date.before(feedback_type_deadline_date)){


                    showToast("最后反馈时间应小于截至时间");
                    return  false;
                }
        }
        }

        if(mDatas.size() ==0){
            showToast("任务详细要求不能为空");
            return  false;
        }

//        if(orgEntityList.size() ==0){
//            showToast("任务接收方不能为空");
//            return  false;
//        }

        return true;
    }

    private void showTimeDialog1() {
        TimeSelectorDialog dialog = new TimeSelectorDialog(AddDutyActivity.this);
        //设置标题
        dialog.setTimeTitle("选择时间:");
        //显示类型
        dialog.setIsShowtype(TimeConfig.YEAR_MONTH_DAY_HOUR_MINUTE);
        //默认时间
        dialog.setCurrentDate(DateUtils.getFormatedDateTime(new Date().getTime()));
        //隐藏清除按钮

        dialog.setEmptyIsShow(false);
        //设置起始时间
        dialog.setStartYear(2000);
        dialog.setDateListener(new DateListener() {
            @Override
            public void onReturnDate(String time, int year, int month, int day, int hour, int minute, int isShowType) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                deadline.setText(time);

                try {
                    deadline_date = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                    deadline_date = new Date();
                }
            }

            @Override
            public void onReturnDate(String empty) {

            }
        });
        dialog.show();
    }

    private void showTimeDialog() {
        TimeSelectorDialog dialog = new TimeSelectorDialog(AddDutyActivity.this);
        //设置标题
        dialog.setTimeTitle("选择时间:");
        //显示类型
        dialog.setIsShowtype(TimeConfig.YEAR_MONTH_DAY_HOUR_MINUTE);

        //默认时间
        dialog.setCurrentDate(DateUtils.getFormatedDateTime(new Date().getTime()));
        //隐藏清除按钮

        dialog.setEmptyIsShow(false);

        //设置起始时间
        dialog.setStartYear(2000);
        dialog.setDateListener(new DateListener() {
            @Override
            public void onReturnDate(String time, int year, int month, int day, int hour, int minute, int isShowType) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                feedback_type_deadline.setText(time);

                try {
                    feedback_type_deadline_date = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                    feedback_type_deadline_date = new Date();
                }
            }

            @Override
            public void onReturnDate(String empty) {

            }
        });
        dialog.show();
    }

    @Override
    public void taskAddSuccess(String data) {

        showToast(data);
        Notice notice=new Notice();
        notice.type= ConstanceValue.REFRESHTASK;
        RxBus.getDefault().post(notice);
        finish();
    }
}
