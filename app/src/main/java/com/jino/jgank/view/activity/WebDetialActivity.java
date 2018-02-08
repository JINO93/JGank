package com.jino.jgank.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.utils.SnackbarUtils;
import com.jino.jgank.R;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.model.bean.ArticleItem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import butterknife.BindView;
import timber.log.Timber;

public class WebDetialActivity extends BaseActivity {

    public static final String PARAMS_DATA = "data";
    public static final String PARAMS_URL = "url";
    public static final String PARAMS_TITLE = "title";

    private static final String URL_PREFIX = "http://resource";

    @BindView(R.id.web_lay_container)
    public ViewGroup mContainer;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

        @BindView(R.id.webview)
    public WebView mWebview;

    @BindView(R.id.pb_web)
    public ProgressBar mProgressBar;

    @BindView(R.id.fab)
    public FloatingActionButton fab;

    private String mUrl;
    private String mTitle;
    private boolean checked;
    private ArticleItem itemData;
    private boolean liked;


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
        mToolbar.setTitle(itemData.getDesc());
        setupToolBar(mToolbar);
        setupWebView();
        mWebview.loadUrl(itemData.getUrl());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
//        mWebview = new WebView(getApplicationContext());
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mContainer.addView(mWebview, layoutParams);

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                Timber.tag("JINO").d("current progress:%d",newProgress);
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
                String js = "javascript:window.onload=function() { " +
                        "    css = document.createElement('link');" +
//                            "    css.id = 'xxx_browser_2014';" +
                        "    css.rel = 'stylesheet';  " +
                        "css.type='text/css';" +
                        "css.href='" +loadCss()+"';"+
//                        "css.href='" + URL_PREFIX + "dark.css';" +
//                        "console.log(css.href);" +
                        " document.getElementsByTagName('head')[0].appendChild(css); " +
                        "}";
                view.loadUrl(js);
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

    private String loadCss() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("dark.css");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
//            mUrl = bundle.getString(PARAMS_URL);
//            mTitle = bundle.getString(PARAMS_TITLE);
            itemData = (ArticleItem) bundle.getSerializable(PARAMS_DATA);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_detail, menu);
        liked = DBManager.getInstance().exist(itemData, ArticleItem.TYPE_LIKE);
        MenuItem item = menu.findItem(R.id.action_like);
        item.setIcon(liked ? android.R.drawable.star_big_on : android.R.drawable.star_big_off);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_like:
                liked = !liked;
                item.setIcon(liked ? android.R.drawable.star_big_on : android.R.drawable.star_big_off);
                refreshLikeState(liked);
                break;
            case R.id.action_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshLikeState(boolean liked) {
        SnackbarUtils.showContent(mContainer, liked ? "收藏成功" : "取消收藏");
        ArticleItem articleItem = DBManager.getInstance().get(itemData.getUrl());
        if (articleItem == null && liked) {
            itemData.setType(ArticleItem.TYPE_LIKE);
            DBManager.getInstance().insertArticleItem(itemData);
        } else if (liked) {
            articleItem.setType(articleItem.getType() | ArticleItem.TYPE_LIKE);
            DBManager.getInstance().updataArticleItem(articleItem);
        } else {
            articleItem.setType(articleItem.getType() ^ ArticleItem.TYPE_LIKE);
            DBManager.getInstance().updataArticleItem(articleItem);
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
//        System.exit(0);
    }
}
