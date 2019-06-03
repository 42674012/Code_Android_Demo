package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.WritingItemEntity;
import common.utils.DateUtils;

/**
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WritingListAdatper extends BaseQuickAdapter<WritingItemEntity,BaseViewHolder> {
    protected Activity context;

    boolean isEdit = false;

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public WritingListAdatper(Activity context, List<WritingItemEntity> data) {
        super(R.layout.item_writing_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder,final WritingItemEntity entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        baseViewHolder.setText(R.id.tv_msg_title,entity.getArticle_title());
        baseViewHolder.setText(R.id.tv_short,entity.getDescription());
        baseViewHolder.setText(R.id.state_tv,entity.getResponse_stateStr());

         baseViewHolder.setText(R.id.tv_msg_time, DateUtils.toDateStr(new Date(entity.getCreatetime()))+"");
        CheckBox check_box = baseViewHolder.getView(R.id.check_box);

        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                entity.setChosen(b);
            }
        });
         if(isEdit()){
             check_box.setVisibility(View.VISIBLE);
         }else {
             check_box.setVisibility(View.GONE);
         }
    }
}
