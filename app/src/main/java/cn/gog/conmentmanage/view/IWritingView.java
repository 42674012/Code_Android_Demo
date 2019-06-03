package cn.gog.conmentmanage.view;

import cn.gog.conmentmanage.model.ArticleDetail;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/19.
 */

public interface IWritingView extends IBaseMvpView {
    void ArticleDetailSuccess(ArticleDetail data);

    void deleteSuccess(String data);

    void batchAgreeSuccess(String data);

    void batchDisagreeSuccess(String data);
}
