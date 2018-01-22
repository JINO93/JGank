package com.jino.jgank;

import com.jino.baselibrary.base.BaseApplication;

import timber.log.Timber;

/**
 * Created by JINO on 2018/1/19.
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
