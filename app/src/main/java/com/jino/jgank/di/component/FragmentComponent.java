package com.jino.jgank.di.component;

import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.di.scope.PreFragment;
import com.jino.jgank.view.fragment.GankArticleFragment;
import com.jino.jgank.view.fragment.GankCategroyFragment;

import dagger.Component;

/**
 * Created by JINO on 2018/1/23.
 */
@PreFragment
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(GankCategroyFragment fragment);
}
