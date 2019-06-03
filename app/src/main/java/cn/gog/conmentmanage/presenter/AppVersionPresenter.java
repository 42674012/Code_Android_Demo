//package cn.gog.conmentmanage.presenter;
//
//import com.orhanobut.logger.Logger;
//
//
//import cn.gog.conmentmanage.api.ClientFactory;
//import cn.gog.conmentmanage.api.NewsService;
//import cn.gog.conmentmanage.model.AppVersion;
//import cn.gog.conmentmanage.view.IAppUpdateView;
//import common.presenter.BasePresenter;
//import common.vo.SubscriberCallBack;
//
///**
// * author:gujin
// * mail:1002606871@qq.com
// * date:2017/5/21 on 10:57
// * desc:检查版本更新 Presenter
// */
//
//public class AppVersionPresenter extends BasePresenter<IAppUpdateView> {
//
//    public  static String tag ="AppVersionPresenter";
//    public AppVersionPresenter(IAppUpdateView mvpView) {
//        super(mvpView);
//    }
//
//    public void getLastVersion() {
//        NewsService apiService = ClientFactory.getNewsService();
//
//        addSubscription(apiService, new SubscriberCallBack<AppVersion>(tag+"getLastVersion") {
//            @Override
//            protected void onSuccess(AppVersion appVersion) {
//                mvpView.onDataSuccess(appVersion);
//            }
//
//            @Override
//            public void onComplete() {
//                super.onComplete();
//                mvpView.onCompleted();
//                Logger.e("请求完成");
//            }
//        });
//    }
//}
