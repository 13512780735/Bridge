package com.bridgesafe.bridge.ui.other;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.util.StatusBarUtil;

import butterknife.BindView;

public class WarningDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_bridge_name)//桥梁名称
            TextView mTvBridgeName;
    @BindView(R.id.tv_bridge_warning)//安全预警
            TextView mTvBridgeWarning;
    @BindView(R.id.tv_bridge_time)//预警时间
            TextView mTvBridgeTime;
    @BindView(R.id.tv_bridge_time01)//维护时间
            TextView mTvBridgeTime01;
    @BindView(R.id.tv_bridge_time02)//通船时间
            TextView mTvBridgeTime02;
    @BindView(R.id.iv_bridge_pic)//桥梁图片
            ImageView mIvBridgePic;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_details);
        int color = getResources().getColor(R.color.theme_bg_tex);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
        setBackView();
        ininUI();
    }

    private void ininUI() {
        titleBar.setBackgroundColor(getResources().getColor(R.color.theme_bg_tex));
        setTitle("详情", getResources().getColor(R.color.black));
        mTvBridgeName.setText("额头湾立交桥");
        mTvBridgeWarning.setText("地基不均匀沉降");
        mTvBridgeTime.setText("2019年8月15日 09:30:25");
        mTvBridgeTime01.setText("2019年12月15日");
        mTvBridgeTime02.setText("2019年12月20日");
    }
}
