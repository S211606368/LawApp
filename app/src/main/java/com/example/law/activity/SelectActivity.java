package com.example.law.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.law.R;
import com.example.law.dao.impl.ChapterDaoImpl;
import com.example.law.dao.impl.LawDaoImpl;
import com.example.law.dao.impl.RegulationDaoImpl;
import com.example.law.pojo.Chapter;
import com.example.law.pojo.Law;
import com.example.law.pojo.Regulation;
import com.wyt.searchedittext.SearchEditText;

import java.util.List;

/**
 * @author 林书浩
 * @date 2020/08/05
 * @lastDate 2020/08/07
 */
public class SelectActivity extends AppCompatActivity {
    TextView titleSelectTextView;
    TextView fullTextSelectTextView;

    TableLayout selectTableLayout;

    LawDaoImpl lawDaoImpl;
    ChapterDaoImpl chapterDaoImpl;
    RegulationDaoImpl regulationDaoImpl;

    ImageView clearSelectImageView;
    SearchEditText selectTextSearchEditText;

    TextView goBackTextView;

    /**
     * 判断是否是标题检索
     */
    static boolean isTitleSelect = true;

    /**
     * 全文搜索的法律id，如果为-1则代表所有法律条文搜索
     */
    static long lawId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        lawDaoImpl = new LawDaoImpl();
        chapterDaoImpl = new ChapterDaoImpl();
        regulationDaoImpl = new RegulationDaoImpl();

        selectTextSearchEditText = findViewById(R.id.select_text);

        selectTableLayout = findViewById(R.id.select_table);

        clearSelectImageView = findViewById(R.id.clear_select);
        clearSelectImageView.setOnClickListener(new ClearSelectOnClick());

        watcherText(selectTextSearchEditText, clearSelectImageView);

        goBackTextView = findViewById(R.id.go_back);
        goBackTextView.setOnClickListener(new GoBackOnClick());

        titleSelectTextView = findViewById(R.id.title_select);
        fullTextSelectTextView = findViewById(R.id.full_text_select);
        titleSelectTextView.setOnClickListener(new TitleSelectOnclick(SelectActivity.this));
        fullTextSelectTextView.setOnClickListener(new FullTextSelectOnClick(SelectActivity.this));
        changeSelectStyle(SelectActivity.this);

