package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.DutyDetailModel;
import cn.gog.conmentmanage.view.IAddDutyView;
import cn.gog.conmentmanage.view.IEditDutyView;
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

public class EditDutyPresent extends BasePresenter<IEditDutyView>{
    public EditDutyPresent(IEditDutyView mvpView) {
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

    public void taskSave(String taskid,String task_name,
                        int task_urgency_degree,
                        int task_importance_degree,
                        boolean task_long_term,
                        Date deadline,
                        int feedback_type,
                         Date feedback_type_deadline,
                        int feedback_cycle,
                        String responseOrgList,
                        String taskDetailsVoList){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();


        param.put("taskid", taskid);
        param.put("task_name", task_name);
        param.put("task_urgency_degree", task_urgency_degree);
        param.put("task_importance_degree", task_importance_degree);
        param.put("task_long_term", task_long_term);
        if(deadline == null){
            param.put("deadline", "");
        }else{

            param.put("deadline", deadline.getTime());
        }
        param.put("feedback_type", feedback_type);
        if(feedback_type_deadline == null){
            param.put("feedback_type_deadline", "");
        }else{

            param.put("feedback_type_deadline", feedback_type_deadline.getTime());
        }
        param.put("feedback_cycle", feedback_cycle);
        param.put("responseOrgList", responseOrgList);
        param.put("taskDetailsVoList", taskDetailsVoList);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.task_update(body), new SubscriberCallBack<String>("taskAdd") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.taskUpdateSuccess(data);

            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mvpView.onCompleted();
                ToastUtils.showShort(msg);
            }
        });
    }

    public void taskSaveAndPublish(String taskid,String task_name,
                         int task_urgency_degree,
                         int task_importance_degree,
                         boolean task_long_term,
                         Date deadline,
                         int feedback_type,
                         Date feedback_type_deadline,
                         int feedback_cycle,
                         String responseOrgList,
                         String taskDetailsVoList){
        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();


        param.put("taskid", taskid);
        param.put("task_name", task_name);
        param.put("task_urgency_degree", task_urgency_degree);
        param.put("task_importance_degree", task_importance_degree);
        param.put("task_long_term", task_long_term);
        if(deadline == null){
            param.put("deadline", "");
        }else{

            param.put("deadline", deadline.getTime());
        }
        param.put("feedback_type", feedback_type);
        if(feedback_type_deadline == null){
            param.put("feedback_type_deadline", "");
        }else{

            param.put("feedback_type_deadline", feedback_type_deadline.getTime());
        }
        param.put("feedback_cycle", feedback_cycle);
        param.put("responseOrgList", responseOrgList);
        param.put("taskDetailsVoList", taskDetailsVoList);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.task_updateAndPublish(body), new SubscriberCallBack<String>("taskAdd") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {

                mvpView.taskUpdateSuccess(data);

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
