package com.jino.jgank.view.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;

import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.btn_sort)
    public Button btn_sort;
    @BindView(R.id.btn_clear)
    public ImageButton btn_clear;
    @BindView(R.id.rv_history)
    public RecyclerView list_history;

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
        return R.layout.activity_history;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
