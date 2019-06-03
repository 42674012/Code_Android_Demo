package cn.gog.conmentmanage.ui.view;

/**
 * Created by gujin on 2017/8/2
 */

public interface ISegmentedControl {

    int getCount();

    SegmentedControlItem getItem(int position);

    String getName(int position);

}
