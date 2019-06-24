package com.bridgesafe.bridge.ui.main;

import android.os.Bundle;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;

/**
 * 水位表
 */
public class WaterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("水位表",getResources().getColor(R.color.title_color));
    }
}
