package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.MeEntity;
import cn.gog.conmentmanage.model.MsgEntity;

/**
 * 类描述：消息适配器
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MeAdatper extends BaseQuickAdapter<MeEntity,BaseViewHolder> {
    protected Activity context;


    public MeAdatper(Activity context, List<MeEntity> data) {
        super(R.layout.item_me_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MeEntity entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        baseViewHolder.setText(R.id.tv_msg_title,entity.getName());
        baseViewHolder.setBackgroundRes(R.id.img_msg_icon,entity.getResID());
    }
}
