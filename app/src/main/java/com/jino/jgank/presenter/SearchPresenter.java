package com.jino.jgank.presenter;

import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.baselibrary.presenter.BasePresenter;
import com.jino.baselibrary.rx.BaseObserver;
import com.jino.jgank.contract.SearchContract;
import com.jino.jgank.entity.GankSearchEntity;
import com.jino.jgank.model.GankSearchModel;
import com.jino.jgank.transformer.SearchItemTransformer;

import javax.inject.Inject;

import io.rx_cache2.Reply;

/**
 * Created by JINO on 2018/2/5.
 */

@PreActivity
public class SearchPresenter extends BasePresenter<SearchContract.View, GankSearchModel, SearchItemTransformer> implements SearchContract.Presenter {

    private int curPage = 1;

    @Inject
    public SearchPresenter(GankSearchModel model, SearchItemTransformer transformer) {
        mModel = model;
        mTransformer = transformer;
    }

    @Override
    public void searchKeyword(String kw) {
        curPage = 1;
        mModel.execute(new BaseObserver<Reply<GankSearchEntity>>(mView) {
            @Override
            public void onNext(Reply<GankSearchEntity> gankSearchEntityReply) {
                GankSearchEntity data = gankSearchEntityReply.getData();
                if (data.getCount() <= 0) {
                    mView.showEmpty();
                } else {
                    mView.showResult(mTransformer.transform(data.getResults()));
                }
            }
        }, new GankSearchModel.PARAMS(kw, curPage, 10));
    }

    @Override
    public void getMoreData(String kw) {
        mModel.execute(new BaseObserver<Reply<GankSearchEntity>>(mView) {
            @Override
            public void onNext(Reply<GankSearchEntity> gankSearchEntityReply) {
                GankSearchEntity data = gankSearchEntityReply.getData();
                if (data.getCount() <= 0) {
                    mView.noMoreData();
                } else {
                    mView.showMore(mTransformer.transform(data.getResults()));
                }
            }
        }, new GankSearchModel.PARAMS(kw, ++curPage, 10));
    }
}
