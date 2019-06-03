package common.presenter;
/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
