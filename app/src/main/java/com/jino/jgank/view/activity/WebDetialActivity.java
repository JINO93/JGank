package com.jino.jgank.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;

import butterknife.BindView;

public class WebDetialActivity extends BaseActivity {

    public static final String PARAMS_URL = "url";
    public static final String PARAMS_TITLE = "title";

    @BindView(R.id.web_lay_container)
    public ViewGroup mContainer;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    //    @BindView(R.id.webview)
    public WebView mWebview;

    @BindView(R.id.pb_web)
    public ProgressBar mProgressBar;

    @BindView(R.id.fab)
    public FloatingActionButton fab;

    private String mUrl;
    private String mTitle;


    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadSucceed() {

    }

    @Override
    public void injectComponent(AppComponent component) {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_web_detial;
    }

    @Override
    public void initView() {
        setupToolBar(mToolbar);
        setupWebView();

        mWebview.loadUrl(mUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        mWebview = new WebView(getApplicationContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer.addView(mWebview, layoutParams);

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });

        mWebview.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
////                super.onReceivedSslError(view, handler, error);
//                handler.proceed();
//            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });

        WebSettings settings = mWebview.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(PARAMS_URL);
            mTitle = bundle.getString(PARAMS_TITLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebview != null) {
            mWebview.removeAllViews();
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.setTag(null);
            mWebview.clearHistory();
            mWebview.destroy();
            mWebview = null;
        }
        System.exit(0);
    }
}
