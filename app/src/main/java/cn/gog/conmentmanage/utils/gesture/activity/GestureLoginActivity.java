package cn.gog.conmentmanage.utils.gesture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.star.lockpattern.util.LockPatternUtil;
import com.star.lockpattern.widget.LockPatternView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.LiveTokenEntity;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.presenter.HomePresenter;
import cn.gog.conmentmanage.ui.activity.LoginActivity;
import cn.gog.conmentmanage.utils.gesture.util.cache.ACache;
import cn.gog.conmentmanage.utils.gesture.util.constant.Constant;
import cn.gog.conmentmanage.view.IHomeView;
import cn.jpush.android.api.JPushInterface;
import common.utils.ToastUtils;

/**
 * Created by Sym on 2015/12/24.
 *
 */
public class GestureLoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginGestureActivity";

    @BindView(R.id.lockPatternView)
    LockPatternView lockPatternView;
    @BindView(R.id.messageTv)
    TextView messageTv;
    @BindView(R.id.forgetGestureBtn)
    Button forgetGestureBtn;
    @BindView(R.id.user_name)
    TextView user_name;
    private ACache aCache;
    private static final long DELAYTIME = 600l;
    private byte[] gesturePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_login);
        ButterKnife.bind(this);
        this.init();
    }

    private void init() {
        aCache = ACache.get(GestureLoginActivity.this);
        //得到当前用户的手势密码
        gesturePassword = aCache.getAsBinary(Constant.GESTURE_PASSWORD);
        lockPatternView.setOnPatternListener(patternListener);
        updateStatus(Status.DEFAULT);
        user_name.setText(UserCache.get().getLogin_name());
    }

    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if(pattern != null){
                if(LockPatternUtil.checkPattern(pattern, gesturePassword)) {
                    updateStatus(Status.CORRECT);
                } else {
                    updateStatus(Status.ERROR);
                }
            }
        }
    };

    int index =0;
    /**
     * 更新状态
     * @param status
     */
    private void updateStatus(Status status) {
        messageTv.setText(status.strId);
        messageTv.setTextColor(getResources().getColor(status.colorId));
        switch (status) {
            case DEFAULT:
                index =0;
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                index = index+1;
                lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockPatternView.postClearPatternRunnable(DELAYTIME);
                if(index == 3){
                    ToastUtils.showShort("连续输错三次，请重新登录");
                    UserCache.putGesSwitch(false);
                    ACache.get(GestureLoginActivity.this).clear();
                    Intent goLogin = new Intent(GestureLoginActivity.this, LoginActivity.class);
                    startActivity(goLogin);
                    finish();
                }
                break;
            case CORRECT:
                index =0;
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
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
        finish();
    }

    /**
     * 忘记手势密码（去账号登录界面）
     */
    @OnClick(R.id.forgetGestureBtn)
    void forgetGesturePasswrod() {
        Intent intent = new Intent(GestureLoginActivity.this, LoginActivity.class);
        startActivity(intent);
        UserCache.putGesSwitch(false);
        ACache.get(GestureLoginActivity.this).clear();
        this.finish();
    }

    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.grey_a5a5a5),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.red_f4333c),
        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.grey_a5a5a5);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }
        private int strId;
        private int colorId;
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
}
