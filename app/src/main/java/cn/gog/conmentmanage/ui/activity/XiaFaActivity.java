package cn.gog.conmentmanage.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.base.activity.BaseMvpActivity;
import cn.gog.conmentmanage.model.GroupEntity;
import cn.gog.conmentmanage.model.ItemLevel1;
import cn.gog.conmentmanage.model.ItemLevel2;
import cn.gog.conmentmanage.model.ItemLevel3;
import cn.gog.conmentmanage.model.WangXinBan;
import cn.gog.conmentmanage.presenter.XiaFaPresent;
import cn.gog.conmentmanage.ui.adapter.ExpandableItemAdapter;
import cn.gog.conmentmanage.ui.adapter.GroupAdatper;
import cn.gog.conmentmanage.view.IXiaFaView;
import common.utils.StatusBarCompat;

//任务下发评论员
public class XiaFaActivity extends BaseMvpActivity<XiaFaPresent> implements IXiaFaView ,ExpandableItemAdapter.OnItemCheckedListenner {


    @BindView(R.id.rv)
    RecyclerView mRecyclerView;


    ExpandableItemAdapter adapter;
    ArrayList<MultiItemEntity> list;

    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;

    List<GroupEntity> groupEntities;

    private String taskid;

    @Override
    protected XiaFaPresent createPresenter() {
        return new XiaFaPresent(this);
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_xia_fa);
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void bindViews() {
        
        
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskid = getIntent().getStringExtra("taskId");
        
        list = new ArrayList<>();
        adapter = new ExpandableItemAdapter(list,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.expandAll();

        groupEntities = new ArrayList<>();



    }

    private ArrayList<MultiItemEntity> generateData() {

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            WangXinBan wangxin = new WangXinBan();
            for (int j = 0; j <3; j++) {
                ItemLevel1 lv1 = new ItemLevel1();
                for (int k = 0; k < 3; k++) {
                    ItemLevel2 lv2 = new ItemLevel2();

                    for(int x = 0;x<3;x++){
                        ItemLevel3 lv3 = new ItemLevel3();
                        lv2.addSubItem(lv3);
                    }
                    lv1.addSubItem(lv2);
                }
                wangxin.addSubItem(lv1);
            }
            res.add(wangxin);
        }

        return res;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);    //给Toolbar设置paddingTop
        }

        showLoadingDialog();
        createPresenter().groupFindByOrgid();
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
    public void onDataSuccess(Object data) {

    }

    @Override
    public void onCompleted() {

        hideLoadingDialog();

    }


    @Override
    public void groupFindByOrgidSuccess(List<WangXinBan> wangXinBans) {

        for(int i= 0;i<wangXinBans.size();i++){

            if(wangXinBans.get(i).getChildren() != null){

            for(int j = 0;j<wangXinBans.get(i).getChildren().size();j++){

                if(wangXinBans.get(i).getChildren().get(j).getChildren()!=null){
                    for(int k =0;k<wangXinBans.get(i).getChildren().get(j).getChildren().size();k++){

                        if(wangXinBans.get(i).getChildren().get(j).getChildren().get(k).getChildren()!=null){
                            for(int x =0;x<wangXinBans.get(i).getChildren().get(j).getChildren().get(k).getChildren().size();x++){
                                wangXinBans.get(i).getChildren().get(j).getChildren().get(k).addSubItem(wangXinBans.get(i).getChildren().get(j).getChildren().get(k).getChildren().get(x));
                            }
                        }

                        wangXinBans.get(i).getChildren().get(j).addSubItem(wangXinBans.get(i).getChildren().get(j).getChildren().get(k));
                    }
                }

                wangXinBans.get(i).addSubItem(wangXinBans.get(i).getChildren().get(j));
            }
            }
        }

        list.clear();
        list.addAll(wangXinBans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void assignToGroupSuccess(String jsonStr) {
        showToast(jsonStr);
        hideLoadingDialog();
        finish();
    }

    @Override
    public void itemChecked(String id, String label, boolean isChosen) {

        if(isChosen){ //添加

           int index = -1;
            for(int i =0;i<groupEntities.size();i++){
                if(TextUtils.equals(groupEntities.get(i).getId().toString(),id)){
                    index = i;
                }
            }
            if( !(index > -1)){
                GroupEntity entity = new GroupEntity();
                entity.setId(id);
                entity.setLabel(label);
                groupEntities.add(entity);
            }

        }else { //删除
            int index = -1;
            for(int i =0;i<groupEntities.size();i++){
                if(TextUtils.equals(groupEntities.get(i).getId().toString(),id)){
                    index = i;
                }
            }
            if(index > -1){
                groupEntities.remove(index);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xiafa, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_btn:
                if(groupEntities.size() ==0){
                    showToast("请选择下发组");
                }else{
                    List<String> groupIds = new ArrayList<>();
                    for(GroupEntity bean : groupEntities){
                        groupIds.add(bean.getId());
                    }
                    Gson gson = new Gson();
                     String   groupIdStr = gson.toJson(groupIds);
                     createPresenter().assignToGroup(taskid,groupIdStr);

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
