package cn.gog.conmentmanage.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.DutyEntity;
import cn.gog.conmentmanage.model.OrgEntity;

/**
 * 类描述：应用 适配器
 * 创建人： gujin
 */
public class OrgAdapter extends BaseQuickAdapter<OrgEntity,BaseViewHolder> {


    public OrgAdapter(Context context, @Nullable List<OrgEntity> data) {
        super(R.layout.item_org_list, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper,final OrgEntity item) {

        helper.addOnClickListener(R.id.root);
        helper.setText(R.id.string_tv,item.getOrg_name());
        AppCompatCheckBox iv_head  = helper.getView(R.id.iv_head);
        iv_head.setChecked(item.getChosen());
        iv_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setChosen(b);
            }
        });
    } 
}