package com.example.law.service.function;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    public void blankLines(TableLayout tableLayout){
        TextView blankTextView = new TextView(context);
        blankTextView.setBackground(context.getDrawable(R.drawable.blank_background));
        tableLayout.addView(blankTextView);
    }

    /**
     * 下分割线
     */
    public void bottomSplitLines(TableLayout tableLayout){
        TextView splitTextView = new TextView(context);
        splitTextView.setBackground(context.getDrawable(R.drawable.bottom_line_background));
        tableLayout.addView(splitTextView);
    }

    /**
     * 上分割线
     */
    public void topSplitLines(TableLayout tableLayout){
        TextView splitTextView = new TextView(context);
        splitTextView.setBackground(context.getDrawable(R.drawable.top_line_background));
        tableLayout.addView(splitTextView);
    }

    /**
     * 分割线
     */
    public void splitLines(TableLayout tableLayout){
        TextView splitTextView = new TextView(context);
        splitTextView.setHeight(1);
        splitTextView.setBackground(context.getDrawable(R.drawable.line));
        tableLayout.addView(splitTextView);
    }
}
