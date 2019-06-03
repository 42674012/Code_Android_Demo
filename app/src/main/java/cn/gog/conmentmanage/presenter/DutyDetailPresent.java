package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IContactView;
import cn.gog.conmentmanage.view.IDutyDetailView;
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

public class DutyDetailPresent  extends BasePresenter<IDutyDetailView> {

    public DutyDetailPresent(IDutyDetailView mvpView) {
        super(mvpView);
    }

    public void taskById(String taskid){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.taskById(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                DutyDetailModel model = new Gson().fromJson(data,DutyDetailModel.class);
                mvpView.taskByIdSuccess(model);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }


    public void taskCancel(String taskid,String userid){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid);
        param.put("userid", userid);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.taskCancel(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.cancelSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }
    public void taskFinish(String taskid,String userid){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid);
        param.put("userid", userid);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.taskFinish(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.finishSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }   public void taskPublish(String taskid,String userid){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid);
        param.put("userid", userid);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.taskPublish(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.publishSuccess(data);

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
