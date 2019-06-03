package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.presenter.EditDutyPresent;
import cn.gog.conmentmanage.ui.adapter.StringAdapter;
import cn.gog.conmentmanage.view.IEditDutyView;
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

public class EditDutyActivity extends BaseMvpActivity<EditDutyPresent> implements IEditDutyView {



    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.last_response_time)
    RadioGroup last_response_time;
    @BindView(R.id.dingshifankui)
    RadioButton dingshifankui;
    @BindView(R.id.zhouqi)
    RadioButton zhouqi;


    @BindView(R.id.deadline_l)
    LinearLayout deadline_l;

    @BindView(R.id.recycle_ll)
    LinearLayout recycle_ll;

    @BindView(R.id.feedback_type_deadline)
    TextView feedback_type_deadline;


    List<DutyDetailModel.TaskDetailsVoListBean> mDatas;
    private int feedback_type;
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
    private int feedback_cycle =1;

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
    @BindView(R.id.task_name_tv)
    TextView task_name_tv;
    private boolean iscustom;
    private String taskid;
    private DutyDetailModel dutyDetailModel;

    @BindView(R.id.task_create_time)
    TextView task_create_time;
    @BindView(R.id.task_code)
    TextView task_code;
    @BindView(R.id.create_time)
    TextView create_time;
    @BindView(R.id.create_man)
    TextView create_man;
    @BindView(R.id.publish_state)
    TextView publish_state;


    @BindView(R.id.one_day)
    RadioButton one_day;
    @BindView(R.id.one_mouth)
    RadioButton  one_mouth ;
    @BindView(R.id.one_week)
    RadioButton  one_week ;
    @BindView(R.id.custom)
    RadioButton custom ;

    @BindView(R.id.long_task)
    RadioButton long_task;
    @BindView(R.id.short_task)
    RadioButton short_task;


    @BindView(R.id.feedback_type_deadline_l)
    LinearLayout feedback_type_deadline_l;

      int custom_feedback_cycle =0  ;


    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

        hideLoadingDialog();

    }

    @Override
    protected EditDutyPresent createPresenter() {
        return new EditDutyPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_edit_duty);
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
        createPresenter().taskById(taskid);
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



    @Override
    public void taskByIdSuccess(DutyDetailModel data) {
        dutyDetailModel = data;
        task_name.setText(data.getTask_name());
        task_name_tv.setText(data.getTask_name());
        task_create_time.setText(DateUtils.toDateStr(new Date(data.getCreatetime())));
        task_code.setText(data.getTask_code());
        create_man.setText(data.getCreater_manager_username());
        create_time.setText(DateUtils.toDateStr(new Date(data.getCreatetime())));
        publish_state.setText("未发布");
        deadline.setText(DateUtils.toDateStr(new Date(data.getDeadline())));

        mDatas.clear();
        mDatas.addAll(data.getTaskDetailsVoList());
        orgEntityList.clear();
        for(int i =0;i<data.getResponseOrgList().size();i++){
            OrgEntity entity = new OrgEntity();
            entity.setOrg_name(data.getResponseOrgList().get(i).getLabel());
            entity.setOrgid(data.getResponseOrgList().get(i).getId());
            orgEntityList.add(entity);
        }

        if (data.isTask_long_term()) {

            deadline_l.setVisibility(View.GONE);
            long_task.setChecked(true);
            short_task.setChecked(false);
        } else {
            short_task.setChecked(true);
            long_task.setChecked(false);
        }

        if (data.getFeedback_type() == 0) {
            dingshifankui.setChecked(true);
            feedback_type_deadline_l.setVisibility(View.VISIBLE);
            recycle_ll.setVisibility(View.GONE);
            feedback_type_deadline.setText(DateUtils.toDateStr(new Date(data.getFeedback_type_deadline())));
        } else {
            zhouqi.setChecked(true);
            feedback_type_deadline_l.setVisibility(View.GONE);
            recycle_ll.setVisibility(View.VISIBLE);
            switch (data.getFeedback_cycle()) {
                case 1:
                    custom_l.setVisibility(View.GONE);
                    one_day.setChecked(true);
                    break;
                case 7:
                    custom_l.setVisibility(View.GONE);
                    one_week.setChecked(true);
                    break;
                case 30:
                    custom_l.setVisibility(View.GONE);
                    one_mouth.setChecked(true);
                    break;
                default:
                    custom_l.setVisibility(View.VISIBLE);
                    custom_edit.setText(data.getFeedback_cycle()+"");

                    custom.setChecked(true);
                    break;
            }

        }


        switch (data.getTask_urgency_degree()) {
            case 0:
                task_urgency_degree_tv.setText("一般");
                task_urgency_degree = 0;
                break;
            case 1:
                task_urgency_degree_tv.setText("紧急");
                task_urgency_degree = 1;
                break;
            case 2:
                task_urgency_degree_tv.setText("非常紧急");
                task_urgency_degree = 2;
                break;
        }
        deadline_date = new Date(data.getDeadline());

        feedback_type_deadline_date = new Date(data.getFeedback_type_deadline());
        feedback_type_deadline.setText(DateUtils.toDateStr(new Date(data.getFeedback_type_deadline())));
        switch (data.getTask_urgency_degree()) {
            case 0:
                task_importance_degree_tv.setText("一般");
                task_importance_degree = 0;
                break;
            case 1:
                task_importance_degree_tv.setText("重要");
                task_importance_degree = 1;
                break;
            case 2:
                task_importance_degree_tv.setText("非常重要");
                task_importance_degree = 2;
                break;
        }

        deadline.setText(DateUtils.toDateStr(new Date(data.getDeadline())));

        StringBuffer buffer = new StringBuffer();
        if (data.getResponseOrgList() != null) {
            for (int i = 0; i < data.getResponseOrgList().size(); i++) {
                if (data.getResponseOrgList().get(i) != null) {
                    buffer.append(data.getResponseOrgList().get(i).getLabel()).append("  ");
                }
            }
        }

        responseOrgList.setText(buffer.toString());
    }

    @Override
    public void taskUpdateSuccess(String data) {
        showToast(data);
        Notice notice=new Notice();
        notice.type= ConstanceValue.REFRESHTASKDETAIL;
        RxBus.getDefault().post(notice);
        finish();
    }

    @OnClick({R.id.task_details_l, R.id.feedback_type_deadline_l,
            R.id.deadline_l, R.id.task_urgency_degree_l,
            R.id.task_importance_degre_l, R.id.add_request,
            R.id.save, R.id.publish
    })
    public void click(View view) {
        switch (view.getId()) {
            case R.id.task_details_l:
                Intent mIntent = new Intent(EditDutyActivity.this, DutyDetailRequestAddActivity.class);
                mIntent.putExtra("list", new Gson().toJson(mDatas));
                startActivity(mIntent);
                break;
            case R.id.feedback_type_deadline_l:
                showTimeDialog();
                break;
            case R.id.deadline_l:
                showTimeDialog1();
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
                Intent mJIntent = new Intent(EditDutyActivity.this, AddRequestOrgActivity.class);
                mJIntent.putExtra("list", new Gson().toJson(orgEntityList));
                startActivity(mJIntent);
                break;

            case R.id.save:

                if(judgeAll()){
                    int  daySum = feedback_cycle;

                    if (feedback_type == 1 && iscustom){
                        daySum = custom_feedback_cycle;
                    }

                    createPresenter().taskSave(
                            taskid,
                            task_name.getText().toString(),
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
                    createPresenter().taskSaveAndPublish(
                            taskid,
                            task_name.getText().toString(),
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
        TimeSelectorDialog dialog = new TimeSelectorDialog(EditDutyActivity.this);
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
        TimeSelectorDialog dialog = new TimeSelectorDialog(EditDutyActivity.this);
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
}
