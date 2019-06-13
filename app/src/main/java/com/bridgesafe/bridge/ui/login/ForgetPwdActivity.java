package com.bridgesafe.bridge.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.listener.IEditTextChangeListener;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.fragment.RegisterFragment;
import com.bridgesafe.bridge.util.EditTextSizeCheckUtil;
import com.bridgesafe.bridge.util.StatusBarUtil;
import com.bridgesafe.bridge.util.StringUtil;
import com.bridgesafe.bridge.view.BorderTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    RelativeLayout mTitleBar;
    @BindView(R.id.ed_forget_phone)
    EditText mEdForgetPhone;
    @BindView(R.id.ed_forget_pwd)
    EditText mEdForgetPwd;
    @BindView(R.id.ed_forget_pwd_01)
    EditText mEdForgetPwd01;
    @BindView(R.id.ed_forget_code)
    EditText mEdForgetCode;
    @BindView(R.id.send_code_btn)
    BorderTextView mTvSendCode;
    @BindView(R.id.tv_confirm)
    BorderTextView mTvConfirm;
    TimeCount time = new TimeCount(60000, 1000);
    private String phone, pwd, pwd_confirm, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        int color = getResources().getColor(R.color.theme_bg_tex);
        StatusBarUtil.setColor(this, color, 0);
        StatusBarUtil.setLightMode(this);
        setBackView();
        initUI();
    }

    private void initUI() {
        mTitleBar.setBackgroundColor(getResources().getColor(R.color.theme_bg_tex));
        setTitle("找回密码", getResources().getColor(R.color.white));
        mTvSendCode.setContentColorResource01(getResources().getColor(R.color.bt_color));
        mTvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sendCode();
            }
        });


        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvConfirm);
        textChangeListener.addAllEditText(mEdForgetPhone, mEdForgetPwd, mEdForgetPwd01, mEdForgetCode);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvConfirm.setContentColorResource01(getResources().getColor(R.color.theme_bg_tex));
                    mTvConfirm.setStrokeColor01(getResources().getColor(R.color.theme_bg_tex));
                    //mTvRegister.setOnClickListener(RegisterFragment.this);
                    mTvConfirm.setClickable(true);
                } else {
                    mTvConfirm.setContentColorResource01(getResources().getColor(R.color.bt_color));
                    mTvConfirm.setStrokeColor01(getResources().getColor(R.color.bt_color));

                    mTvConfirm.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //startMainActivity();\
                phone = mEdForgetPhone.getText().toString().trim();
                pwd = mEdForgetPwd.getText().toString().trim();
                pwd_confirm = mEdForgetPwd01.getText().toString().trim();
                code = mEdForgetCode.getText().toString().trim();
                // SMSSDK.submitVerificationCode("86", mobile, code);
                if (StringUtil.isBlank(pwd)) {
                    showProgress("密码不能为空");
                    return;
                }
                if (!pwd_confirm.equals(pwd)) {
                    showProgress("两次密码不一样，请重新输入");
                    return;
                }
                if (StringUtil.isBlank(code)) {
                    showProgress("验证码不能为空");
                    return;
                }
//                Register(password);
//                LoaddingShow();
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
