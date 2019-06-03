package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.TaskPage;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/18.
 *
 */

public interface IDutyView extends IBaseMvpView {
    void TaskPageSuccess(TaskPage data);

    void deleteMsgSuccess(String data);
}
