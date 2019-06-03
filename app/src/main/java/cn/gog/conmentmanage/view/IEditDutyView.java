package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.DutyDetailModel;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/23.
 */

public interface IEditDutyView  extends IBaseMvpView{
    void taskByIdSuccess(DutyDetailModel data);

    void taskUpdateSuccess(String data);
}
