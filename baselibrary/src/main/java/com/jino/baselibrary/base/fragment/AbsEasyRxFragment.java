package com.jino.baselibrary.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jino.baselibrary.base.IApplication;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.presenter.IPresenter;

import javax.inject.Inject;

/**
 * MVP模式的Fragment
 * Created by JINO on 2018/1/18.
 */

public abstract class AbsEasyRxFragment<P extends IPresenter> extends AbsRxFragment {

    @Inject
    protected P mPresenter;

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
        inject(((IApplication) mContext).getAppComponent());
    }

    protected abstract void inject(AppComponent appComponent);

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
