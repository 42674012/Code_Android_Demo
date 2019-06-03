package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.ui.activity.ArticleInfoListActivity;
import cn.gog.conmentmanage.ui.activity.DutyNoticeListActivity;
import cn.gog.conmentmanage.ui.activity.SystemInfoActivity;
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
public class MsgAdatper extends BaseQuickAdapter<MsgEntity,BaseViewHolder> {
    protected Activity context;


    public MsgAdatper(Activity context, List<MsgEntity> data) {
        super(R.layout.item_msg_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MsgEntity entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        baseViewHolder.setText(R.id.tv_msg_title,entity.getType());
        baseViewHolder.setText(R.id.tv_msg,entity.getLabel());
        baseViewHolder.setText(R.id.tv_msg_time, DateUtils.toDateStr(new Date(entity.getCreatetime()))+"");
        if(TextUtils.equals(entity.getType() ,"任务通知")){
            baseViewHolder.setBackgroundRes(R.id.img_msg_icon,R.mipmap.task_info);
        }else if(TextUtils.equals(entity.getType() ,"文章列表消息")){
            baseViewHolder.setBackgroundRes(R.id.img_msg_icon,R.mipmap.article_info);
        }else if(TextUtils.equals(entity.getType() ,"系统消息")){
            baseViewHolder.setBackgroundRes(R.id.img_msg_icon,R.mipmap.system_info);
        }
    }
}
