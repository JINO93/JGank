package com.jino.baselibrary.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jino.baselibrary.presenter.IPresenter;
import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.utils.ConditionUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Acitvity基类
 * Created by JINO on 2018/1/14.
 */

public abstract class BaseActivity extends SupportActivity implements IActivity, IView {

    private Unbinder unbinder;

//    @Inject
//    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConditionUtils.checkParams(layoutId() >= 0, "please set the correct layout id");
        setContentView(layoutId());
        unbinder = ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroy();
    }

    /**
     * 设置toolbar
     *
     * @param toolbar
     */
    protected void setupToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    protected void startActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }

    protected void startActivity(Class activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
