package com.jino.baselibrary.di.module;

import android.app.Application;

import com.jino.baselibrary.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JINO on 2018/1/19.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }


//    @Singleton
//    @Provides
//    public RequestManager provideRequestManager(RequestManager requestManager) {
//        return requestManager;
//    }
}
