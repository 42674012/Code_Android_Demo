package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IDutyDetailView;
import cn.gog.conmentmanage.view.IDutyListView;
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

public class DutyListPresent extends BasePresenter<IDutyListView> {

    public DutyListPresent(IDutyListView mvpView) {
        super(mvpView);
    }
    public void getTastList(int pageNo,int pageSize){

        UserInfo userInfo =   UserCache.get();
        if(userInfo == null){
            return;
        }
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo+"");
        param.put("pageSize", pageSize+"");
        param.put("searchWord", "");

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.taskPage(body), new SubscriberCallBack<String>("getTastList") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                DutyPage dutyPage = new Gson().fromJson(data,DutyPage.class);

                mvpView.getTastListSuccess(dutyPage);

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
