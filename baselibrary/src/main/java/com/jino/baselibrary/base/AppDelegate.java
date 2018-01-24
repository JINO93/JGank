package com.jino.baselibrary.base;

import android.app.Application;

import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.component.DaggerAppComponent;
import com.jino.baselibrary.di.module.ApplicationModule;
import com.jino.baselibrary.di.module.LibraryModule;
import com.jino.baselibrary.utils.ConditionUtils;

import javax.inject.Inject;

//import com.jino.baselibrary.di.component.DaggerAppComponent;

/**
 * Created by JINO on 2018/1/23.
 */

public class AppDelegate implements IApplication {

    private Application mApplication;
    private AppComponent appComponent;
    @Inject
    public AppLifeCallback mLifeCallback;

    public void onCreate(Application application) {
        mApplication = application;

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(mApplication))
                .libraryModule(new LibraryModule())
                .build();
        appComponent.inject(this);

        mApplication.registerActivityLifecycleCallbacks(mLifeCallback);
    }

    @Override
    public AppComponent getAppComponent() {
        ConditionUtils.checkNotNull(appComponent, "app component can not be null.");
        return appComponent;
    }
}
