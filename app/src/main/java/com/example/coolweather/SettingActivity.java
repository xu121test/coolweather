package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.coolweather.util.SettingInfo;

public class SettingActivity extends AppCompatActivity {
    private Button save_bt;
    private Switch switch_bt;
    private NumberPicker numberPicker;
    private int def;
    private boolean flag;
    private SharedPreferences prefs;
    private RelativeLayout relative_hz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        setContentView(R.layout.activity_setting);
        save_bt =  findViewById(R.id.ok_bt);
        switch_bt = findViewById(R.id.switch_bt);
        relative_hz = findViewById(R.id.relative_hz);
        numberPicker = findViewById(R.id.num_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(24);
        prefs = getSharedPreferences("SettingInfo", Context.MODE_PRIVATE);
        def = prefs.getInt("update", 8);
        flag = prefs.getBoolean("isAllowUpdate", true);
        if (flag == true) {
            relative_hz.setVisibility(View.VISIBLE);
        } else {
            relative_hz.setVisibility(View.GONE);
        }
        switch_bt.setChecked(flag);
        numberPicker.setValue(def);
        numberPicker.setOnValueChangedListener((picker, oldv, newv) -> {
            def = newv;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("update", def);
            editor.apply();

            SettingInfo settingInfo = new SettingInfo(def);
            Intent intent = new Intent();
            intent.putExtra("settingInfo", settingInfo);
            setResult(RESULT_OK, intent);
        });

        switch_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag = b;
                if (flag == true) {
                    relative_hz.setVisibility(View.VISIBLE);
                } else {
                    relative_hz.setVisibility(View.GONE);
                }
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isAllowUpdate", flag);
                editor.apply();

                SettingInfo settingInfo = new SettingInfo(flag);
                Intent intent = new Intent();
                intent.putExtra("settingInfo", settingInfo);
                setResult(RESULT_OK, intent);
            }
        });

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}