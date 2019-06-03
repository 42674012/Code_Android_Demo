package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.DutyPage;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/23.
 */

public interface ITaskListView  extends IBaseMvpView {
    void getTastListSuccess(DutyPage data);
}
