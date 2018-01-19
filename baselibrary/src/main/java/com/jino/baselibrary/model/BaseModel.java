package com.jino.baselibrary.model;

import com.jino.baselibrary.utils.ConditionUtils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JINO on 2018/1/19.
 */

public abstract class BaseModel<T, PARAMS> implements IModel<T, PARAMS> {

    private CompositeDisposable disposables;


    public BaseModel() {
        disposables = new CompositeDisposable();
    }

    protected abstract Observable<T> buildObservable(PARAMS params);

    @Override
    public void execute(DisposableObserver<T> observer, PARAMS params) {
        ConditionUtils.checkNotNull(observer, "observer can not be null.");
        Observable<T> observable = buildObservable(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        ConditionUtils.checkNotNull(observable, "no observable generated.");
        addDisposable(observable.subscribeWith(observer));
    }

    @Override
    public void addDisposable(Disposable disposable) {
        ConditionUtils.checkNotNull(disposable, "can not add null");
        disposables.add(disposable);
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
