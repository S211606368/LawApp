package com.example.law.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.law.R;

/**
 * @author 林书浩
 * date 2020/08/06
 * lestDate 2020/08/07
 */
public class SettingActivity extends AppCompatActivity {
    ImageView goBackImage;

    SeekBar textSizeSeekBar;
    SeekBar textStyleSeekBar;

    static int textSize;
    static int titleSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        goBackImage = findViewById(R.id.go_back);
        goBackImage.setOnClickListener(new GoBackOnClick());

        textSizeSeekBar = findViewById(R.id.text_size);
        textSizeSeekBar.setOnSeekBarChangeListener(new TextSizeOnSeekBarChange());

        textStyleSeekBar = findViewById(R.id.text_style);
        textStyleSeekBar.setOnSeekBarChangeListener(new TextStyleOnSeekBarChange());

    }

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
    public static int getTitleSize(){
        return titleSize;
    }
    public static void setTitleSize(int titleSize){
        SettingActivity.titleSize = titleSize;
    }

    private class TextSizeOnSeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i){
                case 0:
                    setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_12));
                    setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_17));
                    seekBar.setProgress(i);
                    break;
                case 1:
                    setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_15));
                    setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_20));
                    break;
                case 2:
                    setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_17));
                    setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_22));
                    break;
                case 3:
                    setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_19));
                    setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_24));
                    break;
                case 4:
                    setTextSize(getResources().getDimensionPixelSize(R.dimen.qb_px_22));
                    setTitleSize(getResources().getDimensionPixelSize(R.dimen.qb_px_27));
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

    private class TextStyleOnSeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
