package common.vo;


import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.ui.activity.MainActivity;
import cn.gog.conmentmanage.utils.gesture.util.cache.ACache;
import common.model.Notice;
import common.utils.ConstanceValue;
import common.utils.RxBus;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public abstract class SubscriberCallBack<T> extends BaseCallBack<ResultResponse<T>> {
    public SubscriberCallBack(String tag) {
        super(tag);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(ResultResponse response) {

        if (response.getCode() == 1) {
            onSuccess((T) response.getResult());
            onComplete();
        } else {
            if (response.getCode() == 201 || response.getCode() == 207) {

                ACache aCache = ACache.get(GogApplication.getAppContext());
                aCache.clear();

                UserCache.putGesSwitch(false);

                Notice notice = new Notice();
                notice.type = ConstanceValue.RELOGIN;
                RxBus.getDefault().post(notice);
            }

            onFailure(response);
        }
    }

    protected abstract void onSuccess(T response);
}
