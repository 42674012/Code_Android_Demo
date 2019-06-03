package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.ArticleDetail;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.ISafeView;
import cn.gog.conmentmanage.view.IWritingView;
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

public class WritingDetailPresent  extends BasePresenter<IWritingView> {
    public WritingDetailPresent(IWritingView mvpView) {
        super(mvpView);
    }

    public void articleFindByID(String articleid) {

            ApiService apiService = ClientFactory.getApiService();

            Map<String, Object> param = new HashMap<>();


            param.put("articleid", articleid);

            RequestBody body = JsonRequestBody.createJsonBody(param);

            addSubscription(apiService.articleFindByID(body), new SubscriberCallBack<String>("login") {


                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                protected void onSuccess(String data) {

                    ArticleDetail articleDetail = new Gson().fromJson(data,ArticleDetail.class);
                    mvpView.ArticleDetailSuccess(articleDetail);

                }

                @Override
                protected void onError(String msg) {
                    super.onError(msg.toString());
                    mvpView.onCompleted();
                    ToastUtils.showShort(msg.toString());
                }
            });

    }

    public void deleteByID(String articleid) {

        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();


        param.put("articleid", articleid);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.deleteByID(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.deleteSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });

    }

    public void caina(String articleid) {
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        List<String> ids = new ArrayList<>();
        ids.add(articleid);

        param.put("articleids", new Gson().toJson(ids));

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

    public void nocaina(String articleid) {
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();
        List<String> ids = new ArrayList<>();
        ids.add(articleid);

        param.put("articleids", new Gson().toJson(ids));
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
