package com.example.law.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.law.service.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 法律条例界面
 *
 * @author 林书浩
 * @date 2020/07/31
 * @lastDate 2020/08/03
 */
public class RegulationActivity extends AppCompatActivity {

    /**
     * 顶栏显示所属法律名字的文本框
     */
    TextView lawNameTextView;

    TableLayout chapterTableLayout;
    TableLayout regulationTableLayout;

    TableLayout indexTableLayout;

    ImageView goBackImageView;
    TextView indexTextView;

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

    PopupWindow indexWindow;

    LayoutFunction layoutFunction;

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
            RegulationActivity.this.finish();
        }
    }

    /**
     * 索引页面
     */
    private class IndexOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            indexWindow.showAtLocation(view, Gravity.RIGHT,0,0);
        }
    }

    /**
     * 加载索引界面
     */
    public void loadIndexWindow(){
        final View indexWindowView = getLayoutInflater().inflate(R.layout.index,null,false);

        indexWindow = new PopupWindow(indexWindowView,getResources().getDimensionPixelSize(R.dimen.qb_px_250), ViewGroup.LayoutParams.MATCH_PARENT,true);
        indexWindow.setAnimationStyle(R.style.AnimationRightFade);

        indexWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (indexWindowView.isShown()){
                    indexWindow.dismiss();
                    indexWindow = null;
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
     * @param chapters 章节名字
     * @param chapterTextSize 字体大小
     * @param chapterLinearLayout 获取该章节所在的位置
     */
    private void showIndexList(String chapters,int chapterTextSize,LinearLayout chapterLinearLayout){
        int length = 8;
        if (chapters.length() > length){
            chapters = chapters.substring(0,length) + "…";
        }

        LinearLayout indexLinearLayout = createTableRow(chapters,chapterTextSize);
        indexLinearLayout.setBackground(this.getDrawable(R.drawable.white_change_gray));
        indexLinearLayout.setOnClickListener(new ChapterOnClick(chapterLinearLayout,regulationTableLayout,indexWindow));
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

            chapterTextSize = getResources().getDimensionPixelSize(R.dimen.qb_px_30);
            chapters = chapter.getChapterName() + "  " + chapter.getChapterContent();

            LinearLayout chapterLinearLayout = createTableRow(chapters,chapterTextSize);

            layoutFunction.topSplitLines(regulationTableLayout);
            regulationTableLayout.addView(chapterLinearLayout);
            layoutFunction.blankLines(regulationTableLayout);

            showIndexList(chapters,chapterTextSize,chapterLinearLayout);


            List<Regulation> regulationsList;
            regulationsList = regulationDaoImpl.selectRegulation(chapter.getChapterId());


            String regulations;

            for (Regulation regulation : regulationsList) {
                regulations = regulation.getRegulationName() + "  " + regulation.getRegulationContent();
                int regulationTextSize = getResources().getDimensionPixelSize(R.dimen.qb_px_20);

                LinearLayout regulationLinearLayout = createTableRow(regulations,regulationTextSize);

                layoutFunction.topSplitLines(regulationTableLayout);
                regulationTableLayout.addView(regulationLinearLayout);
                layoutFunction.blankLines(regulationTableLayout);
            }
            layoutFunction.blankLines(regulationTableLayout);
        }
    }

    /**
     * 创建行
     *
     * @param content 输入该行要显示的内容
     * @param textSide 该行字体大小
     */
    private LinearLayout createTableRow(String content, int textSide){
        LinearLayout linearLayout = new TableRow(RegulationActivity.this);
        TextView textView = new TextView(RegulationActivity.this);

        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSide);

        linearLayout.addView(textView);
        linearLayout.setBackground(this.getDrawable(R.drawable.white_change_gray));

        linearLayout.setOnClickListener(new RegulationOnClick(content));
        return linearLayout;

    }

    /**
     * 条例的点击效果
     */
    private class RegulationOnClick implements View.OnClickListener{
        String regulationOnClick;

        public RegulationOnClick(String regulationOnClick){
         this.regulationOnClick = regulationOnClick;
        }
        @Override
        public void onClick(View view) {

        }
    }

    /**
     * 目录的点击效果（输入要跳转到的y轴坐标）
     */
    private class ChapterOnClick implements View.OnClickListener {
        LinearLayout linearLayout;
        TableLayout tableLayout;
        PopupWindow indexWindow;

        /**
         *
         * @param linearLayout 需要移动到控件位置的控件
         * @param tableLayout 该控件的父控件
         * @param indexWindow 弹出的窗口界面
         */
        public ChapterOnClick(LinearLayout linearLayout,TableLayout tableLayout,PopupWindow indexWindow) {
            this.linearLayout = linearLayout;
            this.tableLayout = tableLayout;
            this.indexWindow = indexWindow;
        }

        @Override
        public void onClick(View view) {
            int moveY = linearLayout.getTop()+tableLayout.getTop();
            scrollView.scrollTo(0, moveY);

            indexWindow.dismiss();
            indexWindow = null;
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