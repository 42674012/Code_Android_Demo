package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.ui.view.MaskFrameLayout;
import common.utils.DateUtils;
import common.utils.PicassoLoader;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetailActivity extends BaseActivity {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    ContactPage.UserInfoBean userInfo;

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
    BaseQuickAdapter wadapter;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_contact_detail);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String jsonStr = getIntent().getStringExtra("userinfo");

          userInfo =    new Gson().fromJson(jsonStr, ContactPage.UserInfoBean.class);

        if (userInfo != null){
            bumen.setText(userInfo.getDescription());
            tel.setText(userInfo.getPhone()+"");
            danwei.setText(userInfo.getOrg_name());
            email.setText(userInfo.getEmail()+"");
            PicassoLoader.displayImage(mContext, userInfo.getHeadUrl(), ivUserIco);
        } else
        {
            finish();
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
    }

    @Override
    protected String gogTitle() {
        return null;
    }
}
