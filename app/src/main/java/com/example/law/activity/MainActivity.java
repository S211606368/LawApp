package com.example.law.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.law.R;
import com.example.law.dao.impl.CodeDaoImpl;
import com.example.law.pojo.Code;
import com.example.law.service.function.LayoutFunction;
import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 选择法典界面
 *
 * @author 林书浩
 * @date 2020/07/28
 * @lastDate 2020/08/03
 */
public class MainActivity extends AppCompatActivity {
    CodeDaoImpl codeDaoImpl;

    TableLayout codeTableLayout;

    LayoutFunction layoutFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseOpenHelper.getInstance(MainActivity.this);
        codeDaoImpl = new CodeDaoImpl();

        layoutFunction = new LayoutFunction(MainActivity.this);

        codeTableLayout = findViewById(R.id.table);
        showTable();
    }

    /**
     * 显示表格
     */
    private void showTable() {
        codeTableLayout.removeAllViews();
        codeTableLayout.setStretchAllColumns(true);
        List<Code> codeList;
        codeList = codeDaoImpl.selectCode();
        String codeName;

        for (Code code : codeList) {
            TableRow codeTableRow = new TableRow(MainActivity.this);

            TextView codeTextView = new TextView(MainActivity.this);
            codeName = "    " + code.getCodeName();
            codeTextView.setText(codeName);
            codeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_20));

            codeTableRow.addView(codeTextView);
            codeTableRow.setClickable(true);

            codeTableRow.setOnClickListener(new CodeOnClick(code.getCodeId(), code.getCodeName()));
            codeTableLayout.addView(codeTableRow);
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
            /*LawActivity.setCode(codeId, codeName);
            Intent intent = new Intent(MainActivity.this, LawActivity.class);
            startActivity(intent);*/

        }
    }
}