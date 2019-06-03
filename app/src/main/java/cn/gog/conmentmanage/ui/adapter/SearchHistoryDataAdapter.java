package cn.gog.conmentmanage.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cn.gog.conmentmanage.R;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/13
 * desc:
 */
public class SearchHistoryDataAdapter extends BaseAdapter {

    private Context context;
    private List<String> list = new ArrayList<String>();

    public SearchHistoryDataAdapter(Context context, List<String> strs) {

        this.context =context;

        list= strs;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.search_olddata_item, null);

            holder.tv = (TextView) view.findViewById(R.id.text);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.tv.setText(list.get(i));

        return view;
    }

    public class ViewHolder{
        TextView tv;
    }


}
