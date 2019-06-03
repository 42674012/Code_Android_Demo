package cn.gog.conmentmanage.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gog.conmentmanage.view.IWorkView;
import common.http.JsonRequestBody;
import common.presenter.BasePresenter;
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
public class WorkPresenter extends BasePresenter<IWorkView> {
    IWorkView mvpView;
    public WorkPresenter(IWorkView mvpView) {
        super(mvpView);
        this.mvpView =mvpView;
    }

}
