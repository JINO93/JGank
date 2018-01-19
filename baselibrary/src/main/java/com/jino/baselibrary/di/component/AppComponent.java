package com.jino.baselibrary.di.component;

import android.app.Application;

import com.jino.baselibrary.base.IApplication;
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
    void inject(IApplication application);

    Application getApplication();

    File getCacheFile();
}
