package cn.gog.conmentmanage.ui.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gog.conmentmanage.R;

/**
 * 类描述：消息适配器
 * 公司：多彩贵州网
 * 创建人： 顾进
 * 创建时间：2017/9/27 上午11:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class StringAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    protected Activity context;


    public StringAdapter(Activity context, List<String> data) {
        super(R.layout.item_string_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String entity) {
        baseViewHolder.addOnClickListener(R.id.root);

        baseViewHolder.setText(R.id.string_tv,entity);

    }
}
