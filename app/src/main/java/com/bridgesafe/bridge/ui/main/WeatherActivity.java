package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;

/**
 * 天气
 */
public class WeatherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("天气",getResources().getColor(R.color.title_color));
    }
}
