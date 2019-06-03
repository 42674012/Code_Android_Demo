package cn.gog.conmentmanage.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.PersonPresent;
import cn.gog.conmentmanage.ui.view.MaskFrameLayout;
import cn.gog.conmentmanage.view.IPersonView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.DateUtils;
import common.utils.NetworkUtil;
import common.utils.PicassoLoader;
import common.utils.RxBus;
import common.utils.ToastUtils;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonInfoActivity extends BaseMvpActivity<PersonPresent> implements IPersonView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.bumen)
    TextView bumen;

    @BindView(R.id.danwei)
    TextView danwei;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.img_msg_icon)
    CircleImageView ivUserIco;

    @BindView(R.id.mask_view)
    MaskFrameLayout mask_view;
    @Override
    public void onDataSuccess(UserInfo data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }

    @Override
    protected PersonPresent createPresenter() {
        return new PersonPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_person_info);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (UserCache.get() != null){
             bumen.setText(UserCache.get().getOrg_name());
             tel.setText(UserCache.get().getPhone()+"");
             danwei.setText(UserCache.get().getOrg_name());
             email.setText(UserCache.get().getEmail()+"");
        }else{
            startActivity(new Intent(PersonInfoActivity.this,LoginActivity.class));
            finish();
        }
        if(UserCache.get() != null){
            PicassoLoader.displayImage(mContext, UserCache.get().getHeadUrl(), ivUserIco);
        }
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

    }

    @Override
    protected void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivUserIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PersonInfoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(PersonInfoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            SDK_PERMISSION_REQUEST);

                }else {
                    showGallery();
                }
            }
        });
    }

    @Override
    protected String gogTitle() {
        return null;
    }

    private void showGallery() {

        RxGalleryFinalApi.openRadioSelectImage(PersonInfoActivity.this,new RxBusResultDisposable<ImageRadioResultEvent>() {
            @Override
            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {

            }
        },false).onCropImageResult(new IRadioImageCheckedListener() {
            @Override
            public void cropAfter(Object t) {

                String cropPathPath = t.toString();
                if(cropPathPath == null){

                    return;
                }

                updateAvatar(cropPathPath);

            }

            @Override
            public boolean isActivityFinish() {
                Logger.i("返回false不关闭，返回true则为关闭");
                return true;
            }
        });

    }

    /**
     * 修改头像
     *
     * @param avatarPath
     */
    private void updateAvatar(String avatarPath) {
        File avatarFile = new File(avatarPath);
        //更新头像
        showLoadingDialog();
        createPresenter().upLoadHeadimage("image", avatarFile);
    }

    @Override
    public void modifyOk(String data) {
        hideLoadingDialog();
        UserInfo info = UserCache.get();
        info.setHeadUrl(data);
        UserCache.put(info);
        PicassoLoader.displayImage(mContext, UserCache.get().getHeadUrl(), ivUserIco);
        Notice notice=new Notice();
        notice.type= ConstanceValue.REFRESHTASKDETAIL;
        RxBus.getDefault().post(notice);
    }
    private final int SDK_PERMISSION_REQUEST = 127;
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SDK_PERMISSION_REQUEST) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (NetworkUtil.isConnected(PersonInfoActivity.this)) {

                  showGallery();
                }
                // 获取到权限，作相应处理
            } else {
                // 没有获取到权限，做特殊处理
                Toast.makeText(this, "获得权限失败,您可以设置中打开", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

}
