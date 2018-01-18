package com.jino.baselibrary.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.jino.baselibrary.interfaces.IPresenter;
import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.utils.ConditionUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Acitvity基类
 * Created by JINO on 2018/1/14.
 */

public abstract class BaseActivity<P extends IPresenter> extends SupportActivity implements IActivity, IView {

    private Unbinder unbinder;

    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ConditionUtils.checkIntParams(layoutId() <= 0, "please set the correct layout id");
        setContentView(layoutId());
        unbinder = ButterKnife.bind(this);
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
    }
}
