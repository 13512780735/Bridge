package com.bridgesafe.bridge.ui.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.view.BorderTextView;

import butterknife.BindView;

/**
 * 船舶信息
 */
public class ShipInfoActivity extends BaseActivity {
    @BindView(R.id.tv_ship_name)//船舶名称
            TextView mTvShipName;
    @BindView(R.id.tv_ship_code)//船舶识别码
            TextView mTvShipCode;
    @BindView(R.id.tv_ship_mmsi)//MMSI
            TextView mTvShipMmsi;
    @BindView(R.id.tv_ship_height)//船舶最大高度
            TextView mTvShipHeight;
    @BindView(R.id.tv_ship_width)//船舶宽度
            TextView mTvShipWidth;
    @BindView(R.id.tv_ship_no_load)//船舶空载
            TextView mTvShipNoLoad;
    @BindView(R.id.tv_ship_full_load)//船舶满载
            TextView mTvShipFullLoad;
    @BindView(R.id.tv_confirm)//提交
            BorderTextView mTvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_info);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("船舶信息", getResources().getColor(R.color.black));
    }
}
