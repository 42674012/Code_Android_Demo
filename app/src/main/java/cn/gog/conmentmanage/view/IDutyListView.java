package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.DutyPage;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/19.
 */

public interface IDutyListView extends IBaseMvpView {
    void getTastListSuccess(DutyPage data);
}
