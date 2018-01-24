package com.jino.baselibrary.di.component;

import android.app.Application;

import com.jino.baselibrary.RequestManager;
import com.jino.baselibrary.base.AppDelegate;
import com.jino.baselibrary.di.module.ApplicationModule;
import com.jino.baselibrary.di.module.LibraryModule;

import java.io.File;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JINO on 2018/1/14.
 */
@Singleton
@Component(modules = {ApplicationModule.class, LibraryModule.class})
public interface AppComponent {
    void inject(AppDelegate appDelegate);

    Application getApplication();

    RequestManager requestManager();

    File getCacheFile();
}
