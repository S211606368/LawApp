package com.example.law.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author 林书浩
 * @date 2020/08/07
 * @lestDate 2020/08/07
 */
@SuppressLint("AppCompatCustomView")
public class RollTextView extends TextView {
    public RollTextView(Context context) {
        super(context);
    }

    public RollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //重写这个方法，返回为true，让TextView一直获取焦点
    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

    }


}
