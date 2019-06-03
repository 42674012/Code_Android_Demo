package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.OrgEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IAddDutyView;
import cn.gog.conmentmanage.view.IAddRequestOrgView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/21.
 */

public class AddRequestOrgPresent  extends BasePresenter<IAddRequestOrgView> {
    public AddRequestOrgPresent(IAddRequestOrgView mvpView) {
        super(mvpView);
    }

    public void childrenOrg( ){
        ApiService apiService = ClientFactory.getApiService();

        addSubscription(apiService.childrenOrg(), new SubscriberCallBack<String>("childrenOrg") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess( String data) {
                List<OrgEntity> orgEntities = new Gson().fromJson(data,new TypeToken<List<OrgEntity>>(){}.getType());
                mvpView.childrenOrgSuccess(orgEntities);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }
    public void assignToOrg(String taskid, String orgidList){
        ApiService apiService = ClientFactory.getApiService();
        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid+"");
        param.put("orgidList", orgidList+"");

        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(apiService.assignToOrg(body), new SubscriberCallBack<String>("childrenOrg") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.assignToOrgSuccess(data);

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
