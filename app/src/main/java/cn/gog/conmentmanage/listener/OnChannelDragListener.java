package cn.gog.conmentmanage.listener;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */

public interface OnChannelDragListener {
    void onStarDrag(BaseViewHolder baseViewHolder);
    void onItemMove(int starPos, int endPos);
}
