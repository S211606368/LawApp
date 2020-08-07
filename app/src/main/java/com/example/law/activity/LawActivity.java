package com.example.law.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.law.R;
import com.example.law.dao.impl.LawDaoImpl;
import com.example.law.pojo.Law;
import com.example.law.service.function.TableFunction;
import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/07
 */
public class LawActivity extends AppCompatActivity {

    TableLayout lawTableLayout;

    LawDaoImpl lawDaoImpl;

    Button settingButton;

    LinearLayout selectLinearLayout;

    TableFunction tableFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.law);

        SettingActivity.setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_15));
        SettingActivity.setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_20));

        DatabaseOpenHelper.getInstance(LawActivity.this);
        lawDaoImpl = new LawDaoImpl();

        settingButton = findViewById(R.id.law_setting);
        settingButton.setOnClickListener(new SettingOnClick());

        selectLinearLayout = findViewById(R.id.select);
        selectLinearLayout.setOnClickListener(new SelectOnClick());

        lawTableLayout = findViewById(R.id.table);

        tableFunction = new TableFunction(LawActivity.this);
        showTable();
    }

    /**
     * 显示表格
     */
    private void showTable() {
        lawTableLayout.removeAllViews();
        lawTableLayout.setStretchAllColumns(true);
        List<Law> lawList;
        lawList = lawDaoImpl.selectLaw();
        String lawName;

        int row = 0;
        for (Law law : lawList) {
            TableRow lawTableRow = new TableRow(LawActivity.this);

            TextView lawTextView = new TextView(LawActivity.this);
            lawName = law.getLawName();
            lawTextView.setText(lawName);
            lawTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SettingActivity.getTitleSize());
            lawTextView.setGravity(Gravity.CENTER_VERTICAL);
            lawTextView.setBackground(this.getDrawable(R.drawable.text_view_background));

            lawTableRow.setOnClickListener(new LawOnClick(law.getLawId(), law.getLawName(),lawTableRow));

            lawTableRow.addView(lawTextView);
            lawTableRow.setBackground(this.getDrawable(R.drawable.table_row_background_white));

            lawTableLayout.addView(lawTableRow);
        }
    }

    /**
     * 法律的点击效果
     */
    private class LawOnClick implements View.OnClickListener {
        long lawId;
        String lawName;
        TableRow lawTableRow;

        public LawOnClick(long id, String name,TableRow lawTableRow) {
            super();
            this.lawId = id;
            this.lawName = name;
            this.lawTableRow = lawTableRow;
        }

        @Override
        public void onClick(View view) {
            RegulationActivity.setLaw(lawId, lawName);
            tableFunction.changeTableRow(lawTableRow);
            Intent intent = new Intent(LawActivity.this, RegulationActivity.class);
            startActivity(intent);

        }
    }

    /**
     * 转到查询界面
     */
    public class SelectOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SelectActivity.setIsTitleSelect(true);
            Intent intent = new Intent(LawActivity.this, SelectActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 设置的点击事件
     */
    private class SettingOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LawActivity.this, SettingActivity.class);
            startActivity(intent);
        }
    }
}
