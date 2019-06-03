package cn.gog.conmentmanage.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.DutyEntity;
import cn.gog.conmentmanage.model.TaskPage;
import common.utils.DateUtils;

/**
 * 类描述：应用 适配器
 * 创建人： gujin
 */
public class DutyAdatper extends BaseQuickAdapter<TaskPage.ListBean,BaseViewHolder> {


   private boolean isEdit  = false;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public DutyAdatper(Context context, @Nullable List<TaskPage.ListBean> data) {
        super(R.layout.item_duty_layout, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskPage.ListBean item) {

        helper.addOnClickListener(R.id.root);
        helper.addOnClickListener(R.id.ischosen);

        View ischosen = helper.getView(R.id.ischosen);

        if(isEdit){
            ischosen.setVisibility(View.VISIBLE);
        }else {
            ischosen.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_msg_title,item.getLabel());
        helper.setText(R.id.name,item.getInitiatorName());
        helper.setText(R.id.time_tv, DateUtils.toDateStr(new Date(item.getCreatetime())));
        helper.setBackgroundRes(R.id.img_msg_icon ,item.isChosen()?R.mipmap.checkbox_round_1:R.mipmap.chose);

    } 
}