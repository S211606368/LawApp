package com.example.law.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.law.R;
import com.example.law.dao.impl.LawDaoImpl;
import com.example.law.pojo.Law;
import com.example.law.service.function.LayoutFunction;
import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * @author 林书浩
 * @date 2020/07/29
 * @lastDate 2020/08/05
 */
public class LawActivity extends AppCompatActivity {

    TableLayout lawTableLayout;

    LawDaoImpl lawDaoImpl;

    LayoutFunction layoutFunction;

    LinearLayout selectLinearLayout;

    TableRow choiceTableRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.law);

        DatabaseOpenHelper.getInstance(LawActivity.this);
        lawDaoImpl = new LawDaoImpl();

        selectLinearLayout = findViewById(R.id.select);
        selectLinearLayout.setOnClickListener(new SelectOnClick());

        layoutFunction = new LayoutFunction(LawActivity.this);

        lawTableLayout = findViewById(R.id.table);
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
            lawTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_20));
            lawTextView.setGravity(Gravity.CENTER_VERTICAL);

            lawTableRow.setOnClickListener(new LawOnClick(law.getLawId(), law.getLawName()));

            lawTableRow.addView(lawTextView);
            lawTableRow.setBackground(this.getDrawable(R.drawable.white_change_gray));

            lawTableLayout.addView(lawTableRow);
        }
    }

    /**
     * 法律的点击效果
     */
    private class LawOnClick implements View.OnClickListener {
        int lawId;
        String lawName;

        public LawOnClick(int id, String name) {
            super();
            this.lawId = id;
            this.lawName = name;
        }

        @Override
        public void onClick(View view) {
            RegulationActivity.setLaw(lawId, lawName);
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
}
