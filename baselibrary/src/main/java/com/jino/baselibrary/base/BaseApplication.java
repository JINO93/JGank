package com.jino.baselibrary.base;

import android.app.Application;

import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.component.DaggerAppComponent;
import com.jino.baselibrary.di.module.ApplicationModule;

/**
 * Created by JINO on 2018/1/19.
 */

public class BaseApplication extends Application implements IApplication {

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mComponent.inject(this);
    }

    @Override
    public AppComponent getAppComponent() {
        return mComponent;
    }
}
