package cn.gog.conmentmanage.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.presenter.SafePresent;
import cn.gog.conmentmanage.view.ISafeView;

public class PwModifyActivity extends BaseMvpActivity<SafePresent> implements ISafeView {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.next_l)
    LinearLayout next_l;

    @BindView(R.id.confime_modify)
    LinearLayout confime_modify;

    @BindView(R.id.new_pw)
    EditText new_pw;
    @BindView(R.id.new_pw_rp)
    EditText new_pw_rp;
  @BindView(R.id.ed_phone)
    EditText ed_phone;


    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.divide_v)
      View divide_v;
    @Override
    protected SafePresent createPresenter() {
        return new SafePresent(this);
    }

    @Override
    protected void loadViewLayout() {

        setContentView(R.layout.activity_pw_modify);
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

        ed_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               if(TextUtils.isEmpty(charSequence.toString())){
                   btn_next.setEnabled(false);
                   divide_v.setBackgroundColor(getResources().getColor(R.color.divider));
               }else {
                   btn_next.setEnabled(true);
                   divide_v.setBackgroundColor(Color.parseColor("#5b8cff"));
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

    @OnClick({R.id.btn_next, R.id.btn_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if(TextUtils.isEmpty(ed_phone.getText().toString())){
                    showToast("请输入旧密码");
                }else{
                    next_l.setVisibility(View.GONE);
                    confime_modify.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btn_confirm:

                if (judgeAll()) {
                      createPresenter().updatePwd(ed_phone.getText().toString(),new_pw.getText().toString());
                }
                break;
        }
    }

    private boolean judgeAll() {
        if (TextUtils.isEmpty(new_pw.getText().toString())) {

            showToast("请输入新密码");
            return false;
        }
        if (TextUtils.isEmpty(new_pw_rp.getText().toString())) {

            showToast("确认新密码不能为空");
            return false;
        }

        if (!TextUtils.equals(new_pw.getText().toString(), new_pw_rp.getText().toString())) {
            showToast("两次新密码输入不一致");
            return false;
        }

        return true;
    }

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void updateSuccess(String data) {
        showToast(data);
        UserCache.clear();
        startActivity(new Intent(PwModifyActivity.this,LoginActivity.class));
        finish();
    }
}
