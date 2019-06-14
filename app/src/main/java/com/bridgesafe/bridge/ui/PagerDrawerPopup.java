package com.bridgesafe.bridge.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bridgesafe.bridge.R;
import com.lxj.xpopup.core.DrawerPopupView;

public class PagerDrawerPopup extends DrawerPopupView {
    public PagerDrawerPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_pager_drawer;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "PagerDrawerPopup onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "PagerDrawerPopup onDismiss");
    }
}
