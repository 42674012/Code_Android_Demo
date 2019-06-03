package cn.gog.conmentmanage.presenter;


import java.io.File;
import cn.gog.conmentmanage.api.ApiService;
import cn.gog.conmentmanage.api.ClientFactory;
import cn.gog.conmentmanage.view.IPersonView;
import common.presenter.BasePresenter;
import common.utils.ToastUtils;
import common.vo.SubscriberCallBack;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/4/18.
 *
 */

public class PersonPresent extends BasePresenter<IPersonView> {
    public PersonPresent(IPersonView mvpView) {
        super(mvpView);
    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void upLoadHeadimage(String image, File avatarFile) {
        ApiService apiService = ClientFactory.getApiService();
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
//        multipartBodyBuilder.addFormDataPart("uploadImg", image);

        if (null != avatarFile) {
            multipartBodyBuilder.addFormDataPart("uploadImg", avatarFile.getName(), RequestBody.create(MEDIA_TYPE_PNG, avatarFile));
        }

        //构建请求体
        RequestBody requestBody = multipartBodyBuilder.build();

        addSubscription(apiService.uploadImg(requestBody ), new SubscriberCallBack<String>("getNewestMsfList") {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            protected void onSuccess(String data) {
                  mvpView.modifyOk(data);
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
