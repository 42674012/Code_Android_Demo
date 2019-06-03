package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.ArticleMsg;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.view.IArticleInfoView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/23.
 */

public class ArticleInfoPresent  extends BasePresenter<IArticleInfoView>{
    public ArticleInfoPresent(IArticleInfoView mvpView) {
        super(mvpView);
    }
    public void pageArticleMsg(int pageNo,int pageSize){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo+"");
        param.put("pageSize", pageSize+"");
        param.put("searchWord", "");

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.pageArticleMsg(body), new SubscriberCallBack<String>("getTastList") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                ArticleMsg articleMsg = new Gson().fromJson(data,ArticleMsg.class);
                mvpView.pageArticleSuccess(articleMsg);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }
    public void deletaMsgs(String targetids) {

        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("targetids", targetids+"");


        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.deletaMsgs(body), new SubscriberCallBack<String>("getTastList") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.deleteMsgSuccess(data);

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
