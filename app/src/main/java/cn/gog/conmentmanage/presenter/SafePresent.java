package cn.gog.conmentmanage.presenter;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IPersonView;
import cn.gog.conmentmanage.view.ISafeView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/18.
 *
 */

public class SafePresent  extends BasePresenter<ISafeView> {
    public SafePresent(ISafeView mvpView) {
        super(mvpView);
    }

    public void updatePwd(String old, String newPwd) {

        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("oldPassword", old);
        param.put("newPassword", newPwd);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.updatePassword(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.updateSuccess(data);

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
