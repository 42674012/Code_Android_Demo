package cn.gog.conmentmanage.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.LiveTokenEntity;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.HomePresenter;
import cn.gog.conmentmanage.presenter.LoginPresent;
import cn.gog.conmentmanage.utils.gesture.util.cache.ACache;
import cn.gog.conmentmanage.view.IHomeView;
import cn.gog.conmentmanage.view.ILoginView;
import cn.jpush.android.api.JPushInterface;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.RxBus;
import common.utils.StatusBarCompat;


/**
 * 登录页面
 */
public class LoginActivity extends BaseMvpActivity<LoginPresent> implements ILoginView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    LoginPresent mvpPresenter;
    @BindView(R.id.ed_account)
    EditText mAccount;
    @BindView(R.id.ed_pwd)
    EditText mPwd;
    @BindView(R.id.divide_a)
    View divide_a ;
    @BindView(R.id.divide_p)
    View divide_p;
    public  static  boolean isAlive = false;

    @Override
    protected LoginPresent createPresenter() {

        if (mvpPresenter == null) {
            mvpPresenter = new LoginPresent(this);
        }

        return mvpPresenter;
    }

    @Override
    protected void loadViewLayout() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        StatusBarCompat.translucentStatusBar(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
    }

    @OnClick({R.id.btn_login})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.btn_login:

                if(jugeAll()){

                    showLoadingDialog();
                    createPresenter().login(mAccount.getText().toString(),mPwd.getText().toString());

                }
        }
    }

    private boolean jugeAll() {

        if (TextUtils.isEmpty(mAccount.getText().toString())){

            showToast("请输入账号");
            return  false;
        }
        if (TextUtils.isEmpty(mPwd.getText().toString())){

            showToast("请输入密码");
            return  false;
        }

        return  true;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        isAlive =true;
    }

    @Override
    protected void setListener() {
        mAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(charSequence.toString())){

                    divide_a.setBackgroundColor(getResources().getColor(R.color.divider));
                }else {
                    divide_a.setBackgroundColor(Color.parseColor("#5b8cff"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(charSequence.toString())){

                    divide_p.setBackgroundColor(getResources().getColor(R.color.divider));
                }else {
                    divide_p.setBackgroundColor(Color.parseColor("#5b8cff"));
                }
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
    public void onDataSuccess(UserInfo data) {

    }

    @Override
    public void onCompleted() {

        hideLoadingDialog();
    }

    @Override
    public void logoinSuccess(UserInfo data) {

        UserCache.put(data);
        UserCache.putGesSwitch(false);//强制清除缓存
        ACache.get(LoginActivity.this).clear();

        Notice notice=new Notice();
        notice.type= ConstanceValue.ONUSERRELOGIN;
        RxBus.getDefault().post(notice);

        finish();
        loginGestureSuccess();
    }

    /**
     * 手势登录成功（去首页）
     */
    private void loginGestureSuccess() {

        new HomePresenter(new IHomeView() {
            @Override
            public void getTokenOk(LiveTokenEntity data) {

            }

            @Override
            public void getDecodeResultOk(String data) {

            }

            @Override
            public void getNewestMsfListSuccess(List<MsgEntity> data) {

            }

            @Override
            public void onDataSuccess(Object data) {

            }

            @Override
            public void onCompleted() {

            }
        }).jiguangPush(JPushInterface.getRegistrationID(GogApplication.getAppContext()));

    }
    /**
     * 手机返回键操作
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAlive = false;
    }
}

