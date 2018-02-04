package com.jino.jgank;

import com.facebook.stetho.Stetho;
import com.jino.baselibrary.base.BaseApplication;
import com.jino.jgank.db.DBManager;

import timber.log.Timber;

/**
 * Created by JINO on 2018/1/19.
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.init(this);
        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());
        Timber.tag("JINO");
    }
}
