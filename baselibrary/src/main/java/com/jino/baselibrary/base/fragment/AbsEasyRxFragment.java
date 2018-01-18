package com.jino.baselibrary.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jino.baselibrary.interfaces.IPresenter;

import javax.inject.Inject;

/**
 * MVP模式的Fragment
 * Created by JINO on 2018/1/18.
 */

public abstract class AbsEasyRxFragment<P extends IPresenter> extends AbsRxFragment {

    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mPresenter != null) {
            mPresenter.onAttach();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
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
