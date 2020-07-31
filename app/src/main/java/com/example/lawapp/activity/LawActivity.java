package com.example.lawapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lawapp.R;
import com.example.lawapp.dao.impl.LawDaoImpl;
import com.example.lawapp.pojo.Code;
import com.example.lawapp.pojo.Law;
import com.example.lawapp.pojo.Regulation;
import com.example.lawapp.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * @author 林书浩
 * @date 2020/7/29
 * @lastDate 2020/7/31
 */
public class LawActivity extends AppCompatActivity {
    ImageView goBackImageView;
    TextView withdrawTextView;

    /**
     * 顶栏显示所属法典名字的文本框
     */
    TextView codeNameTextView;

    TableLayout tableLayout;

    /**
     * 所属法典编码
     */
    public static int codeId;
    /**
     * 所属法典名字
     */
    public static String codeName;

    LawDaoImpl lawDaoImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.law);

        DatabaseOpenHelper.getInstance(LawActivity.this);
        lawDaoImpl = new LawDaoImpl();

        codeNameTextView = findViewById(R.id.code_name);
        codeNameTextView.setText(codeName);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());
        withdrawTextView = findViewById(R.id.withdraw);
        withdrawTextView.setOnClickListener(new GoBackOnClick());

        tableLayout = findViewById(R.id.table);
        showTable();
    }

    /**
     * 返回上一页面
     */
    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            LawActivity.this.finish();
        }
    }

    public static void setCode(int codeId, String codeName) {
        LawActivity.codeId = codeId;
        LawActivity.codeName = codeName;
    }

    /**
     * 显示表格
     */
    private void showTable() {
        tableLayout.removeAllViews();
        tableLayout.setStretchAllColumns(true);
        List<Law> lawList;
        lawList = lawDaoImpl.selectLaw();
        String lawName;

        int row = 0;
        for (Law law : lawList) {
            TableRow tableRow = new TableRow(LawActivity.this);

            TextView lawTextView = new TextView(LawActivity.this);
            lawName = law.getLawName();
            lawTextView.setText(lawName);
            lawTextView.setTextColor(0xFFFFFFFF);
            lawTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_30));

            tableRow.addView(lawTextView);

            if (row % 2 == 0) {
                tableRow.setBackgroundColor(0xAAAAAAAA);
            } else {
                tableRow.setBackgroundColor(0x8D4B95E0);
            }
            row++;

             tableRow.setOnClickListener(new LawOnClick(law.getLawId(),law.getLawName()));
            tableLayout.addView(tableRow);
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
            RegulationActivity.setLaw(lawId,lawName);
            Intent intent = new Intent(LawActivity.this, RegulationActivity.class);
            startActivity(intent);
        }
    }
}
