package cn.gog.conmentmanage.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.fragment.BaseMvpFragment;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.MeEntity;
import cn.gog.conmentmanage.presenter.MePresenter;
import cn.gog.conmentmanage.ui.activity.AboutActivity;
import cn.gog.conmentmanage.ui.activity.ContactActivity;
import cn.gog.conmentmanage.ui.activity.PersonInfoActivity;
import cn.gog.conmentmanage.ui.activity.SafeActivity;
import cn.gog.conmentmanage.ui.activity.SettingActivity;
import cn.gog.conmentmanage.ui.adapter.MeAdatper;
import cn.gog.conmentmanage.view.IMeView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.NoticeOberver;
import common.utils.PicassoLoader;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author:顾进
 * mail:1002606871@qq.com
 * date：2017/12/28
 * desc: 我的
 */
public class MeFragment extends BaseMvpFragment<MePresenter> implements IMeView  {


    @BindView(R.id.live_item_list)
    RecyclerView mLiveItemList;


    /**
     * 头像,不是头像了很
     */
    @BindView(R.id.iv_user_ico)
    CircleImageView ivUserIco;
    @BindView(R.id.user_name)
     TextView user_name;

    protected BaseQuickAdapter meAdapter;
    
    public List<MeEntity> mDatas = new ArrayList<>();

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews(View view) {

        ButterKnife.bind(this, rootView);


        MeEntity personnelInfo = new MeEntity();
        personnelInfo.setName("个人信息");
        personnelInfo.setType(MeEntity.PERSONNELINFO);
        personnelInfo.setResID(R.mipmap.personnel_info);
        mDatas.add(personnelInfo);

        MeEntity contactInfo = new MeEntity();
        contactInfo.setName("我的通讯录");
        contactInfo.setType(MeEntity.CONTACTINFO);
        contactInfo.setResID(R.mipmap.contact_icon);
        mDatas.add(contactInfo);

        MeEntity safety = new MeEntity();
        safety.setName("账号安全");
        safety.setType(MeEntity.SAFETY);
        safety.setResID(R.mipmap.safe_icon);
        mDatas.add(safety);

        MeEntity setting = new MeEntity();
        setting.setName("设置");
        setting.setType(MeEntity.SETTING);
        setting.setResID(R.mipmap.setting_icon);
        mDatas.add(setting);

        MeEntity about = new MeEntity();
        about.setName("关于");
        about.setType(MeEntity.ABOUT);
        about.setResID(R.mipmap.about_icon);
        mDatas.add(about);

        meAdapter = new MeAdatper(getActivity(), mDatas);
//        mLiveItemList.addItemDecoration(new SimpleDividerDecoration(getActivity()));
//        initCommonRecyclerView(mLiveItemList, messageAdapter, null);
        mLiveItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLiveItemList.setAdapter(meAdapter);

    }

    @Override
    protected void processLogic() {


        if(UserCache.get() != null){
            user_name.setText(UserCache.get().getUsername());
            PicassoLoader.displayImage(mContext, UserCache.get().getHeadUrl(), ivUserIco);
        }

        RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new NoticeOberver<Notice>() {
            @Override
            public void onNext(Notice message) {
                if (message.type == ConstanceValue.REFRESHHEADURL) {
                    PicassoLoader.displayImage(mContext, UserCache.get().getHeadUrl(), ivUserIco);
                }else if(message.type == ConstanceValue.ONUSERRELOGIN){
                    if(UserCache.get() != null){
                        user_name.setText(UserCache.get().getUsername());
                        PicassoLoader.displayImage(mContext, UserCache.get().getHeadUrl(), ivUserIco);
                    }
                }
            }
        });
    }

    @Override
    protected void setListener() {

        meAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int type = mDatas.get(position).getType();
                switch (type){
                    case MeEntity.PERSONNELINFO:
                        startActivity(new Intent(getActivity(),PersonInfoActivity.class));
                        break;
                    case MeEntity.SAFETY:
                        startActivity(new Intent(getActivity(), SafeActivity.class));
                        break;
                    case MeEntity.CONTACTINFO:
                        startActivity(new Intent(getActivity(),ContactActivity.class));
                        break;
                    case MeEntity.SETTING:
                        startActivity(new Intent(getActivity(),SettingActivity.class));
                        break;
                    case MeEntity.ABOUT:
                        startActivity(new Intent(getActivity(),AboutActivity.class));
                        break;
                }
            }
        });

    }


    @OnClick({R.id.iv_user_ico })
    public void onClick(View v)  {
        switch (v.getId()){
            case R.id.iv_user_ico:
                startActivity(new Intent(getActivity(),PersonInfoActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        processLogic();
    }

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

}