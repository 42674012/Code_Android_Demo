package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.model.ArticleMsg;
import cn.gog.conmentmanage.model.ArticlePage;
import cn.gog.conmentmanage.model.ContactPage;
import cn.gog.conmentmanage.model.DutyItemEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.ui.activity.AddDutyActivity;
import cn.gog.conmentmanage.view.IWaitWorkListView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * Created by gujin on 2018/4/18.
 *
 */

public class WaitWorkPresenter extends BasePresenter<IWaitWorkListView> {
    public WaitWorkPresenter(IWaitWorkListView mvpView) {
        super(mvpView);
    }

    public void articlePage(int  pageNo,int  pageSize ){
        UserInfo userInfo =   UserCache.get();
        if(userInfo == null){
            return;
        }

        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("pageNo", pageNo);
        param.put("pageSize", pageSize);

        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.articlePage(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                ArticlePage articlePage = new Gson().fromJson(data, ArticlePage.class);
                mvpView.articlePageSuccess(articlePage);

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
