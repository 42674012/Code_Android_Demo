package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.SystemInfoPage;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/24.
 */

public interface ISystemInfoView  extends IBaseMvpView{
    void pageSystemMsgSuccess(SystemInfoPage data);

    void deleteMsgSuccess(String data);
}
