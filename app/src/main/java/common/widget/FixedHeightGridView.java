/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 固定高度的GridView(去掉GridView原有的滚动效果,全部展示所有的item内容)
 * <p>
 * 可用于ScrollView嵌套
 * <p>
 * Author: Gujin
 * Time: 16/8/29  15:44
 */
public class FixedHeightGridView extends GridView {

    public FixedHeightGridView(Context context) {
        super(context);
    }

    public FixedHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写onMeasure方法实现GridView全部内容完全显示
     *
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
