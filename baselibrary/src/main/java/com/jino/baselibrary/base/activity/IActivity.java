package com.jino.baselibrary.base.activity;

import com.jino.baselibrary.di.component.AppComponent;

/**
 * Created by JINO on 2018/1/14.
 */

public interface IActivity {

    void injectComponent(AppComponent component);

    int layoutId();

    void initView();
}
