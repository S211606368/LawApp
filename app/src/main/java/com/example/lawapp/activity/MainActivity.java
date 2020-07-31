package com.example.lawapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.lawapp.R;
import com.example.lawapp.dao.impl.CodeDaoImpl;
import com.example.lawapp.pojo.Code;
import com.example.lawapp.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 选择法典界面
 *
 * @author 林书浩
 * @date 2020/07/28
 * @lastDate 2020/07/30
 */
public class MainActivity extends AppCompatActivity {
    CodeDaoImpl codeDaoImpl;

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseOpenHelper.getInstance(MainActivity.this);
        codeDaoImpl = new CodeDaoImpl();

        tableLayout = findViewById(R.id.table);
        showTable();
    }

    /**
     * 显示表格
     */
    private void showTable() {
        tableLayout.removeAllViews();
        tableLayout.setStretchAllColumns(true);
        List<Code> codeList;
        codeList = codeDaoImpl.selectCode();
        String codeName;

        int row = 0;
        for (Code code : codeList) {
            TableRow tableRow = new TableRow(MainActivity.this);

            TextView codeTextView = new TextView(MainActivity.this);
            codeName = "    " + code.getCodeName();
            codeTextView.setText(codeName);
            codeTextView.setTextColor(0xFFFFFFFF);
            codeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_30));

            tableRow.addView(codeTextView);

            if (row % 2 == 0) {
                tableRow.setBackgroundColor(0xAAAAAAAA);
            } else {
                tableRow.setBackgroundColor(0x8D4B95E0);
            }
            row++;

            tableRow.setOnClickListener(new CodeOnClick(code.getCodeId(), code.getCodeName()));
            tableLayout.addView(tableRow);

        }
    }

    /**
     * 法典的点击效果
     */
    private class CodeOnClick implements View.OnClickListener {
        int codeId;
        String codeName;

        public CodeOnClick(int id, String name) {
            super();
            this.codeId = id;
            this.codeName = name;
        }

        @Override
        public void onClick(View view) {
            LawActivity.setCode(codeId, codeName);
            Intent intent = new Intent(MainActivity.this, LawActivity.class);
            startActivity(intent);
        }
    }
}