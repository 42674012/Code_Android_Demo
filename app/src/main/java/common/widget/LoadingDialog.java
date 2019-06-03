/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import cn.gog.conmentmanage.R;


/**
 * 通过的加载对话框
 * <p>
 * Author: Gujin
 * Time: 16/9/3  13:26
 */
public class LoadingDialog extends ProgressDialog {

    private String tip;

    private TextView tvTip;


    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_loading);


        if (!TextUtils.isEmpty(tip)) {
            this.tvTip = (TextView) findViewById(android.R.id.message);
            tvTip.setText(tip);
        }
        setCancelable(true);
//        setCancelable(false);
    }

    /**
     * 设置消息提示
     *
     * @param message
     */
    @Override
    public void setMessage(CharSequence message) {
        tip = message.toString();
    }
}
