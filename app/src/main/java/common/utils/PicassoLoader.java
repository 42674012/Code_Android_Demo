package common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import cn.gog.conmentmanage.R;


/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/5 on 11:01
 * desc:Picasso
 */

public class PicassoLoader {

    public static void displayImage(Context context, String uri, ImageView view) {
        if (StringUtils.isEmpty(uri)) {
            return;
        }
        Picasso.with(context).load(uri).config(Bitmap.Config.RGB_565).placeholder(R.mipmap.persion_icon).into(view);

    }

}
