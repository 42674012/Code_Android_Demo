package common.vo;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */

public abstract class HtmlSubscriberCallBack extends BaseCallBack<String> {

    public HtmlSubscriberCallBack(String tag) {
        super(tag);
    }

    @Override
    public void onNext(String response) {
        onSuccess(response);
    }

    protected abstract void onSuccess(String response);
}
