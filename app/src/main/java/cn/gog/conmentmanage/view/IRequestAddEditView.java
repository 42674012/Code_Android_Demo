package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.TaskTypeEntity;
import cn.gog.conmentmanage.model.UserInfo;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/20.
 */

public interface IRequestAddEditView extends IBaseMvpView<TaskTypeEntity> {
    void getTaskTypeSuccess(List<TaskTypeEntity> data);
}
