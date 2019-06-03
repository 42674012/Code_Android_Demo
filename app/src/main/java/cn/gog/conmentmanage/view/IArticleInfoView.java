package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.ArticleMsg;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/23.
 */

public interface IArticleInfoView extends IBaseMvpView {

    void pageArticleSuccess(ArticleMsg data);

    void deleteMsgSuccess(String data);
}
