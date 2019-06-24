package com.bridgesafe.bridge.ui.other;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.listener.IEditTextChangeListener;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.util.EditTextSizeCheckUtil;
import com.bridgesafe.bridge.util.StringUtil;
import com.bridgesafe.bridge.view.BorderTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *修改密码
 */
public class BacdfeedActivity extends BaseActivity {
    @BindView(R.id.ed_forget_phone)
    EditText mEdForgetPhone;
    @BindView(R.id.ed_forget_pwd)
    EditText mEdForgetPwd;
    @BindView(R.id.ed_forget_pwd_01)
    EditText mEdForgetPwd01;
    @BindView(R.id.ed_forget_order_pwd)
    EditText mEdForgetOrderPwd;
    @BindView(R.id.tv_confirm)
    BorderTextView mTvConfirm;

    private String phone, pwd, pwd_confirm, orderPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacdfeed);
        setBackView();
        initUI();
    }
    private void initUI() {
        setTitle("找回密码", getResources().getColor(R.color.title_color));


        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvConfirm);
        textChangeListener.addAllEditText(mEdForgetPhone, mEdForgetPwd, mEdForgetPwd01, mEdForgetOrderPwd);
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
                orderPwd = mEdForgetOrderPwd.getText().toString().trim();
                // SMSSDK.submitVerificationCode("86", mobile, code);
                if (StringUtil.isBlank(pwd)) {
                    showProgress("密码不能为空");
                    return;
                }
                if (!pwd_confirm.equals(pwd)) {
                    showProgress("两次密码不一样，请重新输入");
                    return;
                }
//                Register(password);
//                LoaddingShow();
                break;
        }
    }
}
