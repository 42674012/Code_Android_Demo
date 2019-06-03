package cn.gog.conmentmanage.ui.view.popupwindow;

import android.content.Context;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/12 on 15:58
 * desc:
 */

public class ScreenUtils {

    private ScreenUtils() {
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
