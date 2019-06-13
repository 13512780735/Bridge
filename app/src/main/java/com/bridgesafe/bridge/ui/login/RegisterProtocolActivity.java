package com.bridgesafe.bridge.ui.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bridgesafe.bridge.R;
import com.bridgesafe.bridge.ui.base.BaseActivity;

import butterknife.BindView;

public class RegisterProtocolActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_protocol);
        initUI();
    }

    private void initUI() {
        setBackView();
        setTitle("注册协议", getResources().getColor(R.color.black));
        mWebView.loadUrl("file:///android_asset/Registeragreement.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

}
