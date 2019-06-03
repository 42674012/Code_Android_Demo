package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.UserInfo;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/18.
 */

public interface IContactView extends IBaseMvpView{


    void contactListSuccess(ContactPage data);
}
