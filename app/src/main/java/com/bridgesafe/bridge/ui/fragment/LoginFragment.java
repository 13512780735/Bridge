package com.bridgesafe.bridge.ui.fragment;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.listener.IEditTextChangeListener;
import com.bridgesafe.bridge.ui.MainActivity;
import com.bridgesafe.bridge.ui.base.BaseFragment;
import com.bridgesafe.bridge.ui.login.ForgetPwdActivity;
import com.bridgesafe.bridge.util.EditTextSizeCheckUtil;
import com.bridgesafe.bridge.util.SharedPreferencesUtils;
import com.bridgesafe.bridge.util.StringUtil;
import com.bridgesafe.bridge.view.BorderTextView;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {
    @BindView(R.id.ed_login_phone)
    EditText mEdLoginPhone;
    @BindView(R.id.ed_login_pwd)
    EditText mEdLoginPwd;
    @BindView(R.id.tv_login)
    BorderTextView mTvLogin;
    private String phone, pwd;

    @Override
    protected int setContentView() {
        return R.layout.fragment_login;
    }

    @Override
    protected void lazyLoad() {
        phone = SharedPreferencesUtils.getString(getActivity(), "phone");
        pwd = SharedPreferencesUtils.getString(getActivity(), "pwd");
        if (!StringUtil.isBlank(phone) && !StringUtil.isBlank(pwd)) {
            mTvLogin.setContentColorResource01(getResources().getColor(R.color.theme_bg_tex));
            mTvLogin.setStrokeColor01(getResources().getColor(R.color.theme_bg_tex));
            mTvLogin.setClickable(true);
        }
        mEdLoginPhone.setText(phone);
        mEdLoginPwd.setText(pwd);
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(mTvLogin);
        textChangeListener.addAllEditText(mEdLoginPhone, mEdLoginPwd);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mTvLogin.setContentColorResource01(getResources().getColor(R.color.theme_bg_tex));
                    mTvLogin.setStrokeColor01(getResources().getColor(R.color.theme_bg_tex));
                    // mTvLogin.setOnClickListener(RegisterFragment.this);
                    mTvLogin.setClickable(true);
                } else {
                    mTvLogin.setContentColorResource01(getResources().getColor(R.color.bt_color));
                    mTvLogin.setStrokeColor01(getResources().getColor(R.color.bt_color));
                    mTvLogin.setClickable(false);
                    // tv_register.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.tv_forget_pwd, R.id.tv_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pwd:
                toActivity(ForgetPwdActivity.class);
                break;
            case R.id.tv_login:
                phone = mEdLoginPhone.getText().toString().trim();
                pwd = mEdLoginPwd.getText().toString().trim();

                toLogin(phone, pwd);

                break;
        }
    }

    private void toLogin(String phone, String pwd) {
        SharedPreferencesUtils.put(getActivity(), "phone", phone);
        SharedPreferencesUtils.put(getActivity(), "pwd", pwd);
        toActivity(MainActivity.class);
        toFinish();
    }
}
