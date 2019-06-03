package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.DutyPage;
import cn.gog.conmentmanage.view.ITaskListView;
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

public class TaskListPresent  extends BasePresenter<ITaskListView>{
    public TaskListPresent(ITaskListView mvpView) {
        super(mvpView);
    }
    public void getTastList(int pageNo,int pageSize,String keywords){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo+"");
        param.put("pageSize", pageSize+"");
        param.put("searchWord", keywords);

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
