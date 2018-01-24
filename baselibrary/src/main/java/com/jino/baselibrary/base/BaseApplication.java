package com.jino.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.jino.baselibrary.di.component.AppComponent;

/**
 * Created by JINO on 2018/1/19.
 */

public abstract class BaseApplication extends Application implements IApplication{

    protected AppDelegate mDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        if(mDelegate == null){
            mDelegate = new AppDelegate();
        }
        mDelegate.onCreate(this);
    }

    @Override
    public AppComponent getAppComponent() {
        return mDelegate.getAppComponent();
    }

}
