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
    TextView withdrawTextView;

    TableLayout lawTableLayout;

    LawDaoImpl lawDaoImpl;

    LayoutFunction layoutFunction;

    TextView titleSelectTextView;
    TextView fullTextSelectTextView;

    LinearLayout selectLinearLayout;

    /**
     * 判断是否是标题检索
     */
    boolean isTitleSelect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.law);

        DatabaseOpenHelper.getInstance(LawActivity.this);
        lawDaoImpl = new LawDaoImpl();

        withdrawTextView = findViewById(R.id.law_select);
        withdrawTextView.setOnClickListener(new LawSelectOnClick());

        titleSelectTextView = findViewById(R.id.title_select);
        titleSelectTextView.setOnClickListener(new TitleSelectOnclick(LawActivity.this));
        fullTextSelectTextView = findViewById(R.id.full_text_select);
        fullTextSelectTextView.setOnClickListener(new FullTextSelectOnClick(LawActivity.this));

        selectLinearLayout = findViewById(R.id.select);
        selectLinearLayout.setOnClickListener(new SelectOnClick());

        layoutFunction = new LayoutFunction(LawActivity.this);

        lawTableLayout = findViewById(R.id.table);
        showTable();
    }

    /**
     * 标题检索按钮
     */
    private class TitleSelectOnclick implements View.OnClickListener {

        Context context;

        public TitleSelectOnclick(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            isTitleSelect = true;
            titleSelectTextView.setBackground(getDrawable(R.drawable.select_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBackgroundWhite));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.not_select_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBlueDark));
        }
    }

    /**
     * 全文检索按钮
     */
    private class FullTextSelectOnClick implements View.OnClickListener {

        Context context;

        public FullTextSelectOnClick(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            isTitleSelect = false;
            titleSelectTextView.setBackground(getDrawable(R.drawable.not_select_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBlueDark));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.select_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBackgroundWhite));
        }
    }

    private class LawSelectOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
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
            lawTextView.setMinLines(getResources().getDimensionPixelSize(R.dimen.qb_px_1));
            lawTextView.setGravity(Gravity.CENTER_VERTICAL);

            lawTableRow.setOnClickListener(new LawOnClick(law.getLawId(), law.getLawName()));

            layoutFunction.splitLines(lawTableLayout);
            lawTableRow.addView(lawTextView);
            lawTableRow.setBackground(this.getDrawable(R.drawable.white_change_gray));

            lawTableLayout.addView(lawTableRow);
        }
        layoutFunction.splitLines(lawTableLayout);
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

    public class SelectOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            SelectActivity.setIsTitleSelect(isTitleSelect);
            Intent intent = new Intent(LawActivity.this, SelectActivity.class);
            startActivity(intent);
        }
    }
}
