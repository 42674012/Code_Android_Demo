package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IContactView;
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

public class ContactPresent  extends BasePresenter<IContactView> {
    public ContactPresent(IContactView mvpView) {
        super(mvpView);
    }


    public void contactList(int  pageNo,int  pageSize ){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo);
        param.put("pageSize", pageSize);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.contactList(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                ContactPage contactPage = new Gson().fromJson(data,ContactPage.class);
                mvpView.contactListSuccess(contactPage);

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
