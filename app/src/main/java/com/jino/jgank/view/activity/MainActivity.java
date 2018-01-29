package com.jino.jgank.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.jgank.R;
import com.jino.jgank.di.component.DaggerActivityComponent;
import com.jino.jgank.presenter.MainPresenter;
import com.jino.jgank.view.fragment.GankArticleFragment;

import butterknife.BindView;

@PreActivity
public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.lay_dawer)
    public DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    public Toolbar mToolBar;
    @BindView(R.id.view_navigation)
    public NavigationView mNavigation;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void injectComponent(AppComponent component) {
        DaggerActivityComponent.builder()
                .appComponent(component).build()
                .inject(this);
    }


    @Override
    public void initView() {
        setupToolBar(mToolBar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, new GankArticleFragment()).commit();
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawer, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);

        mNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return false;
    }
}
