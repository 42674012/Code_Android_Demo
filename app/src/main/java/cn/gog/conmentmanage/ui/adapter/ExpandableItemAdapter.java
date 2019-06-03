package cn.gog.conmentmanage.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.ItemLevel1;
import cn.gog.conmentmanage.model.ItemLevel2;
import cn.gog.conmentmanage.model.ItemLevel3;
import cn.gog.conmentmanage.model.WangXinBan;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 =  2;
    public static final int TYPE_LEVEL_3 = 3;

    OnItemCheckedListenner listenner;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data, OnItemCheckedListenner listenner) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_lv2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_lv3);
        this.listenner = listenner;
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:

                final WangXinBan lv0 = (WangXinBan) item;
                holder.setText(R.id.title, lv0.getLabel())
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);

                AppCompatCheckBox radioButton = holder.getView(R.id.iv_head);
                radioButton.setChecked(lv0.isChosen());
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        lv0.setChosen(b);
                        listenner.itemChecked(lv0.getId(),lv0.getLabel(),b);
//                        notifyChildren1(b,lv0);
                    }
                });
                if (lv0.isGroupIs()){
                    radioButton.setVisibility(View.VISIBLE);
                }else {
                    radioButton.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });

                break;
            case TYPE_LEVEL_1:
                final ItemLevel1 lv1 = (ItemLevel1) item;
                holder.setText(R.id.title, lv1.getLabel())
                        .setImageResource(R.id.iv, lv1.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv1.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                AppCompatCheckBox radioButton1= holder.getView(R.id.iv_head);
                radioButton1.setChecked(lv1.isChosen());
                radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        lv1.setChosen(b);
                        listenner.itemChecked(lv1.getId(),lv1.getLabel(),b);
//                        notifyChildren2(b,lv1);
                    }
                });

                if (lv1.isGroupIs()){
                    radioButton1.setVisibility(View.VISIBLE);
                }else {
                    radioButton1.setVisibility(View.GONE);
                }

                break;
            case  TYPE_LEVEL_2:

                final ItemLevel2 lv2 = (ItemLevel2) item;
                holder.setText(R.id.title, lv2.getLabel())
                        .setImageResource(R.id.iv, lv2.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv2.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });

                AppCompatCheckBox radioButton2= holder.getView(R.id.iv_head);
                radioButton2.setChecked(lv2.isChosen());
                radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        lv2.setChosen(b);
                        listenner.itemChecked(lv2.getId(),lv2.getLabel(),b);
//                        notifyChildren3(b,lv2);
                    }
                });
                if (lv2.isGroupIs()){
                    radioButton2.setVisibility(View.VISIBLE);
                }else {
                    radioButton2.setVisibility(View.GONE);
                }

                break;
            case  TYPE_LEVEL_3:

                final ItemLevel3 lv3 = (ItemLevel3) item;
                holder.setText(R.id.title, lv3.getLabel());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
//                        remove(pos);
                    }
                });

                AppCompatCheckBox radioButton3= holder.getView(R.id.iv_head);
                radioButton3.setChecked(lv3.isChosen());
                radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        lv3.setChosen(b);
                        listenner.itemChecked(lv3.getId(),lv3.getLabel(),b);
//                        notify();
                    }
                });

                if (lv3.isGroupIs()){
                    radioButton3.setVisibility(View.VISIBLE);
                }else {
                    radioButton3.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void notifyChildren1(boolean b, WangXinBan lv0) {

        List<ItemLevel1>  l1List = lv0.getChildren();
        if(l1List != null){
            for(ItemLevel1 bean : l1List){
                bean.setChosen(b);
                notifyChildren2(b,bean);
            }
        }

        notifyDataSetChanged();
    }

    private void notifyChildren2(boolean b, ItemLevel1 itemLevel1) {

        List<ItemLevel2>  l1List = itemLevel1.getChildren();
        if(l1List != null){
            for(ItemLevel2 bean : l1List){
                bean.setChosen(b);
                notifyChildren3(b,bean);
            }
        }
        notifyDataSetChanged();
    }

    private void notifyChildren3(boolean b, ItemLevel2 itemLevel2) {
        List<ItemLevel3>  l1List = itemLevel2.getChildren();
        if(l1List != null){
            for(ItemLevel3 bean : l1List){
                bean.setChosen(b);
            }
        }
        notifyDataSetChanged();
    }

    public  interface OnItemCheckedListenner{
          void itemChecked(String id,String label,boolean isChosen);
    }
}


