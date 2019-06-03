package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.DutyDetailModel;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/19.
 */

public interface IDutyDetailView extends IBaseMvpView {
    void taskByIdSuccess(DutyDetailModel data);

    void cancelSuccess(String data);

    void finishSuccess(String data);

    void publishSuccess(String data);
}
