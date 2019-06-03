package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.base.fragment.BaseFragment;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.LoginPresent;
import cn.gog.conmentmanage.presenter.UpdatePresent;
import cn.gog.conmentmanage.ui.fragment.FragmentController;
import cn.gog.conmentmanage.utils.gesture.activity.CreateGestureActivity;
import cn.gog.conmentmanage.utils.gesture.activity.GestureLoginActivity;
import cn.gog.conmentmanage.utils.gesture.util.cache.ACache;
import cn.gog.conmentmanage.utils.gesture.util.constant.Constant;
import cn.gog.conmentmanage.view.ILoginView;
import cn.gog.conmentmanage.view.IUpdateView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseMvpActivity<UpdatePresent>  implements IUpdateView {

    /**
     * 主菜单
     */
    @BindView(R.id.llBottom)
    LinearLayout llBottom;

    @BindView(R.id.ivIconHome)
    ImageView ivIconHome;


    @BindView(R.id.rlIconHome)
    RelativeLayout rlIconHome;

    @BindView(R.id.tvTextHome)
    TextView tvTextHome;


    private final int SDK_PERMISSION_REQUEST = 127;
    /**
     * 导航页面控制器
     */
    private FragmentController mController;




    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected UpdatePresent createPresenter() {
        if(mvpPresenter == null){
            mvpPresenter =new   UpdatePresent(this);
        }
        return mvpPresenter;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        UserInfo userInfo =   UserCache.get();
        if(userInfo == null){
            Intent goLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goLogin);
        }

        ACache aCache = ACache.get(MainActivity.this);


        if(UserCache.getGesSwitch()){
            String gesturePassword = aCache.getAsString(Constant.GESTURE_PASSWORD);

            if(gesturePassword == null || "".equals(gesturePassword)) {
                Intent intent = new Intent(MainActivity.this, CreateGestureActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, GestureLoginActivity.class);
                startActivity(intent);
            }

        }else{
            Intent goLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goLogin);
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mController = FragmentController.getInstance(this, R.id.fl_content, true);
        mController.showFragment(0);
        mController.setCurrentFrame((BaseFragment) mController.getFragment(0));


        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.RELOGIN) {


                        UserCache.clear();
                        UserCache.putGesSwitch(false);
                        ACache aCache =   ACache.get(MainActivity.this);
                        aCache.clear();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));

                }
            }
        });
    }
    private View lastSelectedIcon;
    private View lastSelectedText;
    private RelativeLayout lastSelectedRel;

    @Override
    protected void setListener() {

        for (int i = 0; i < llBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(rlIconHome,ivIconHome, tvTextHome);
            }
            final int position = i;
            llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mController.getCurrentFrame() != mController.getFragment(position)){
                        if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                        if (lastSelectedText != null) lastSelectedText.setSelected(false);
                        if (lastSelectedRel != null) lastSelectedRel.setSelected(false);

                        RelativeLayout rl = (RelativeLayout) v;
                        ImageView icon = (ImageView) rl.getChildAt(0);
                        TextView text = (TextView) rl.getChildAt(1);

                        mController.showFragment(position);
                        mController.setCurrentFrame((BaseFragment) mController.getFragment(position));
                        setSelectIcon(rl,icon, text);
                    }else{
                        mController.setCurrentFrame((BaseFragment) mController.getFragment(position));
                    }
                }
            });
        }

    }

    private void setSelectIcon(RelativeLayout rl,ImageView iv, TextView tv) {

        iv.setSelected(true);
        rl.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
        lastSelectedRel = rl;
    }


    @Override
    protected String gogTitle() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        //只有界在上的FRAME影应了之后（比如退出正在播放的视频)才能SUPER

        if (mController.getCurrentFrame().onBackPressed()) {
            super.onBackPressed();
        }
        System.out.println("按下了back键   onBackPressed()");
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
