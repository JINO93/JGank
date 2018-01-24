package com.jino.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.jino.baselibrary.base.activity.IActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by JINO on 2018/1/23.
 */
@Singleton
public class AppLifeCallback implements Application.ActivityLifecycleCallbacks {

    @Inject
    public AppLifeCallback() {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity != null && activity instanceof IActivity) {
            ((IActivity) activity).injectComponent(((IApplication) activity.getApplication()).getAppComponent());
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
