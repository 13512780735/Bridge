package com.bridgesafe.bridge.ui.other;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.login.LoginActivity;
import com.bridgesafe.bridge.util.AppManager;
import com.bridgesafe.bridge.util.DataClearUtil;
import com.bridgesafe.bridge.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_clear_size)
    TextView tv_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("设置",getResources().getColor(R.color.black));
        tv_clear.setText(DataClearUtil.getTotalCacheSize(this));
    }
    @OnClick({R.id.rl_clear,R.id.tv_logout})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_clear:
                DataClearUtil.cleanAllCache(this);
                Toast.makeText(this, "清除缓存成功", Toast.LENGTH_SHORT).show();
                tv_clear.setText(DataClearUtil.getTotalCacheSize(this));
                break;
            case R.id.tv_logout:
                AppManager.getAppManager().finishAllActivity();
                SharedPreferencesUtils.put(mContext,"pwd","");
                toActivity(LoginActivity.class);

                break;
        }
    }
}
