package cn.gog.conmentmanage.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

import common.utils.DateUtils;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/8 on 19:27
 * desc:音频播放 进度条
 */

public class TextSeekBar extends AppCompatSeekBar {

    /**
     * 文本的大小
     */
    private float mTitleTextSize = 9;
    /**
     * 文字的内容
     */
    private String mTitleText;
    /**
     * 文字距离进度条的距离
     */
    private float mTextY = 40;

    private int padding = 12;

    /**
     * 得到拖快的宽度
     */
    private int mThumbWidth;
    /**
     * 得到进度条的宽度
     */
    private int mSeekWidth;
    /**
     * 画文字画笔
     */
    private Paint mPaint;

    /**
     * 文本偏差值
     */
    private int mDeviationValue = 0;
    private int totalSeconds = 0;
    private int currentTime;

    private OnViewDrawLisener lisener;


    public void setOnViewDrawLisener(OnViewDrawLisener lisener) {
        this.lisener = lisener;
    }

    public TextSeekBar(Context context) {
        super(context);
        initView();
    }

    public TextSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public TextSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        setPadding(12, 0, 9, 0);
//        initPaint();
    }

//    private void initPaint() {
//        this.mPaint = new Paint();
//        this.mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
//        // 设置抗锯齿
//        this.mPaint.setAntiAlias(true);
//        float px = DisplayUtil.sp2px(this.getContext(), mTitleTextSize);
//        this.mPaint.setTextSize(px);
//        // 这里设置40是因为拖快的大小是80
//        setPadding(padding, 0, padding, 0);
//    }

    @SuppressLint("NewApi")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            // 得到拖快的宽
            Rect rectThumb = getThumb().getBounds();
            mThumbWidth = rectThumb.width();
            // 得到进度条的宽
            Rect rectSeek = this.getProgressDrawable().getBounds();
            mSeekWidth = rectSeek.width();
        }
    }

    /**
     * 设置文本偏差值,用来设置显示实际内容和显示内容之间的差
     */
    public void setTextDeviation(int value) {
        mDeviationValue = value;
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//         得到写的内容
//        mTitleText = getCurrentTimeString(currentTime);
//        // 文字的宽
//        float numTextWidth = mPaint.measureText(mTitleText);
//        float text_x = mSeekWidth * getProgress() / getMax() + (padding + mThumbWidth - numTextWidth) / 2;
//        canvas.drawText(mTitleText, text_x, mTextY, mPaint);// 画文字

        if(lisener != null){
            lisener.refreshCurrentTime(getCurrentTimeString(currentTime));
        }
    }

    private String getCurrentTimeString(int progress) {
//        int max=getMax();
//        int pro = (int) (totalSeconds * ((progress*1.0) / max));
        String str = DateUtils.generateTimeBySeconds(currentTime);

        return str;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        invalidate();
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        return true;
    }

    public void setTotal(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public interface OnViewDrawLisener{
         void refreshCurrentTime(String currentTime);
    }

}
