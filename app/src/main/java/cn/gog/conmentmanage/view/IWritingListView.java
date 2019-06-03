package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.ArticlePage;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/23.
 */

public interface IWritingListView extends IBaseMvpView {
    void articlePageSuccess(ArticlePage data);

    void batchAgreeSuccess(String data);

    void batchDisagreeSuccess(String data);
}
