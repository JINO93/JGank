package com.jino.jgank.di.module;

import android.app.Activity;

import com.jino.baselibrary.RequestManager;
import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.jgank.model.GankCategoryModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JINO on 2018/1/22.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @PreActivity
    @Provides
    public Activity provideActivity() {
        return mActivity;
    }

//    @PreActivity
//    @Provides
//    public GankCategoryModel provideGankModel(RequestManager requestManager){
//        return new GankCategoryModel(requestManager);
//    }
}
