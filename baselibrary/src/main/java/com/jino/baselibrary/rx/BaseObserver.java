package com.jino.baselibrary.rx;

import com.jino.baselibrary.interfaces.IView;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by JINO on 2018/1/25.
 */

public abstract class BaseObserver<T> extends DisposableObserver<T> {

    private IView mView;

    public BaseObserver(IView mView) {
        this.mView = mView;
    }

    @Override
    protected void onStart() {
        if (mView != null) {
            mView.onLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e);
        if (mView != null) {
            mView.onLoadFailed();
        }
    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.onLoadSucceed();
        }
    }
}
