package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.DutyDetailPresent;
import cn.gog.conmentmanage.presenter.DutyPresent;
import cn.gog.conmentmanage.ui.view.MaskFrameLayout;
import cn.gog.conmentmanage.view.IDutyDetailView;
import cn.gog.conmentmanage.view.IDutyView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.DateUtils;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import common.utils.ToastUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DutyDetailActivity extends BaseMvpActivity<DutyDetailPresent> implements IDutyDetailView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    String taskid;

    @BindView(R.id.task_code)
    TextView task_code;

    @BindView(R.id.create_man)
    TextView create_man;
    @BindView(R.id.create_time)
    TextView create_time;
    @BindView(R.id.publish_state)
    TextView publish_state;
    @BindView(R.id.emergince_level)
    TextView emergince_level;
    @BindView(R.id.important_level)
    TextView important_level;

    //    @BindView(R.id.important_level)
//    TextView  deadline_time;
    @BindView(R.id.last_time)
    TextView last_time;
    @BindView(R.id.responseOrgList)
    TextView responseOrgList;

    DutyDetailModel dutyDetailModel;

    @BindView(R.id.buttonPanel)
    LinearLayout buttonPanel;


    @BindView(R.id.paifa)
    Button paifa;
    @BindView(R.id.xiafa)
    Button xiafa;

    @BindView(R.id.task_name)
    TextView task_name;
    @BindView(R.id.task_create_time)
    TextView task_create_time;


    @BindView(R.id.is_long)
    RadioGroup is_long;
    @BindView(R.id.long_task)
    RadioButton long_task;
    @BindView(R.id.short_task)
    RadioButton short_task;

    @BindView(R.id.last_response_time)
    RadioGroup last_response_time;
    @BindView(R.id.dingshifankui)
    RadioButton dingshifankui;
    @BindView(R.id.zhouqi)
    RadioButton zhouqi;

    @BindView(R.id.feedback_type_deadline)
    TextView feedback_type_deadline;

    Menu menu1;
    @BindView(R.id.recycle)
    RadioGroup recycle;

    @BindView(R.id.one_day)
      RadioButton one_day;
    @BindView(R.id.one_mouth)
    RadioButton  one_mouth ;
    @BindView(R.id.one_week)
    RadioButton  one_week ;
    @BindView(R.id.custom)
    RadioButton custom ;

    @BindView(R.id.custom_l)
       LinearLayout custom_l;
    @BindView(R.id.custom_edit)
    EditText custom_edit;

    @BindView(R.id.feedback_type_deadline_l)
    LinearLayout feedback_type_deadline_l;
    @BindView(R.id.recycle_ll)
    LinearLayout recycle_ll;

    @BindView(R.id.deadline_time)
    TextView  deadline_time;
    @BindView(R.id.root)
    NestedScrollView root;

    @BindView(R.id.deadline_time_l)
    LinearLayout deadline_time_l;



    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }

    @Override
    protected DutyDetailPresent createPresenter() {
        return new DutyDetailPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_duty_detail);
    }

    @Override
    protected void refreshData() {

    }
    @BindView(R.id.mask_view)
    MaskFrameLayout mask_view;

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskid = getIntent().getStringExtra("taskid");
        UserInfo info = UserCache.get();
        String textStr= info.getUsername()+info.getOrg_name()+ DateUtils.toDateYMStr(new Date()).toString();
        for(int i=0;i<10;i++){
            TextView v = new TextView(this);
            v.setText(textStr);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            mask_view.addView(v, i, params);
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
        createPresenter().taskById(taskid);
        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.REFRESHTASKDETAIL) {
                    createPresenter().taskById(taskid);
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
    }

    @Override
    protected String gogTitle() {
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_duty_detail, menu);
        menu1 = menu;
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_btn:
                if (TextUtils.equals(item.getTitle(), "编辑")) {


                    if(dutyDetailModel != null) {
                        Intent editIntent = new Intent(DutyDetailActivity.this, EditDutyActivity.class);
                        editIntent.putExtra("taskid", taskid);
                        startActivity(editIntent);
                    }
                } else if (TextUtils.equals(item.getTitle(), "完结任务")) {

                    if(dutyDetailModel != null){


                    if (UserCache.get() != null) {
                        showLoadingDialog();
                        createPresenter().taskFinish(dutyDetailModel.getTaskid(), UserCache.get().getUserid());
                    }
                    }
                }
                break;
            case R.id.delete_btn:

                if(dutyDetailModel != null){
                if (UserCache.get() != null) {
                    showLoadingDialog();
                    createPresenter().taskCancel(dutyDetailModel.getTaskid(), UserCache.get().getUserid());
                }}
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void taskByIdSuccess(DutyDetailModel data) {

        if(data == null){
            root.setVisibility(View.GONE);
            ToastUtils.showShort("任务已删除");

            return;
        }
        dutyDetailModel = data;
        task_name.setText(data.getTask_name());

        task_create_time.setText(DateUtils.toDateStr(new Date(data.getCreatetime())));
        task_code.setText(data.getTask_code());
        create_man.setText(data.getCreater_manager_username());
        create_time.setText(DateUtils.toDateStr(new Date(data.getCreatetime())));

        switch (data.getTask_state()) {
            case 0:
                publish_state.setText("未发布");
                MenuItem item = menu1.getItem(0);
                item.setTitle("编辑");
                item.setIcon(R.mipmap.edit);
                paifa.setText("发布");
                xiafa.setText("删除");
                xiafa.setVisibility(View.GONE);
                break;
            case 1:
                publish_state.setText("已发布");
                break;
            case 2:
                buttonPanel.setVisibility(View.GONE);
                publish_state.setText("已完结");
                menu1.clear();
                break;
            case 3:
                buttonPanel.setVisibility(View.GONE);
                publish_state.setText("已取消");
                menu1.clear();
                break;
        }
        if (data.isTask_long_term()) {
            deadline_time.setText("长期");
//            last_time.setVisibility(View.GONE);
//            deadline_time_l.setVisibility(View.GONE);
//            long_task.setChecked(true);
//            short_task.setChecked(false);
        } else {
//            short_task.setChecked(true);
//            long_task.setChecked(false);
            deadline_time.setText(DateUtils.toDateStr(new Date(data.getDeadline())));
        }

        if (data.getFeedback_type() == 0) {
//            dingshifankui.setChecked(true);
//            feedback_type_deadline_l.setVisibility(View.VISIBLE);
//            recycle_ll.setVisibility(View.GONE);
            last_time.setText(DateUtils.toDateStr(new Date(data.getFeedback_type_deadline())));
        } else {

            last_time.setText("每"+ data.getFeedback_cycle()+"天");
//            zhouqi.setChecked(true);
//            feedback_type_deadline_l.setVisibility(View.GONE);
//            recycle_ll.setVisibility(View.VISIBLE);
//
//            switch (data.getFeedback_cycle()) {
//                case 1:
//                    custom_l.setVisibility(View.GONE);
//                    one_day.setChecked(true);
//                    break;
//                case 7:
//                    custom_l.setVisibility(View.GONE);
//                    one_week.setChecked(true);
//                    break;
//                case 30:
//                    custom_l.setVisibility(View.GONE);
//                    one_mouth.setChecked(true);
//                    break;
//                default:
//                   custom_l.setVisibility(View.VISIBLE);
//                   custom_edit.setText(data.getFeedback_cycle()+"天");
//                   custom.setChecked(true);
//                    break;
//            }

        }


        switch (data.getTask_urgency_degree()) {
            case 0:
                emergince_level.setText("一般");
                break;
            case 1:
                emergince_level.setText("紧急");
                break;
            case 2:
                emergince_level.setText("非常紧急");
                break;
        }

        switch (data.getTask_urgency_degree()) {
            case 0:
                important_level.setText("一般");
                break;
            case 1:
                important_level.setText("重要");
                break;
            case 2:
                important_level.setText("非常重要");
                break;
        }

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
    public void cancelSuccess(String data) {
        showLoadingDialog();
        showToast(data);
        Notice notice = new Notice();
        notice.type = ConstanceValue.REFRESHTASK;
        RxBus.getDefault().post(notice);
        finish();

    }

    @Override
    public void finishSuccess(String data) {
        showLoadingDialog();
        showToast(data);
        Notice notice = new Notice();
        notice.type = ConstanceValue.REFRESHTASK;
        RxBus.getDefault().post(notice);
        finish();
    }

    @Override
    public void publishSuccess(String data) {
        showLoadingDialog();
        showToast(data);
        Notice notice = new Notice();
        notice.type = ConstanceValue.REFRESHTASK;
        RxBus.getDefault().post(notice);
        finish();
    }

    @OnClick({R.id.task_detailsll, R.id.paifa, R.id.xiafa})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.task_detailsll:

                Intent mIntent = new Intent(DutyDetailActivity.this, DutyDetailRequestActivity.class);

                Gson gson = new Gson();
                mIntent.putExtra("list", gson.toJson(dutyDetailModel.getTaskDetailsVoList()));
                startActivity(mIntent);

                break;

            case R.id.paifa:
                if (TextUtils.equals(paifa.getText().toString(), "发布")) {
                    if (UserCache.get() != null) {
                        showLoadingDialog();
                        createPresenter().taskPublish(dutyDetailModel.getTaskid(), UserCache.get().getUserid());
                    }
                } else {

                    Intent mIntentRe = new Intent(DutyDetailActivity.this, AddRequestOrgActivity.class);

                    mIntentRe.putExtra("taskid", taskid);

                    startActivity(mIntentRe);

                }

                break;
            case R.id.xiafa:
                if (TextUtils.equals(xiafa.getText().toString(), "删除")) {
                    showToast("删除，暂时未提供");
                } else {
                    Intent mIntent1 = new Intent(DutyDetailActivity.this, XiaFaActivity.class);
                    mIntent1.putExtra("taskId", taskid);
                    startActivity(mIntent1);

                }
                break;
        }
    }
}
