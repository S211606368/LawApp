package com.example.law.activity;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.law.R;
import com.example.law.dao.impl.ChapterDaoImpl;
import com.example.law.dao.impl.RegulationDaoImpl;
import com.example.law.pojo.Chapter;
import com.example.law.pojo.Regulation;
import com.example.law.service.function.LayoutFunction;
import com.example.law.service.function.TableFunction;
import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 法律条例界面
 *
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/07
 */
public class RegulationActivity extends AppCompatActivity {

    /**
     * 顶栏显示所属法律名字的文本框
     */
    TextView lawNameTextView;

    TableLayout chapterTableLayout;
    TableLayout regulationTableLayout;

    TableLayout indexTableLayout;

    Button goBackImageView;
    Button indexTextView;

    LinearLayout fullTextSelectLinearLayout;

    TableFunction tableFunction;

    /**
     * 所属法律编码
     */
    public static long lawId;
    /**
     * 所属法律名字
     */
    public static String lawName;

    ChapterDaoImpl chapterDaoImpl;
    RegulationDaoImpl regulationDaoImpl;

    ScrollView scrollView;

    PopupWindow indexPopupWindow;

    LayoutFunction layoutFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regulation);

        tableFunction = new TableFunction(RegulationActivity.this);
        layoutFunction = new LayoutFunction(RegulationActivity.this);

        DatabaseOpenHelper.getInstance(RegulationActivity.this);
        chapterDaoImpl = new ChapterDaoImpl();
        regulationDaoImpl = new RegulationDaoImpl();

        lawNameTextView = findViewById(R.id.law_name);
        lawNameTextView.setText(lawName);

        fullTextSelectLinearLayout = findViewById(R.id.full_text_select);
        fullTextSelectLinearLayout.setOnClickListener(new FullTextSelectOnClick());

        chapterTableLayout = findViewById(R.id.chapter_table);
        regulationTableLayout = findViewById(R.id.regulation_table);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());
        indexTextView = findViewById(R.id.index);
        indexTextView.setOnClickListener(new IndexOnClick());

        scrollView = findViewById(R.id.scroll);

        layoutFunction = new LayoutFunction(RegulationActivity.this);

        showTable();
    }

    /**
     * 返回上一页面
     */
    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SelectActivity.setIsTitleSelect(true, -1);
            RegulationActivity.this.finish();
        }
    }

    /**
     * 索引页面
     */
    private class IndexOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            lawNameTextView.setSelected(true);
            indexPopupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
        }
    }

    /**
     * 加载索引界面
     */
    public void loadIndexWindow() {
        final View indexWindowView = getLayoutInflater().inflate(R.layout.index, null, false);

        indexPopupWindow = new PopupWindow(indexWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        indexPopupWindow.setAnimationStyle(R.style.AnimationRightFade);

        indexWindowView.setBackground(this.getDrawable(R.drawable.index_frame));

        indexWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (indexWindowView.isShown()) {
                    indexPopupWindow.dismiss();
                }
                return false;
            }
        });
        indexTableLayout = indexWindowView.findViewById(R.id.chapter_table);

        indexTableLayout.removeAllViews();
        indexTableLayout.setStretchAllColumns(true);
    }

    /**
     * 显示索引界面
     *
     * @param chapters        章节名字
     * @param chapterTextSize 字体大小
     * @param chapterTableRow 获取该章节所在的位置
     */
    private void showIndexList(String chapters, int chapterTextSize, TableRow chapterTableRow) {
        int length = 13;
        if (chapters.length() > length) {
            chapters = chapters.substring(0, length) + "…";
        }

        LinearLayout indexLinearLayout = createTableRow(chapters, chapterTextSize);
        indexLinearLayout.setBackground(this.getDrawable(R.drawable.table_row_background_gray));
        indexLinearLayout.setOnClickListener(new ChapterOnClick(chapterTableRow, regulationTableLayout, indexPopupWindow));
        indexTableLayout.addView(indexLinearLayout);

    }


    /**
     * 显示表格(第一个循环中获取章节名称，第二个循环中获取改章节下的所有条例)
     */
    private void showTable() {
        loadIndexWindow();
        regulationTableLayout.removeAllViews();
        regulationTableLayout.setStretchAllColumns(true);
        List<Chapter> chaptersList;
        chaptersList = chapterDaoImpl.selectChapter(lawId);

        for (Chapter chapter : chaptersList) {
            int chapterTextSize;
            String chapters;

            chapterTextSize = SettingActivity.getTextSize();
            chapters = chapter.getChapterName() + "  " + chapter.getChapterContent();

            TableRow chapterTableRow = createTableRow(chapters, chapterTextSize);

            regulationTableLayout.addView(chapterTableRow);


            showIndexList(chapters, chapterTextSize, chapterTableRow);


            List<Regulation> regulationsList;
            regulationsList = regulationDaoImpl.selectRegulation(chapter.getChapterId());


            String regulations;

            for (Regulation regulation : regulationsList) {
                regulations = regulation.getRegulationName() + "  " + regulation.getRegulationContent();
                int regulationTextSize = SettingActivity.getTextSize();

                TableRow regulationTableRow = createTableRow(regulations, regulationTextSize);

                regulationTableLayout.addView(regulationTableRow);
            }
        }
    }

    /**
     * 创建行
     *
     * @param content  输入该行要显示的内容
     * @param textSide 该行字体大小
     */
    private TableRow createTableRow(String content, int textSide) {
        TableRow tableRow = new TableRow(RegulationActivity.this);
        TextView textView = new TextView(RegulationActivity.this);

        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSide);
        textView.setBackground(this.getDrawable(R.drawable.text_view_background));

        tableRow.addView(textView);

        tableRow.setBackground(this.getDrawable(R.drawable.table_row_background_white));

        tableRow.setOnClickListener(new RegulationOnClick(tableRow, tableFunction, content));
        return tableRow;

    }

    /**
     * 条例的点击效果
     */
    private class RegulationOnClick implements View.OnClickListener, View.OnLongClickListener {
        TableRow regulationTableRow;
        TableFunction tableFunction;
        String content;

        public RegulationOnClick(TableRow regulationTableRow, TableFunction tableFunction, String content) {
            this.regulationTableRow = regulationTableRow;
            this.tableFunction = tableFunction;
            this.content = content;
        }

        @Override
        public void onClick(View view) {
            tableFunction.changeTableRow(regulationTableRow);
        }

        @Override
        public boolean onLongClick(View view) {

            /*int[] position = new int[2];
            regulationTableRow.getLocationInWindow(position);
            int tableRowTop = position[1] - regulationTableRow.getTop();
            int tableRowBottom = position[1] + regulationTableRow.getBottom();
            if (tableRowTop>getResources().getDimensionPixelSize(R.dimen.qb_px_70)){

            }*/
            /*final View optionWindowView = getLayoutInflater().inflate(R.layout.option, null, false);
            optionPopupWindow = new PopupWindow(optionWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            optionWindowView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (optionWindowView.isShown()) {
                        optionPopupWindow.dismiss();
                    }
                    return false;
                }
            });
            TextView copyTextView;
            copyTextView = optionWindowView.findViewById(R.id.chapter_table);
            copyTextView.setOnClickListener(new CopyOnClick(content));
            optionPopupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 100);*/
            return true;
        }
    }

    /**
     * 目录的点击效果（输入要跳转到的y轴坐标）
     */
    private class ChapterOnClick implements View.OnClickListener {
        TableRow tableRow;
        TableLayout tableLayout;
        PopupWindow indexWindow;

        /**
         * @param tableRow    需要移动到控件位置的控件
         * @param tableLayout 该控件的父控件
         * @param indexWindow 弹出的窗口界面
         */
        public ChapterOnClick(TableRow tableRow, TableLayout tableLayout, PopupWindow indexWindow) {
            this.tableRow = tableRow;
            this.tableLayout = tableLayout;
            this.indexWindow = indexWindow;
        }

        @Override
        public void onClick(View view) {
            int moveY = tableRow.getTop() + tableLayout.getTop();
            scrollView.smoothScrollTo(0, moveY);
            tableFunction.changeTableRow(tableRow);
            indexWindow.dismiss();
        }
    }

    /**
     * 获取所属法律的信息
     *
     * @param lawId   所属法律编码
     * @param lawName 所属法律名字
     */
    public static void setLaw(long lawId, String lawName) {
        RegulationActivity.lawId = lawId;
        RegulationActivity.lawName = lawName;
    }

    /**
     * 搜索的点击事件
     */
    private class FullTextSelectOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            SelectActivity.setIsTitleSelect(false, lawId);

            Intent intent = new Intent(RegulationActivity.this, SelectActivity.class);
            startActivity(intent);
        }
    }

    private static ClipboardManager manager;

    /**
     * 复制的点击事件
     */
    private class CopyOnClick implements View.OnClickListener {
        String content;
        TableLayout tableLayout;

        public CopyOnClick(String content) {
            this.content = content;
        }

        @Override
        public void onClick(View view) {
            ClipData clipData = ClipData.newPlainText("Label", content);
            //将ClipData数据复制到剪贴板：
            manager.setPrimaryClip(clipData);

        }
    }
}
