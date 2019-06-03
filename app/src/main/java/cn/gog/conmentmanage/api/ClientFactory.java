package cn.gog.conmentmanage.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.gog.conmentmanage.GogApplication;
import cn.gog.conmentmanage.db.UserCache;
import cn.gog.conmentmanage.utils.SharedPreferencesUtils;
import common.utils.AppUtils;
import common.utils.ConstanceValue;
import common.utils.StringUtils;
import common.vo.LocationResp;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:Api 废掉
 */
public class ClientFactory {


    public static Retrofit mRetrofit;
    /**
     * 缓存大小
     */
    private static int CACHE_SIZE = 10 * 1024 * 1024;

    /**
     * @return
     */
    public static Retrofit retrofit() {


        if (mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(ConstanceValue.BASE_HOST);
            /**
             * 日志拦截器
             */
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.cache(new Cache(GogApplication.getAppContext().getCacheDir(), CACHE_SIZE))
                    .addInterceptor(logging).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            /**
                             *  添加通用头部
                             *  登录返回TOKEN后要刷新这里
                             */
                            Request.Builder builder = chain.request().newBuilder();
                            String deviceId = AppUtils.getDeviceId(GogApplication.getInstance());
                            String phonetype = android.os.Build.MODEL;

                            String osVersion = android.os.Build.VERSION.RELEASE;

                            builder.addHeader("Content-Type", "application/json");
                            builder.addHeader("Accept", "application/json");

                            if(UserCache.get()!= null){
                                builder.addHeader("commenters_token", UserCache.get().getCommenters_token());
                            }

                            return chain.proceed(builder.build());
                        }
                    });
            OkHttpClient httpClient = httpBuilder.build();
            builder.client(httpClient);
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }

    public static ApiService getApiService() {
        return retrofit().create(ApiService.class);
    }

}
