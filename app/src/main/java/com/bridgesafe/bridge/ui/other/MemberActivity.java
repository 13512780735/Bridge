package com.bridgesafe.bridge.ui.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * 个人资料
 */
public class MemberActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.ed_nickName)
    EditText mEdNickname;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ed_qq)
    EditText mEdQQ;
    @BindView(R.id.ed_wechat)
    EditText mEdWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        setBackView();
        initUI();
    }

    private void initUI() {
        setTitle("个人信息", getResources().getColor(R.color.black));
    }
}
