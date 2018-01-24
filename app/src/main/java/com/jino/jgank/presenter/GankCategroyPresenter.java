package com.jino.jgank.presenter;

import com.jino.baselibrary.di.scope.PreFragment;
import com.jino.baselibrary.presenter.BasePresenter;
import com.jino.jgank.contract.GankCategroyContract;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.model.GankCategoryModel;
import com.jino.jgank.net.GankService;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import io.rx_cache2.Reply;
import timber.log.Timber;

/**
 * Created by JINO on 2018/1/23.
 */

@PreFragment
public class GankCategroyPresenter extends BasePresenter<GankCategroyContract.View, GankCategoryModel> {

    @Inject
    public GankCategroyPresenter(GankCategoryModel model) {
        mModel = model;
    }

    public void loadData() {
        Timber.tag("JINO");
        mModel.execute(new DisposableObserver<Reply<GankResponEntity>>() {
            @Override
            public void onNext(Reply<GankResponEntity> gankResponEntityReply) {
                Timber.d(gankResponEntityReply.toString());
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e);
            }

            @Override
            public void onComplete() {
                Timber.i("request finish.");
            }
        }, new GankCategoryModel.PARAMS(GankService.CATEGROY_ALL, 10, 1));
    }

}
