package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.UserInfo;
import common.view.IBaseMvpView;

/**
 * Created by gujin on 2018/4/18.
 * 登录的view
 */

public interface ILoginView extends IBaseMvpView<UserInfo> {

    void logoinSuccess(UserInfo data);
}
