package cn.gog.conmentmanage.presenter;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IAddWritingView;
import cn.gog.conmentmanage.view.ISafeView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/19.
 */

public class AddWritingPresent extends BasePresenter<IAddWritingView> {
    public AddWritingPresent(IAddWritingView mvpView) {
        super(mvpView);
    }
    public void addArticle(String article_title,String article_url,String site_name,String description){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("article_title", article_title);
        param.put("article_url", article_url);
        param.put("site_name", site_name);
        param.put("description", description);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.addArticle(body), new SubscriberCallBack<String>("addArticle") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.addArticleSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }
}
