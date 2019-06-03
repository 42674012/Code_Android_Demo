package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.view.IWritingListView;
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

public class WritingListPresent extends BasePresenter<IWritingListView>{
    public WritingListPresent(IWritingListView mvpView) {
        super(mvpView);
    }
    public void articlePage(int  pageNo,int  pageSize,String keywords ){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo);
        param.put("pageSize", pageSize);
        param.put("searchWord", keywords);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.articlePage(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                ArticlePage articlePage = new Gson().fromJson(data, ArticlePage.class);
                mvpView.articlePageSuccess(articlePage);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }

    public void batchAgree(String articleids ){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("articleids", articleids);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.batchAgree(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.batchAgreeSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }
    public void batchDisagree(String articleids ){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("articleids", articleids);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.batchDisagree(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.batchDisagreeSuccess(data);

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
