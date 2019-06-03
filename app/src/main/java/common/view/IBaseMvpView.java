package common.view;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/27 on 9:52
 * desc:
 */

public interface IBaseMvpView<T> {
    /**
     * 请求成功
     *
     * @param data
     */
    void onDataSuccess(T data);

    /**
     * 请求失败
     */
    void onCompleted();
}
