package com.example.lawapp.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lawapp.R;
import com.example.lawapp.dao.impl.ChapterDaoImpl;
import com.example.lawapp.dao.impl.LawDaoImpl;
import com.example.lawapp.dao.impl.RegulationDaoImpl;
import com.example.lawapp.pojo.Chapter;
import com.example.lawapp.pojo.Law;
import com.example.lawapp.pojo.Regulation;
import com.example.lawapp.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 法律条例界面
 *
 * @author 林书浩
 * @date 2020/7/31
 * @lastDate 2020/7/31
 */
public class RegulationActivity extends AppCompatActivity {

    /**
     * 顶栏显示所属法律名字的文本框
     */
    TextView lawNameTextView;

    TableLayout chapterTableLayout;
    TableLayout regulationTableLayout;

    ImageView goBackImageView;
    TextView withdrawTextView;

    /**
     * 所属法律编码
     */
    public static int lawId;
    /**
     * 所属法律名字
     */
    public static String lawName;

    ChapterDaoImpl chapterDaoImpl;
    RegulationDaoImpl regulationDaoImpl;

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regulation);

        DatabaseOpenHelper.getInstance(RegulationActivity.this);
        chapterDaoImpl = new ChapterDaoImpl();
        regulationDaoImpl = new RegulationDaoImpl();

        lawNameTextView = findViewById(R.id.law_name);
        lawNameTextView.setText(lawName);

        chapterTableLayout = findViewById(R.id.chapter_table);
        regulationTableLayout = findViewById(R.id.regulation_table);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());
        withdrawTextView = findViewById(R.id.withdraw);
        withdrawTextView.setOnClickListener(new GoBackOnClick());

        scrollView = findViewById(R.id.scroll);

        showTable();
    }

    /**
     * 返回上一页面
     */
    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            RegulationActivity.this.finish();
        }
    }

    /**
     * 显示表格(第一个循环中获取章节目录，第二个循环中获取改章节下的所有条例)
     */
    private void showTable() {
        chapterTableLayout.removeAllViews();
        chapterTableLayout.setStretchAllColumns(true);
        List<Chapter> chaptersList;
        chaptersList = chapterDaoImpl.selectChapter();
        String chapters;

        int row = 0;
        for (Chapter chapter : chaptersList) {
            TableRow chapterTableRow = new TableRow(RegulationActivity.this);

            TextView chapterTextView = new TextView(RegulationActivity.this);
            chapters = chapter.getChapterName() + "  " + chapter.getChapterContent();
            chapterTextView.setText(chapters);
            chapterTextView.setTextColor(0xFFFFFFFF);
            chapterTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_25));

            chapterTableRow.addView(chapterTextView);

            if (row % 2 == 0) {
                chapterTableRow.setBackgroundColor(0xAAAAAAAA);
            } else {
                chapterTableRow.setBackgroundColor(0x8D4B95E0);
            }
            row++;

            chapterTableLayout.addView(chapterTableRow);

            TableRow tableRow = new TableRow(RegulationActivity.this);
            TextView textView = new TextView(RegulationActivity.this);
            textView.setText(chapters);
            textView.setTextColor(0xFFFFFFFF);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_30));
            tableRow.addView(textView);
            tableRow.setBackgroundColor(0xFFFFAD01);
            regulationTableLayout.addView(tableRow);

            chapterTableRow.setOnClickListener(new ChapterOnClick(tableRow,regulationTableLayout));

            List<Regulation> regulationsList;
            regulationsList = regulationDaoImpl.selectRegulation(chapter.getChapterId());

            String regulations;
            int regulationRow = 0;
            for (Regulation regulation : regulationsList) {
                TableRow regulationTableRow = new TableRow(RegulationActivity.this);

                TextView regulationTextView = new TextView(RegulationActivity.this);
                regulations = regulation.getRegulationName() + "  " + regulation.getRegulationContent();
                regulationTextView.setText(regulations);
                regulationTextView.setTextColor(0xFFFFFFFF);
                regulationTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.qb_px_20));

                regulationTableRow.addView(regulationTextView);

                if (regulationRow % 2 == 0) {
                    regulationTableRow.setBackgroundColor(0xAAAAAAAA);
                } else {
                    regulationTableRow.setBackgroundColor(0x8D4B95E0);
                }
                regulationRow++;

                regulationTableLayout.addView(regulationTableRow);
            }
        }
    }

    /**
     * 目录的点击效果（输入要跳转到的y轴坐标）
     */
    private class ChapterOnClick implements View.OnClickListener {
        TableRow tableRow;
        TableLayout tableLayout;

        /**
         *
         * @param tableRow 需要移动到控件位置的控件
         * @param tableLayout 该控件的父控件
         */
        public ChapterOnClick(TableRow tableRow,TableLayout tableLayout) {
            this.tableRow = tableRow;
            this.tableLayout = tableLayout;
        }

        @Override
        public void onClick(View view) {
            int moveY = tableRow.getTop()+tableLayout.getTop();
            scrollView.scrollTo(0, moveY);
        }
    }

    /**
     * 获取所属法律的信息
     *
     * @param lawId   所属法律编码
     * @param lawName 所属法律名字
     */
    public static void setLaw(int lawId, String lawName) {
        RegulationActivity.lawId = lawId;
        RegulationActivity.lawName = lawName;
    }

}
