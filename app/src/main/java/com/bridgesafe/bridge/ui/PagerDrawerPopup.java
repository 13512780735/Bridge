package com.bridgesafe.bridge.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.other.BacdfeedActivity;
import com.bridgesafe.bridge.ui.other.MemberActivity;
import com.bridgesafe.bridge.ui.other.MonitorActivity;
import com.bridgesafe.bridge.ui.other.RecordActivity;
import com.bridgesafe.bridge.ui.other.SettingActivity;
import com.bridgesafe.bridge.ui.other.ShipInfoActivity;
import com.bridgesafe.bridge.ui.other.WarningActivity;
import com.lxj.xpopup.core.DrawerPopupView;

public class PagerDrawerPopup extends DrawerPopupView implements View.OnClickListener {
    private ImageView mIvAvatar;
    private TextView mTvMember, mTvRecord, mTvShipinfo, mTvWarning, mTvMonitor, mTvBacdfeed, mTvSetting;

    public PagerDrawerPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_pager_drawer;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initUI();
    }


    private void initUI() {
        mIvAvatar = findViewById(R.id.iv_avatar);
        mTvMember = findViewById(R.id.tv_member_info);
        mTvRecord = findViewById(R.id.tv_record_icon);
        mTvShipinfo = findViewById(R.id.tv_shipinfo_icon);
        mTvWarning = findViewById(R.id.tv_warning);
        mTvMonitor = findViewById(R.id.tv_monitor);
        mTvBacdfeed = findViewById(R.id.tv_bacdfeed);
        mTvSetting = findViewById(R.id.tv_setting);
        mTvMember.setOnClickListener(this);
        mTvRecord.setOnClickListener(this);
        mTvShipinfo.setOnClickListener(this);
        mTvWarning.setOnClickListener(this);
        mTvMonitor.setOnClickListener(this);
        mTvBacdfeed.setOnClickListener(this);
        mTvSetting.setOnClickListener(this);

    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "PagerDrawerPopup onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "PagerDrawerPopup onDismiss");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_member_info:
                toActivity(MemberActivity.class);
                break;
            case R.id.tv_record_icon:
                toActivity(RecordActivity.class);
                break;
            case R.id.tv_shipinfo_icon:
                toActivity(ShipInfoActivity.class);
                break;
            case R.id.tv_warning:
                toActivity(WarningActivity.class);
                break;
            case R.id.tv_monitor:
                toActivity(MonitorActivity.class);
                break;
            case R.id.tv_bacdfeed:
                toActivity(BacdfeedActivity.class);
                break;
            case R.id.tv_setting:
                toActivity(SettingActivity.class);
                break;
        }
    }

    public void toActivity(Class activity) {
        Intent intent = new Intent(getContext(), activity);
        getContext().startActivity(intent);
    }
}
