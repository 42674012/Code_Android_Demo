package cn.gog.conmentmanage.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.WangXinBan;
import cn.gog.conmentmanage.view.IXiaFaView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/22.
 */

public class XiaFaPresent  extends BasePresenter<IXiaFaView> {
    public XiaFaPresent(IXiaFaView mvpView) {
        super(mvpView);
    }

    public void groupFindByOrgid(){

        ApiService apiService = ClientFactory.getApiService();

        addSubscription(apiService.groupFindByOrgid(), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String jsonStr) {
                mvpView.onCompleted();
                Gson gson = new Gson();
                List<WangXinBan> wangXinBans =    gson.fromJson(jsonStr,  new TypeToken<List<WangXinBan>>() { }.getType()) ;




             mvpView.groupFindByOrgidSuccess(wangXinBans);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }

    public void assignToGroup(String taskid,String groupidList){

        ApiService apiService = ClientFactory.getApiService();


        Map<String, Object> param = new HashMap<>();

        param.put("taskid", taskid);
        param.put("groupidList", groupidList);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.assignToGroup(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String jsonStr) {

                mvpView.assignToGroupSuccess(jsonStr);

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
