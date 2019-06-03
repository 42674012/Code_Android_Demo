package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.SearchEntity;
import cn.gog.conmentmanage.model.UserInfo;
import common.utils.DateUtils;
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
public class SearchAdatper extends BaseQuickAdapter<SearchEntity.MsgBean,BaseViewHolder> {
    protected Activity context;


    public SearchAdatper(Activity context, List<SearchEntity.MsgBean> data) {
        super(R.layout.item_search_layout, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchEntity.MsgBean entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        if(entity.getLabel().contains("_1")){
            baseViewHolder.setText(R.id.tv_msg_title,entity.getLabel().split("_")[0]);
            LinearLayout llBottom =baseViewHolder.getView(R.id.llBottom);
            llBottom.setVisibility(View.GONE);
        }else{
            baseViewHolder.setText(R.id.tv_msg_title,entity.getLabel());
            baseViewHolder.setText(R.id.name,entity.getInitiatorName());
            baseViewHolder.setText(R.id.time_tv, DateUtils.toDateStr(new Date(entity.getCreatetime())));
        }
    }
}
