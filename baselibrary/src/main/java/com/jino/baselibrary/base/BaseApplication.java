package com.jino.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.jino.baselibrary.base.activity.IActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.component.DaggerAppComponent;
import com.jino.baselibrary.di.module.ApplicationModule;

/**
 * Created by JINO on 2018/1/19.
 */

public class BaseApplication extends Application implements IApplication, Application.ActivityLifecycleCallbacks {

    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        mComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mComponent.inject(this);
    }

    @Override
    public AppComponent getAppComponent() {
        return mComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity != null && activity instanceof IActivity) {
            ((IActivity) activity).injectComponent(mComponent);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
