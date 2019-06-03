package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.LiveTokenEntity;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IHomeView;
import cn.gog.conmentmanage.view.ILoginView;
import cn.jpush.android.api.JPushInterface;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/18.
 * 登录的present
 */

public class LoginPresent  extends BasePresenter<ILoginView> {
    public LoginPresent(ILoginView mvpView) {
        super(mvpView);
    }

    public void login(String account,String password){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("login_name", account);
        param.put("password", password);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.login(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                UserInfo userInfo = new Gson().fromJson(data,UserInfo.class);
                new HomePresenter(new IHomeView() {
                    @Override
                    public void getTokenOk(LiveTokenEntity data) {

                    }

                    @Override
                    public void getDecodeResultOk(String data) {

                    }

                    @Override
                    public void getNewestMsfListSuccess(List<MsgEntity> data) {

                    }

                    @Override
                    public void onDataSuccess(Object data) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                }).jiguangPush(JPushInterface.getRegistrationID(GogApplication.getAppContext()));
                mvpView.logoinSuccess(userInfo);

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
