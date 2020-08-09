package com.example.law.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.law.R;

/**
 * @author 林书浩
 * @date 2020/08/09
 * @lestDate 2020/08/09
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent( WelcomeActivity.this, LawActivity.class);
        startActivity(intent);
        finish();
    }
}
