package com.bridgesafe.bridge.ui.login;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.adapter.LoginRegisterTabAdapter;
import com.bridgesafe.bridge.ui.base.BaseActivity;
import com.bridgesafe.bridge.ui.fragment.LoginFragment;
import com.bridgesafe.bridge.ui.fragment.RegisterFragment;
import com.bridgesafe.bridge.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatas = new ArrayList<>(Arrays.asList("登录", "注册"));
        initUI();
    }

    private void initUI() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(viewpager);
        List<Fragment> mfragments = new ArrayList<Fragment>();
        mfragments.add(new LoginFragment());
        mfragments.add(new RegisterFragment());
        viewpager.setAdapter(new LoginRegisterTabAdapter(getSupportFragmentManager(), mfragments, mDatas));
        viewpager.setCurrentItem(0);
    }
}
