package cn.gog.conmentmanage.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.model.SearchEntity;
import cn.gog.conmentmanage.model.UserInfo;
import cn.gog.conmentmanage.view.ISearchView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.Page;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/3 on 20:38
 * desc:
 */

public class SearchPresenter extends BasePresenter<ISearchView> {

    public SearchPresenter(ISearchView mvpView) {
        super(mvpView);
    }

    public void searhByKey(String searchWord) {

        ApiService apiService = ClientFactory.getApiService();

        Map<String, Object> param = new HashMap<>();

        param.put("searchWord", searchWord);
        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(apiService.searhByKey(body), new SubscriberCallBack<String>("login") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                List<SearchEntity> searchEntities = new Gson().fromJson(data,new TypeToken<List<SearchEntity>>(){}.getType());
                 mvpView.searchSuccess(searchEntities);

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
