package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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

public class SettingActivity extends BaseMvpActivity<SettingPresent> implements ISettingView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.loginout_btn)
    TextView loginout_btn;
    @BindView(R.id.switch_tog)
    SwitchCompat switch_tog ;

//    @BindView(R.id.switch_gesture)
//    SwitchCompat switch_gesture ;

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
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      switch_tog.setChecked(UserCache.getMysSwitch())  ;


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
        loginout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserCache.clear();
//                startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                ACache aCache = ACache.get(SettingActivity.this);
                if(UserCache.getGesSwitch()){
                    String gesturePassword = aCache.getAsString(Constant.GESTURE_PASSWORD);

                    if(gesturePassword == null || "".equals(gesturePassword)) {
                        Intent goLogin = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(goLogin);
                    } else {
                        Intent intent = new Intent(SettingActivity.this, GestureLoginActivity.class);
                        startActivity(intent);
                    }

                }else{
                    Intent goLogin = new Intent(SettingActivity.this, LoginActivity.class);
                    startActivity(goLogin);
                }
                finish();
            }
        });
        switch_tog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    JPushInterface.resumePush(SettingActivity.this);
                }else {
                    JPushInterface.stopPush(SettingActivity.this);
                }
                UserCache.putMysSwitch(b);
            }
        });
//        switch_gesture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                UserCache.putGesSwitch(b);
//                if(b){
//                    Intent intent = new Intent(SettingActivity.this, CreateGestureActivity.class);
//                    startActivity(intent);
////                    finish();
//                }else{
//                    ACache.get(SettingActivity.this).clear();
//                }
//            }
//        });

    }

    @Override
    protected String gogTitle() {
        return null;
    }
}
