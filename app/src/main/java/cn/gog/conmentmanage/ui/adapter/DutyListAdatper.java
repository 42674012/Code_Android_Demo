package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import cn.gog.conmentmanage.R;
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
public class DutyListAdatper extends BaseQuickAdapter<DutyItemEntity,BaseViewHolder> {
    protected Activity context;


    public DutyListAdatper(Activity context, List<DutyItemEntity> data) {
        super(R.layout.item_duty_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DutyItemEntity entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        baseViewHolder.setText(R.id.tv_msg_title,entity.getTask_name());

        switch (entity.getTask_state()){
            case 0:
 
                baseViewHolder.setText(R.id.state_tv,"未发布");
                break;
            case 1:
                baseViewHolder.setText(R.id.state_tv,"已发布");
                break;
            case 2:

                baseViewHolder.setText(R.id.state_tv,"已完结");
                break;
            case 3:
                baseViewHolder.setText(R.id.state_tv,"已取消");
                break;
        }
        baseViewHolder.setText(R.id.tv_name,entity.getCreater_manager_username());
        baseViewHolder.setText(R.id.tv_msg_time, DateUtils.toDateStr(new Date(entity.getCreatetime()))+"");

    }
}
