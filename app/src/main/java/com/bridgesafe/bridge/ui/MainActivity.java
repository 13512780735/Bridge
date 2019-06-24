package com.bridgesafe.bridge.ui;

import android.os.Bundle;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.main.TideActivity;
import com.bridgesafe.bridge.ui.main.WaterActivity;
import com.bridgesafe.bridge.ui.main.WeatherActivity;
import com.bridgesafe.bridge.util.AppManager;
import com.elvishew.xlog.XLog;
import com.lxj.xpopup.XPopup;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.iv_member, R.id.rl_water, R.id.rl_weather, R.id.rl_tide})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_member:
                new XPopup.Builder(mContext)
//                        .asCustom(new CustomDrawerPopupView(getContext()))
                        .asCustom(new PagerDrawerPopup(mContext))
//                        .asCustom(new ListDrawerPopupView(getContext()))
                        .show();
                break;
            case R.id.rl_water:
                toActivity(WaterActivity.class);
                break;
            case R.id.rl_weather:
                toActivity(WeatherActivity.class);
                break;
            case R.id.rl_tide:
                toActivity(TideActivity.class);
                break;
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                //finish();
                AppManager.getAppManager().finishAllActivity();
                // System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
