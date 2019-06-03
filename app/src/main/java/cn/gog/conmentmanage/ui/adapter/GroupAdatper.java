package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.GroupEntity;
import cn.gog.conmentmanage.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 类描述：下发组适配器
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GroupAdatper extends BaseQuickAdapter<GroupEntity,BaseViewHolder> {
    protected Activity context;


    public GroupAdatper(Activity context, List<GroupEntity> data) {
        super(R.layout.item_group_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GroupEntity entity) {
        baseViewHolder.addOnClickListener(R.id.root);


        baseViewHolder.setText(R.id.tv_msg_title,entity.getLabel());


    }
}
