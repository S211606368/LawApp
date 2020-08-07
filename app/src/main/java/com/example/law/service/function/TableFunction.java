package com.example.law.service.function;

import android.content.Context;
import android.widget.TableRow;

import com.example.law.R;
import com.example.law.activity.RegulationActivity;

/**
 * 表格布局工具类
 *
 * @author 林书浩
 * @date 2020/08/06
 * @lestDate 2020/08/06
 */
public class TableFunction {
    TableRow choiceTableRow;
    Context activity;

    public TableFunction(Context activity) {
        this.activity = activity;
    }

    public void changeTableRow(TableRow tableRow) {
        if (null != choiceTableRow && choiceTableRow != tableRow) {
            choiceTableRow.setBackground(activity.getDrawable(R.drawable.table_row_background_white));
        }
        tableRow.setBackground(activity.getDrawable(R.drawable.table_row_background_gray));
        choiceTableRow = tableRow;
    }

    public TableRow getChoiceTableRow() {
        return choiceTableRow;
    }

    public void setChoiceTableRow(TableRow choiceTableRow) {
        this.choiceTableRow = choiceTableRow;
    }
}
