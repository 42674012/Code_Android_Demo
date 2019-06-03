package common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.gog.conmentmanage.R;
import common.ContextHolder;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showShort(Context context, CharSequence text) {


        View view = LayoutInflater.from(context).inflate(R.layout.widget_toast, null);
        TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
        tv_toast.setText(text);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public static void showShort( CharSequence text) {

        View view = LayoutInflater.from(ContextHolder.getContext()).inflate(R.layout.widget_toast, null);
        TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
        tv_toast.setText(text);

        Toast toast = new Toast(ContextHolder.getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

}
