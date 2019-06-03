package common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class FormatEditText extends EditText {

    boolean shouldStopChange = false;
    boolean isAddText;

    private WhiteSpaceFilter filter = new WhiteSpaceFilter();

    public FormatEditText(Context context) {
        super(context);
        init();

    }

    public FormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public FormatEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        setFilters(new InputFilter[]{filter});
        addTextChangedListener(new MyTextWatcher(this));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    class MyTextWatcher implements TextWatcher {
        EditText editText;
        String whiteSpace = " ";

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (shouldStopChange) {
                shouldStopChange = false;
                return;
            }

            filter.setSpaceEnable(true);
            String str = s.toString().trim().replaceAll(whiteSpace, "");
            int len = str.length();
            int courPos = getSelectionStart();
            StringBuilder builder = new StringBuilder();
            if (len <= 3) {
                shouldStopChange = true;
                if (courPos > len) {
                    courPos = len;
                }
                setText(str);
                setSelection(courPos);
            } else if (len > 3 && len <= 7) {
                for (int i = 0; i < str.length(); i++) {
                    builder.append(str.charAt(i));
                    if (i == 2) {
                        builder.append(whiteSpace);
                    }
                }
                shouldStopChange = true;
                if (len == 4) {
                    if (isAddText) {
                        courPos += 1;
                    } else {

                    }

                } else if (len == 7) {
                    if (isAddText) {

                    } else {
                    }
                }

                if (courPos > builder.length()) {
                    courPos = builder.length();
                }
                setText(builder.toString());
                setSelection(courPos);
            } else if (len > 7 && len <= 11) {
                for (int i = 0; i < str.length(); i++) {
                    builder.append(str.charAt(i));
                    if (i == 2 || i == 6) {
                        builder.append(whiteSpace);
                    }
                }

                if (len == 8) {
                    if (isAddText) {
                        if (courPos == 4 || courPos == 9) {
                            courPos += 1;
                        }
                    }
                } else if (len == 11) {
                    if (isAddText) {

                    } else {
                        if (courPos <= 4) {

                        } else if (courPos < 8) {
                            courPos += 1;
                        } else if (courPos > 8) {
                            courPos += 2;
                        }
                    }
                }
                if (courPos > builder.length()) {
                    courPos = builder.length();
                }
                shouldStopChange = true;
                setText(builder.toString());
                setSelection(courPos);
            } else if (len > 11) {
                shouldStopChange = true;
                if (len == 12 && isAddText) {
                    if (courPos >= 5) {
                        courPos -= 1;
                    }
                    if (courPos >= 9) {
                        courPos -= 1;
                    }

                }
                if (courPos > str.length()) {
                    courPos = str.length();
                }
                setText(str);
                setSelection(courPos);
            }
            filter.setSpaceEnable(false);


        }


    }

    /**
     * 以文本的格式获取电话号码
     * @return
     */
    public String getStringText() {
        return this.getText().toString().replace(" ", "");
    }

    class WhiteSpaceFilter implements InputFilter {
        private boolean enable;

        public void setSpaceEnable(boolean enbale) {
            this.enable = enbale;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            isAddText = start != end;
            if (TextUtils.isEmpty(source.toString().trim()) && !enable) {
                return "";
            }
            return source;
        }
    }
}