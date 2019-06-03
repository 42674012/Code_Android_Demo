package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.DutyItemEntity;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/18.
 */

public interface IWaitWorkListView extends IBaseMvpView {

    void articlePageSuccess(ArticlePage data);
}
