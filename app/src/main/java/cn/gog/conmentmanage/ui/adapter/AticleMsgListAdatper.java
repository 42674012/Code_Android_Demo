package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.ArticleMsg;
import cn.gog.conmentmanage.model.DutyItemEntity;
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
public class AticleMsgListAdatper extends BaseQuickAdapter<ArticleMsg.ListBean,BaseViewHolder> {
    protected Activity context;
    private boolean isEdit  = false;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public AticleMsgListAdatper(Activity context, List<ArticleMsg.ListBean> data) {
        super(R.layout.item_article_msg_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ArticleMsg.ListBean item) {
        baseViewHolder.addOnClickListener(R.id.root);
        baseViewHolder.addOnClickListener(R.id.ischosen);


        View ischosen = baseViewHolder.getView(R.id.ischosen);

        if(isEdit){
            ischosen.setVisibility(View.VISIBLE);
        }else {
            ischosen.setVisibility(View.GONE);
        }

        baseViewHolder.setText(R.id.tv_msg_title,item.getLabel());
        baseViewHolder.setText(R.id.name,item.getInitiatorName());
        baseViewHolder.setText(R.id.time_tv, DateUtils.toDateStr(new Date(item.getCreatetime())));
        baseViewHolder.setBackgroundRes(R.id.img_msg_icon ,item.isChosen()?R.mipmap.checkbox_round_1:R.mipmap.chose);
    }
}
