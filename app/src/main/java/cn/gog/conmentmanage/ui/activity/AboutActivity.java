package cn.gog.conmentmanage.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.presenter.AboutPresent;
import cn.gog.conmentmanage.view.IAboutView;

public class AboutActivity extends BaseMvpActivity<AboutPresent> implements IAboutView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;



    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    protected AboutPresent createPresenter() {
        return null;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_about);
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

    public void checkNew(View view) {
        showToast("当前已是最新版本");
    }
}
