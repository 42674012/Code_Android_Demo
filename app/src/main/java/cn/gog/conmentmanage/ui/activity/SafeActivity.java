package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.presenter.SettingPresent;
import cn.gog.conmentmanage.utils.gesture.activity.CreateGestureActivity;
import cn.gog.conmentmanage.utils.gesture.activity.GestureLoginActivity;
import cn.gog.conmentmanage.utils.gesture.util.cache.ACache;
import cn.gog.conmentmanage.utils.gesture.util.constant.Constant;
import cn.gog.conmentmanage.view.ISettingView;
import cn.jpush.android.api.JPushInterface;

public class SafeActivity extends BaseMvpActivity<SettingPresent> implements ISettingView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

//    @BindView(R.id.switch_tog)
//    SwitchCompat switch_tog ;

    @BindView(R.id.switch_gesture)
    SwitchCompat switch_gesture ;

    @BindView(R.id.pw_modify)
    LinearLayout pw_modify;

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected SettingPresent createPresenter() {
        return null;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_safe);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        switch_tog.setChecked(UserCache.getMysSwitch())  ;

        switch_gesture.setChecked(UserCache.getGesSwitch());
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        switch_tog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    JPushInterface.resumePush(SafeActivity.this);
//                }else {
//                    JPushInterface.stopPush(SafeActivity.this);
//                }
//                UserCache.putMysSwitch(b);
//            }
//        });
        pw_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SafeActivity.this, PwModifyActivity.class);
                startActivity(intent);
            }
        });
        switch_gesture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                UserCache.putGesSwitch(b);
                if(b){
                    Intent intent = new Intent(SafeActivity.this, CreateGestureActivity.class);
                    startActivity(intent);
//                    finish();
                }else{
                    ACache.get(SafeActivity.this).clear();
                }
            }
        });

    }

    @Override
    protected String gogTitle() {
        return null;
    }
}
