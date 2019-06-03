/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 固定高度的ListView(去掉ListView原有的滚动效果,全部展示所有的item内容)
 *
 * 可用于ScrollView嵌套
 *
 * Author: Gujin
 * Time: 16/8/29  15:44
 */
public class FixedHeightListView extends ListView {

    public FixedHeightListView(Context context) {
        super(context);
    }

    public FixedHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写onMeasure方法实现GridView全部内容完全显示
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