        if (isTitleSelect) {
            showLawTable("");
        } else {
            showRegulationTable("");
        }
    }

    /**
     * 标题检索按钮
     */
    private class TitleSelectOnclick implements View.OnClickListener {

        Context context;

        public TitleSelectOnclick(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            showLawTable(selectTextSearchEditText.getText().toString());
            SelectActivity.setIsTitleSelect(true);
            changeSelectStyle(context);
        }
    }

    /**
     * 全文检索按钮
     */
    private class FullTextSelectOnClick implements View.OnClickListener {

        Context context;

        public FullTextSelectOnClick(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            showRegulationTable(selectTextSearchEditText.getText().toString());
            SelectActivity.setIsTitleSelect(false);
            changeSelectStyle(context);
        }
    }

    public void changeSelectStyle(Context context) {
        if (isTitleSelect) {
            titleSelectTextView.setBackground(getDrawable(R.drawable.select_left_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context, R.color.colorBackgroundWhite));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.not_select_right_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context, R.color.colorTopBackground));
        } else {
            titleSelectTextView.setBackground(getDrawable(R.drawable.not_select_left_background));
            titleSelectTextView.setTextColor(ContextCompat.getColor(context, R.color.colorTopBackground));
            fullTextSelectTextView.setBackground(getDrawable(R.drawable.select_right_background));
            fullTextSelectTextView.setTextColor(ContextCompat.getColor(context, R.color.colorBackgroundWhite));
        }
    }

    /**
     * 获取当前是标题检索还是全文检索
     *
     * @param isTitleSelect 判断是否是标题检索
     */
    public static void setIsTitleSelect(boolean isTitleSelect) {
        SelectActivity.isTitleSelect = isTitleSelect;
    }

    public static void setIsTitleSelect(boolean isTitleSelect, long lawId) {
        SelectActivity.isTitleSelect = isTitleSelect;
        SelectActivity.lawId = lawId;
    }

    /**
     * 显示法律表格
     */
    public void showLawTable(String selectContent) {
        selectTableLayout.removeAllViews();
        selectTableLayout.setStretchAllColumns(true);
        List<Law> lawList;
        lawList = lawDaoImpl.selectLaw();
        String lawName;

        for (Law law : lawList) {
            TableRow lawTableRow = new TableRow(SelectActivity.this);

            TextView lawTextView = new TextView(SelectActivity.this);
            lawName = law.getLawName();

            if (lawName.contains(selectContent)) {
                lawTableRow.setOnClickListener(new LawOnClick(law.getLawId(), law.getLawName()));

                lawTextView.setText(lawName);
               /* keyWordChangeColor(selectContent,lawName,lawTextView);*/
                lawTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,SettingActivity.getTitleSize());
                lawTextView.setGravity(Gravity.CENTER_VERTICAL);

                lawTableRow.addView(lawTextView);

                lawTableRow.setBackground(this.getDrawable(R.drawable.white_change_gray));

                selectTableLayout.addView(lawTableRow);
            }
        }
    }

    /**
     * 显示条例表格
     */
    public void showRegulationTable(String selectContent) {
        selectTableLayout.removeAllViews();
        selectTableLayout.setStretchAllColumns(true);

        if (!"".equals(selectContent)) {

            List<Chapter> chaptersList;
            if (lawId == -1) {
                chaptersList = chapterDaoImpl.selectChapter();
            } else {
                chaptersList = chapterDaoImpl.selectChapter(lawId);
            }


            for (Chapter chapter : chaptersList) {
                String lawName = lawDaoImpl.selectLaw(chapter.getLawId()).get(0).getLawName();
                List<Regulation> regulationsList;
                regulationsList = regulationDaoImpl.selectRegulation(chapter.getChapterId(), selectContent);

                for (Regulation regulation : regulationsList) {

                    TableRow regulationTableRow = createTableRow(regulation, lawName, chapter.getLawId());

                    selectTableLayout.addView(regulationTableRow);
                }
            }
        }
    }

    /**
     * 创建行
     *
     * @param regulation 条例对象
     * @param lawName    所属法律名字
     * @param lawId      所属法律id
     * @return 表格的行
     */
    private TableRow createTableRow(Regulation regulation, String lawName, long lawId) {
        TableRow tableRow = new TableRow(SelectActivity.this);
        TextView textView = new TextView(SelectActivity.this);
        String regulationTextContent = regulation.getRegulationName() + " " + regulation.getRegulationContent();
        textView.setText(regulationTextContent);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SettingActivity.getTextSize());

        tableRow.addView(textView);

        tableRow.setBackground(this.getDrawable(R.drawable.white_change_gray));

        tableRow.setOnClickListener(new RegulationOnClick(lawId, lawName));
        return tableRow;

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

    private class ClearSelectOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            selectTextSearchEditText.setText("");
        }
    }

    /**
     * 法律的点击效果
     */
    private class LawOnClick implements View.OnClickListener {
        long lawId;
        String lawName;

        public LawOnClick(long id, String name) {
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

    /**
     * 条例的点击效果
     */
    private class RegulationOnClick implements View.OnClickListener {
        long lawId;
        String lawName;

        public RegulationOnClick(long id, String name) {
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

    /**
     * 文本监听
     *
     * @param editText 文本框
     * @param imageView 清除按钮
     */
    public void watcherText(final EditText editText, final ImageView imageView) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ("".equals(editText.getText().toString())) {
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }

                if (isTitleSelect) {
                    showLawTable(editText.getText().toString());
                } else {

                    showRegulationTable(editText.getText().toString());

                }
            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    /*public void keyWordChangeColor(String content,String keyWord,TextView textView){
        List<Integer> keyWordList = new ArrayList<>();

        int keyWordLength = keyWord.length();
        String temp = content;
        int keyWordAfterLength = 0;
        int start = -1;

        do {
            start = temp.indexOf(keyWord);

            if (start != -1) {
                start = start + keyWordAfterLength;
                keyWordList.add(start);
                keyWordAfterLength = start + keyWordAfterLength;
                temp = content.substring(keyWordAfterLength);
            }
        } while (start != -1);

        SpannableStringBuilder styledKeyWord = new SpannableStringBuilder(content);
        for (Integer i : keyWordList) {
            styledKeyWord.setSpan(

                    new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorOrange)),
                    i,
                    i + keyWordAfterLength,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(styledKeyWord);

    }*/
}
