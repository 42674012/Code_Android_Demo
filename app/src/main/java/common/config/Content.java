package common.config;

import android.os.Environment;

/**
 * 放置常量信息
 * @author jda
 *
 */
public class Content {

	//app主目录
    public static final String SDCARDHOME = Environment.getExternalStorageDirectory().getPath() + "/gzscip/";

    //wechat
    public static final String APP_ID_WECHAT = "wx5a6e026fc55fd2b2";
	//消息--key
	public final static String EVENT_KEY = "message_key";
	//消息--成功
	public final static String EVENT_SUCCESS = "success";
	//消息--失败
	public final static String EVENT_FAILURE = "failure";
	//消息--取消
	public final static String EVENT_CANCLE = "cancle";

}