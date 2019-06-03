package cn.gog.conmentmanage.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.gog.conmentmanage.R;
import cn.gog.conmentmanage.model.TaskTypeEntity;


/**
 * Created by lenovo on 2017/12/5.
 * 选择发言人的适配器
 */

public class MySpinnerAdapter extends BaseAdapter {

    private List<TaskTypeEntity> mList;
    private Context mContext;

    public MySpinnerAdapter(Context pContext, List<TaskTypeEntity> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }
    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater _LayoutInflater= LayoutInflater.from(mContext);

        if(view ==null)
        {
            view =_LayoutInflater.inflate(R.layout.item_spokesman_spinner, null);
        }
        TextView _TextView1=(TextView)view.findViewById(R.id.name_tv);
        _TextView1.setText(mList.get(position).getType_name());

        return view;
    }
}
