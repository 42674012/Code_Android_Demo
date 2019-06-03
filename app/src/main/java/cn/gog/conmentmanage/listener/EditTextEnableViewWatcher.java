package cn.gog.conmentmanage.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import common.utils.StringUtils;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/16 on 10:51
 * desc:
 */

public class EditTextEnableViewWatcher implements TextWatcher {

    private View mView;

    public EditTextEnableViewWatcher(View view) {
        mView = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String txt = "";
        if (null != editable) {
            txt = editable.toString();
        }
        if (null != mView) {
            mView.setEnabled(!StringUtils.isEmpty(txt));
        }
    }
}
