package cn.gog.conmentmanage.ui.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.ArticleDetail;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.presenter.WritingDetailPresent;
import cn.gog.conmentmanage.ui.view.MaskFrameLayout;
import cn.gog.conmentmanage.view.IWritingView;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.DateUtils;
import common.utils.RxBus;
import common.utils.StatusBarCompat;
import common.utils.ToastUtils;

public class WritingDetailActivity extends BaseMvpActivity<WritingDetailPresent> implements IWritingView {

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    String articleid;

    @BindView(R.id.xuhao)
    TextView xuhao;
    @BindView(R.id.biaoti)
    TextView biaoti;
    @BindView(R.id.lianjiedizhi)
    TextView lianjiedizhi;
    @BindView(R.id.laiyuandizhi)
    TextView laiyuandizhi;
    @BindView(R.id.beizhu)
    TextView beizhu;
    @BindView(R.id.shangbao)
    TextView shangbao;
    @BindView(R.id.shangbaotime)
    TextView shangbaotime;
    @BindView(R.id.shifoucaina)
    TextView shifoucaina;

    @BindView(R.id.buttonPanel)
    LinearLayout buttonPanel;

    @BindView(R.id.xiangsishangbao)
     TextView  xiangsishangbao;


    @BindView(R.id.mask_view)
    MaskFrameLayout mask_view;

    @Override
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {
        hideLoadingDialog();
    }

    @Override
    protected WritingDetailPresent createPresenter() {
        if(mvpPresenter == null){
            mvpPresenter =new WritingDetailPresent(this) ;
        }
        return mvpPresenter;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_writing_detail);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }
        articleid = getIntent().getStringExtra("articleid");
        mvpPresenter.articleFindByID(articleid);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_duty, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_btn:

                AlertDialog.Builder builder = new AlertDialog.Builder(WritingDetailActivity.this);

                //    设置Title的内容
                builder.setTitle("");
                //    设置Content来显示一个信息
                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        showLoadingDialog();
                        createPresenter().deleteByID(articleid);
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                //
                builder.show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @BindView(R.id.root)
    NestedScrollView root;
    @Override
    public void ArticleDetailSuccess(ArticleDetail data) {

        if(data == null){
            root.setVisibility(View.GONE);
            ToastUtils.showShort("文章已删除");

            return;
        }
        hideLoadingDialog();
//          xuhao .setText(data.getArticleid());
        biaoti .setText(data.getArticle_title());
        lianjiedizhi .setText(data.getArticle_url());
        laiyuandizhi .setText(data.getSite_name());
        beizhu .setText(data.getDescription());
        shangbao .setText(data.getOrg_name());
        shangbaotime .setText(DateUtils.toDateStr(new Date(data.getCreatetime())));
        shifoucaina .setText(data.getResponse_stateStr());
        xiangsishangbao.setText(data.getSimilarityArticleSize()+"");
        if(data.getResponse_state() == 0){
              buttonPanel.setVisibility(View.VISIBLE);
        }else {
            buttonPanel.setVisibility(View.GONE);
        }

    }

    @Override
    public void deleteSuccess(String data) {
        ToastUtils.showShort(""+data);
        Notice notice=new Notice();
        notice.type= ConstanceValue.ONARTICLEDELETE;
        RxBus.getDefault().post(notice);
        finish();
    }

    @Override
    public void batchAgreeSuccess(String data) {
        ToastUtils.showShort(data);
        hideLoadingDialog();
        mvpPresenter.articleFindByID(articleid);
    }

    @Override
    public void batchDisagreeSuccess(String data) {
        ToastUtils.showShort(data);
        hideLoadingDialog();

        mvpPresenter.articleFindByID(articleid);
    }

    @OnClick({R.id.caina_btn,R.id.no_caina_btn})
    public void click(View view){

        switch (view.getId()){
            case  R.id.caina_btn:

                 showLoadingDialog();
                mvpPresenter.caina(articleid);
                break;
            case  R.id.no_caina_btn:
                showLoadingDialog();
                mvpPresenter.nocaina(articleid);
                break;   
        }
    }


}
