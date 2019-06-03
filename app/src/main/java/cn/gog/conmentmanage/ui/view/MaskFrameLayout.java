package cn.gog.conmentmanage.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by lenovo on 2018/5/15.
 */

public class MaskFrameLayout extends RelativeLayout {
    private Context mContext;


    public MaskFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaskFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            checkChildView();
        }
    }

    private void checkChildView() {
        for (int i = 0; i < getChildCount(); i++) {


                layoutChild(i);

        }
    }

    private void layoutChild(int i) {


        TextView view = (TextView) getChildAt(i);
        view.setAlpha(0.2f);

        if ( i%2==0){//偶数 在前面

            Log.e("jin",i+"");
            view.setX(0-getWidth()/12);
            view.setY((i+1)/2 * getHeight()/5);
//            view.setPivotX(getWidth()/6);
//            view.setPivotY((i+1)/2 * getHeight()/5);
            view.setRotation(-30f);
        }else{

            Log.e("jin",i+"");
            view.setX(getWidth()/2 );
            view.setY((i-1)/2 * getHeight()/5+  getHeight()/10);
//            view.setPivotX(getWidth()/2);
//            view.setPivotY(i/2 * getHeight()/5 );
//
            view.setRotation(-30f);
        }
    }

    public boolean isOdd(int a){
        if((a&1) != 1){   //是偶数
            return true;
        }
        return false;
    }
}
