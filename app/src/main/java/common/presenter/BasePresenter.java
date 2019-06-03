package common.presenter;


import common.model.Notice;
import common.utils.RxBus;
import common.view.IBaseMvpView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public class BasePresenter<V extends IBaseMvpView> implements Presenter<V> {
    public V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public BasePresenter(V mvpView) {
        attachView(mvpView);
    }


    @Override
    public void detachView() {

//        this.mvpView.onCompleted();
        this.mvpView = null;
    }


    public void addSubscription(Observable observable, Observer observer) {
        observable
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 发送消息
     */
    public void post(Notice msg) {
        RxBus.getDefault().post(msg);
    }

}
