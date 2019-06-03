package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.MsgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IHomeView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.AppUtils;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/12/8
 * desc:
 */
public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView mvpView) {
        super(mvpView);
    }


    public void getNewestMsfList( ){

        UserInfo userInfo =   UserCache.get();
        if(userInfo == null){
            return;
        }
        ApiService apiService = ClientFactory.getApiService();

        addSubscription(apiService.messageNewest( ), new SubscriberCallBack<String>("getNewestMsfList") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {


                List<MsgEntity> msgEntities=     new Gson().fromJson(data,new TypeToken<List<MsgEntity>>(){}.getType());
                mvpView.getNewestMsfListSuccess(msgEntities);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }



    public void jiguangPush(String  registration_id){


        UserInfo userInfo =   UserCache.get();
        if(userInfo == null){
          return;
        }
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("registration_id", registration_id+"");
        param.put("userid", UserCache.get().getUserid()+"");
        param.put("platform",  "android");

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.MESSAGEJPUSH( body), new SubscriberCallBack<String>("jpush") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);

            }
        });
    }
}
