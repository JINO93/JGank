package com.jino.baselibrary.model;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by JINO on 2018/1/19.
 */

public interface IModel<T,PARAMS> {

    void execute(DisposableObserver<T> observer,PARAMS params);

    void addDisposable(Disposable disposable);

    void dispose();
}
