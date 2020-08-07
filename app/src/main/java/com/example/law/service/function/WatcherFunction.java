package com.example.law.service.function;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.law.activity.SelectActivity;

/**
 * 监听事件工具类
 *
 * @author 林书浩
 * @date 2020/08/06
 * @lestDate 2020/08/07
 */
public class WatcherFunction {
    SelectActivity selectActivity = new SelectActivity();

    /**
     * 文本监听
     *
     * @param editText
     * @param imageView
     */
    public void watcherText(final EditText editText, final ImageView imageView, final boolean isTitleSelect) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isTitleSelect) {
                    selectActivity.showLawTable(editText.getText().toString());
                } else {
                    selectActivity.showRegulationTable(editText.getText().toString());
                }
                if ("".equals(editText.getText().toString())) {
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        };
        editText.addTextChangedListener(textWatcher);
    }
}
