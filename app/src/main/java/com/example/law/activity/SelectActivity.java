package com.example.law.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.law.R;
import com.example.law.dao.impl.LawDaoImpl;
import com.example.law.pojo.Law;
import com.example.law.service.function.LayoutFunction;

import java.util.List;

/**
 * @author 林书浩
 * @date 2020/08/05
 * @lastDate 2020/08/05
 */
public class SelectActivity extends AppCompatActivity {
    TextView titleSelectTextView;
    TextView fullTextSelectTextView;

    TableLayout selectTableLayout;

    LawDaoImpl lawDaoImpl;
    LayoutFunction layoutFunction;

    ImageView goBackImageView;

    /**
     * 判断是否是标题检索
     */
    static boolean isTitleSelect = true;

    /**
     * 全文搜索的法律id，如果为-1则代表所有法律条文搜索
     */
    static int lawId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        lawDaoImpl = new LawDaoImpl();

        selectTableLayout = findViewById(R.id.select_table);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());

        titleSelectTextView = findViewById(R.id.title_select);
        fullTextSelectTextView = findViewById(R.id.full_text_select);
        titleSelectTextView.setOnClickListener(new TitleSelectOnclick(SelectActivity.this));
        fullTextSelectTextView.setOnClickListener(new FullTextSelectOnClick(SelectActivity.this));
        changeSelectStyle(SelectActivity.this);

        /*if (isTitleSelect){
            showLawTable();
        }*/
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
            SelectActivity.setIsTitleSelect(true);
            changeSelectStyle(context);
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
            SelectActivity.setIsTitleSelect(false);
            changeSelectStyle(context);
        }
    }

    public void changeSelectStyle(Context context){
        if (isTitleSelect){
            titleSelectTextView.setBackground(getDrawable(R.drawable.select_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBackgroundWhite));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.not_select_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBlueDark));
        }else {
            titleSelectTextView.setBackground(getDrawable(R.drawable.not_select_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBlueDark));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.select_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context,R.color.colorBackgroundWhite));
        }
    }
    /**
     * 获取当前是标题检索还是全文检索
     *
     * @param isTitleSelect
     */
    public static void setIsTitleSelect(boolean isTitleSelect) {
        SelectActivity.isTitleSelect = isTitleSelect;
    }

    public static void setIsTitleSelect(boolean isTitleSelect,int lawId) {
        SelectActivity.isTitleSelect = isTitleSelect;
        SelectActivity.lawId = lawId;
    }

    /**
     * 显示法律表格
     */
    private void showLawTable() {
        selectTableLayout.removeAllViews();
        selectTableLayout.setStretchAllColumns(true);
        List<Law> lawList;
        lawList = lawDaoImpl.selectLaw();
        String lawName;

        int row = 0;
        for (Law law : lawList) {
            TableRow lawTableRow = new TableRow(SelectActivity.this);

            TextView lawTextView = new TextView(SelectActivity.this);
            lawName = law.getLawName();
            lawTextView.setText(lawName);
            lawTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_20));
            lawTextView.setMinLines(getResources().getDimensionPixelSize(R.dimen.qb_px_1));
            lawTextView.setGravity(Gravity.CENTER_VERTICAL);

            lawTableRow.setOnClickListener(new LawOnClick(law.getLawId(), law.getLawName()));

            layoutFunction.splitLines(selectTableLayout);

            lawTableRow.addView(lawTextView);
            lawTableRow.setBackground(this.getDrawable(R.drawable.white_change_gray));

            selectTableLayout.addView(lawTableRow);
        }
        layoutFunction.splitLines(selectTableLayout);
    }

    /**
     * 返回上一页面
     */
    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SelectActivity.this.finish();
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
            Intent intent = new Intent(SelectActivity.this, RegulationActivity.class);
            startActivity(intent);

        }
    }
}
