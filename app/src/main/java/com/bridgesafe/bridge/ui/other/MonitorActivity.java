package com.bridgesafe.bridge.ui.other;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.listener.IEditTextChangeListener;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.login.ForgetPwdActivity;
import com.bridgesafe.bridge.util.EditTextSizeCheckUtil;
import com.bridgesafe.bridge.view.BorderTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class MonitorActivity extends BaseActivity {
    @BindView(R.id.ed_content)
    EditText mEdContent;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ed_email)
    EditText mEdEmail;
    @BindView(R.id.tv_confirm)
    BorderTextView mTvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("意见与反馈", getResources().getColor(R.color.title_color));
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvConfirm);
        textChangeListener.addAllEditText(mEdContent);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvConfirm.setContentColorResource01(getResources().getColor(R.color.theme_bg_tex));
                    mTvConfirm.setStrokeColor01(getResources().getColor(R.color.theme_bg_tex));
                    // mTvLogin.setOnClickListener(RegisterFragment.this);
                    mTvConfirm.setClickable(true);
                } else {
                    mTvConfirm.setContentColorResource01(getResources().getColor(R.color.bt_color));
                    mTvConfirm.setStrokeColor01(getResources().getColor(R.color.bt_color));
                    mTvConfirm.setClickable(false);
                    // tv_register.setClickable(false);
                }
            }
        });

    }

    @OnClick({R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                finish();
                break;
        }
    }
}
