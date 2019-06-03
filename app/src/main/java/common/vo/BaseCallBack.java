package common.vo;

import android.os.Handler;
import android.os.Looper;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

//import cn.gog.conmentmanage.db.UserManager;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.LogUtil;
import common.utils.RxBus;
import common.utils.ToastUtils;
import io.reactivex.Observer;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public abstract class BaseCallBack<T> implements Observer<T> {

    private Handler mDelivery;

    String tag;
    public BaseCallBack(String tag) {
        mDelivery = new Handler(Looper.getMainLooper());
        this.tag = tag;
    }

    @Override
    public void onComplete() {
        mDelivery = null;
    }

    @Override
    public void onError(final Throwable e) {

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (e instanceof SocketTimeoutException) {

                    //后期对网络变化做相关
//                    ToastUtils.showShort("网络连接超时");
                } else if (e instanceof SocketException) {
                    if (e instanceof ConnectException) {
//                        ToastUtils.showShort("网络未连接");
                    } else {
//                        ToastUtils.showShort("网络错误");
                    }
                }
            }
        });
        onError(e.getMessage());
    }


    protected void onError(String msg) {
       // ToastUtils.showShort(msg);
        onComplete();
    }

    protected void onFailure(ResultResponse response) {

        onError(response.getResult().toString());
        onComplete();
    }
}
