package com.example.law.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.law.R;

/**
 * @author 林书浩
 * @date 2020/08/06
 * @lestDate 2020/08/07
 */
public class SettingActivity extends AppCompatActivity {
    ImageView goBackImage;

    SeekBar textSizeSeekBar;
    SeekBar textStyleSeekBar;
    SharedPreferences sharedPreferences;

    static int textSize;
    static int titleSize;
    static int sizeId = 1;
    static int styleId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE);

        goBackImage = findViewById(R.id.go_back);
        goBackImage.setOnClickListener(new GoBackOnClick());

        textSizeSeekBar = findViewById(R.id.text_size);
        textSizeSeekBar.setOnSeekBarChangeListener(new TextSizeOnSeekBarChange());

        textStyleSeekBar = findViewById(R.id.text_style);
        textStyleSeekBar.setOnSeekBarChangeListener(new TextStyleOnSeekBarChange());

        textSizeSeekBar.setProgress(SettingActivity.getSizeId());
        textStyleSeekBar.setProgress(SettingActivity.getStyleId());

    }

    /**
     * 返回效果
     */
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Intent intent = new Intent(SettingActivity.this, LawActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    /**
     * 返回按钮
     */
    private class GoBackOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    public static int getTextSize(){
        return textSize;
    }
    public static void setTextSize(int textSize){
        SettingActivity.textSize = textSize;
    }
    public static int getTitleSize() {
        return titleSize;
    }
    public static void setTitleSize(int titleSize) {
        SettingActivity.titleSize = titleSize;
    }
    public static int getSizeId() {
        return sizeId;
    }
    public static void setSizeId(int sizeId) {
        SettingActivity.sizeId = sizeId;
    }
    public static int getStyleId() {
        return styleId;
    }

    public static void setStyleId(int styleId) {
        SettingActivity.styleId = styleId;
    }

    /**
     * 修改字体大小的滑动条
     */
    private class TextSizeOnSeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i){
                case 0:
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putInt("text_size",R.dimen.qb_px_10).putInt("title_size",R.dimen.qb_px_15).apply();
                    changeSize(sharedPreferences.getInt("text_size",0),sharedPreferences.getInt("title_size",0),i);
                    break;
                case 1:
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putInt("text_size",R.dimen.qb_px_15).putInt("title_size",R.dimen.qb_px_20).apply();
                    changeSize(sharedPreferences.getInt("text_size",0),sharedPreferences.getInt("title_size",0),i);
                    break;
                case 2:
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putInt("text_size",R.dimen.qb_px_20).putInt("title_size",R.dimen.qb_px_25).apply();
                    changeSize(sharedPreferences.getInt("text_size",0),sharedPreferences.getInt("title_size",0),i);
                    break;
                case 3:
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putInt("text_size",R.dimen.qb_px_25).putInt("title_size",R.dimen.qb_px_30).apply();
                    changeSize(sharedPreferences.getInt("text_size",0),sharedPreferences.getInt("title_size",0),i);
                    break;
                case 4:
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putInt("text_size",R.dimen.qb_px_30).putInt("title_size",R.dimen.qb_px_35).apply();
                    changeSize(sharedPreferences.getInt("text_size",0),sharedPreferences.getInt("title_size",0),i);
                    break;
                default:break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    public void changeSize(int textSize,int titleSize,int id){
        setTextSize(getResources().getDimensionPixelSize(textSize));
        setTitleSize(getResources().getDimensionPixelSize(titleSize));
        SettingActivity.setSizeId(id);
    }

    /**
     * 修改字体类型的滑动条
     */
    private static class TextStyleOnSeekBarChange implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i){
                case 0:
                    SettingActivity.setStyleId(0);
                    break;
                case 1:
                    SettingActivity.setStyleId(1);
                    break;
                case 2:
                    SettingActivity.setStyleId(2);
                    break;
                case 3:
                    SettingActivity.setStyleId(3);
                    break;
                case 4:
                    SettingActivity.setStyleId(4);
                    break;
                default:break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
