package cn.gog.conmentmanage.ui.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.presenter.AddWritingPresent;
import cn.gog.conmentmanage.view.IAddWritingView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import common.utils.ToastUtils;

public class AddWritingActivity extends BaseMvpActivity<AddWritingPresent> implements IAddWritingView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.biaoti)
    EditText biaoti;
    @BindView(R.id.lianjiedizhi)
    EditText lianjiedizhi;
    @BindView(R.id.laiyuandizhi)
    EditText laiyuandizhi;
    @BindView(R.id.beizhu)
    EditText beizhu;


    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected AddWritingPresent createPresenter() {
        return new AddWritingPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_add_writing);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
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


    @OnClick({R.id.cancel, R.id.submit})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.submit:

                if (judeAll()) {
                    hideLoadingDialog();
                    createPresenter().addArticle(biaoti.getText().toString(),
                            lianjiedizhi.getText().toString(),
                            laiyuandizhi.getText().toString(),
                            beizhu.getText().toString()
                    );

                }
                break;
        }
    }

    private boolean judeAll() {

        if (TextUtils.isEmpty(biaoti.getText().toString())) {

            showToast("请输入标题");
            return false;
        }


        if (biaoti.getText().toString().length() > 50) {
            showToast("标题字数过长，限制在50以内");
            return false;
        }
        if (TextUtils.isEmpty(lianjiedizhi.getText().toString())) {

            showToast("请输入链接地址");
            return false;
        }

        if (TextUtils.isEmpty(laiyuandizhi.getText().toString())) {

            showToast("请输入来源网址");
            return false;
        }
        if (!TextUtils.isEmpty(beizhu.getText().toString())) {

            if (beizhu.getText().toString().length() > 100) {
                showToast("备注字数过长，限制在100以内");
                return false;
            }
        }
        return true;
    }

    @Override
    protected String gogTitle() {
        return null;
    }

    @Override
    public void addArticleSuccess(String data) {
        ToastUtils.showShort("" + data);
        Notice notice = new Notice();
        notice.type = ConstanceValue.ONARTICLEDELETE;
        RxBus.getDefault().post(notice);
        finish();
    }
}
