package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.UserInfo;
import common.utils.DateUtils;

/**
 * 类描述：消息适配器
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DutyRequestAdatper extends BaseQuickAdapter<DutyDetailModel.TaskDetailsVoListBean,BaseViewHolder> {
    protected Activity context;
    private boolean isEdit  = false;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdit() {
        return isEdit;
    }
    boolean  showDelete =false;
    public DutyRequestAdatper(Activity context, List<DutyDetailModel.TaskDetailsVoListBean> data) {
        super(R.layout.item_duty_reuest_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DutyDetailModel.TaskDetailsVoListBean entity) {
        baseViewHolder.addOnClickListener(R.id.delete_btn);

        View ischosen = baseViewHolder.getView(R.id.ischosen);
        Button delete_btn = baseViewHolder.getView(R.id.delete_btn);

        if(showDelete){
            delete_btn.setVisibility(View.VISIBLE);
        }else {
            delete_btn.setVisibility(View.GONE);
        }
        if(isEdit){
            ischosen.setVisibility(View.VISIBLE);
        }else {
            ischosen.setVisibility(View.GONE);
        }

        baseViewHolder.setText(R.id.tv_org,entity.getSite_name()+"_"+entity.getArticle_title());
        baseViewHolder.setText(R.id.tv_net,entity.getSite_url());
        baseViewHolder.setText(R.id.tv_msg_title,entity.getDescription());
        baseViewHolder.setText(R.id.tv_state,entity.getType_name()+":"+entity.getTask_sum());


    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }
}
