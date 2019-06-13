package com.bridgesafe.bridge.ui;

import android.os.Bundle;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;

import android.view.View;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.tv_test})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test:
                break;
        }
    }
}
