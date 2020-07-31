package com.example.lawapp.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
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
     * 显示表格
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
     * 获取所属法律的信息
     *
     * @param lawId 所属法律编码
     * @param lawName 所属法律名字
     */
    public static void setLaw(int lawId, String lawName) {
        RegulationActivity.lawId = lawId;
        RegulationActivity.lawName = lawName;
    }

}
