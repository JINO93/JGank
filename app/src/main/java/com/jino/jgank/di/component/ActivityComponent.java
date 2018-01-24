package com.jino.jgank.di.component;

import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.jgank.di.module.ActivityModule;
import com.jino.jgank.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by JINO on 2018/1/22.
 */
@PreActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
