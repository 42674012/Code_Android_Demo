package common.http;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/22 on 10:30
 * desc:
 */

public class JsonRequestBody extends RequestBody {
    @Override
    public MediaType contentType() {
        return MediaType.parse("application/json; charset=utf-8");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }

    public static RequestBody createJsonBody(Map<String,Object> param){
        JSONObject jsonObject=new JSONObject(param);
        String jsonParam=jsonObject.toJSONString();
       return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam);
    }
}
