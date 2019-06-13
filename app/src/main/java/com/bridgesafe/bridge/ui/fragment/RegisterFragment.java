package com.bridgesafe.bridge.ui.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.listener.IEditTextChangeListener;
import com.bridgesafe.bridge.ui.base.BaseFragment;
import com.bridgesafe.bridge.ui.login.RegisterProtocolActivity;
import com.bridgesafe.bridge.util.EditTextSizeCheckUtil;
import com.bridgesafe.bridge.util.StringUtil;
import com.bridgesafe.bridge.view.BorderTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment {

    @BindView(R.id.ed_register_phone)
    EditText mEdRregisterPhone;
    @BindView(R.id.ed_register_pwd)
    EditText mEdRregisterPwd;
    @BindView(R.id.ed_register_code)
    EditText mEdRregisterCode;
    @BindView(R.id.send_code_btn)
    BorderTextView mTvSendCode;
    @BindView(R.id.tv_register)
    BorderTextView mTvRegister;
    @BindView(R.id.checkbox01)
    CheckBox mCheckBox;
    private String phone, pwd, code;


    TimeCount time = new TimeCount(60000, 1000);
    @Override
    protected int setContentView() {
        return R.layout.fragment_register;
    }

    @Override
    protected void lazyLoad() {
        mTvSendCode.setContentColorResource01(getResources().getColor(R.color.bt_color));
        mTvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendCode();
            }
        });
        if (!mCheckBox.isChecked()) {
            showProgress("请同意条款");
            return;
        }


        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvRegister);
        textChangeListener.addAllEditText(mEdRregisterPhone, mEdRregisterPwd, mEdRregisterCode);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvRegister.setContentColorResource01(getResources().getColor(R.color.theme_bg_tex));
                    mTvRegister.setStrokeColor01(getResources().getColor(R.color.theme_bg_tex));
                    //mTvRegister.setOnClickListener(RegisterFragment.this);
                    mTvRegister.setClickable(true);
                } else {
                    mTvRegister.setContentColorResource01(getResources().getColor(R.color.bt_color));
                    mTvRegister.setStrokeColor01(getResources().getColor(R.color.bt_color));

                    mTvRegister.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.tv_register, R.id.protocol_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                //startMainActivity();\
                phone = mEdRregisterPhone.getText().toString().trim();
                pwd = mEdRregisterPwd.getText().toString().trim();
                code = mEdRregisterCode.getText().toString().trim();
                // SMSSDK.submitVerificationCode("86", mobile, code);
                if (StringUtil.isBlank(pwd)) {
                    showProgress("密码不能为空");
                    return;
                }
                if (StringUtil.isBlank(code)) {
                    showProgress("验证码不能为空");
                    return;
                }
                if (!mCheckBox.isChecked()) {
                    showProgress("請同意條款");
                    return;
                }
//                Register(password);
//                LoaddingShow();
                break;
            case R.id.protocol_tv:
                toActivity(RegisterProtocolActivity.class);
                break;
        }
    }

    /**
     * 倒计时
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mTvSendCode.setText("获取验证码");
            mTvSendCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mTvSendCode.setClickable(false);//防止重复点击
            mTvSendCode.setText(millisUntilFinished / 1000 + "s");
        }
    }
}
