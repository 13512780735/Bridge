package com.bridgesafe.bridge.ui;

import android.os.Bundle;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.elvishew.xlog.XLog;
import com.lxj.xpopup.XPopup;

import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_test = findView(R.id.tv_test);
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XLog.d("点击了");
                new XPopup.Builder(mContext)
//                        .asCustom(new CustomDrawerPopupView(getContext()))
                        .asCustom(new PagerDrawerPopup(mContext))
//                        .asCustom(new ListDrawerPopupView(getContext()))
                        .show();
            }
        });
    }

//    @OnClick({R.id.tv_test})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_test:
//
//                break;
//        }
//    }
}
