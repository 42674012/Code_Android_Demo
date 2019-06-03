package common.tmp;


import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/30.
 */

public interface FavouriteApi {

    @POST("v2/recommend/star?")
    Observable<FavouriteResp> favourite(@Query("news") String newsId);
}
