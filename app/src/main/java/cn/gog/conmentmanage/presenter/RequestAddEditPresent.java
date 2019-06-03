package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.TaskTypeEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.IRequestAddEditView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/20.
 */

public class RequestAddEditPresent extends BasePresenter<IRequestAddEditView> {
    public RequestAddEditPresent(IRequestAddEditView mvpView) {
        super(mvpView);
    }
    public void getTaskType(){
        ApiService apiService = ClientFactory.getApiService();

        addSubscription(apiService.getTaskType(), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                List<TaskTypeEntity> taskTypeEntities = new Gson().fromJson(data,new TypeToken< List<TaskTypeEntity>>(){}.getType());
                mvpView.getTaskTypeSuccess(taskTypeEntities);

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
