package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import common.utils.PicassoLoader;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 类描述：消息适配器
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ContactAdatper extends BaseQuickAdapter<ContactPage.UserInfoBean,BaseViewHolder> {
    protected Activity context;


    public ContactAdatper(Activity context, List<ContactPage.UserInfoBean> data) {
        super(R.layout.item_contact_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ContactPage.UserInfoBean entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        CircleImageView imageView = baseViewHolder.getView(R.id.img_msg_icon);
        PicassoLoader.displayImage(mContext, entity.getHeadUrl(), imageView);

        baseViewHolder.setText(R.id.tv_msg_title,entity.getUsername());
        baseViewHolder.setText(R.id.tv_msg_time,entity.getOrg_name());

    }
}
