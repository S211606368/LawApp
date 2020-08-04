package com.example.law.service.function;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.law.R;

/**
 * 布局工具类（主要提供页面上的一些UI，如分割线）
 * @author 林书浩
 */
public class LayoutFunction {
    Context context;
    public LayoutFunction(Context context){
        this.context = context;
    }

    /**
     * 空一行
     */
    public void blankLines(LinearLayout linearLayout){
        TextView blankTextView = new TextView(context);
        blankTextView.setBackground(context.getDrawable(R.drawable.blank_background));
        linearLayout.addView(blankTextView);
    }

    /**
     * 下分割线
     */
    public void bottomSplitLines(LinearLayout linearLayout){
        TextView splitTextView = new TextView(context);
        splitTextView.setBackground(context.getDrawable(R.drawable.bottom_line_background));
        linearLayout.addView(splitTextView);
    }

    /**
     * 上分割线
     */
    public void topSplitLines(LinearLayout linearLayout){
        TextView splitTextView = new TextView(context);
        splitTextView.setBackground(context.getDrawable(R.drawable.top_line_background));
        linearLayout.addView(splitTextView);
    }
}
