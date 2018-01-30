package com.jino.jgank.presenter;

import com.jino.baselibrary.di.scope.PreFragment;
import com.jino.baselibrary.presenter.BasePresenter;
import com.jino.baselibrary.rx.BaseObserver;
import com.jino.jgank.contract.GankCategroyContract;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.model.GankCategoryModel;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.transformer.GankItemTransformer;

import java.util.List;

import javax.inject.Inject;

import io.rx_cache2.Reply;
import timber.log.Timber;

/**
 * Created by JINO on 2018/1/23.
 */

@PreFragment
public class GankCategroyPresenter extends BasePresenter<GankCategroyContract.View, GankCategoryModel, GankItemTransformer> implements GankCategroyContract.Presenter {

    private int currentPage = 1;

    private static final int ITEM_COUNT = 10;

    @Inject
    public GankCategroyPresenter(GankCategoryModel model, GankItemTransformer transformer) {
        mModel = model;
        mTransformer = transformer;
    }


    @Override
    public void loadData(String cate) {
        Timber.tag("JINO");
        currentPage = 1;
        mModel.execute(new BaseObserver<Reply<GankResponEntity>>(mView) {
            @Override
            public void onNext(Reply<GankResponEntity> gankResponEntityReply) {
                show(gankResponEntityReply.getData(), false);
            }
        }, new GankCategoryModel.PARAMS(cate, ITEM_COUNT, currentPage));

    }

    @Override
    public void loadMoreData(String cate) {
        mModel.execute(new BaseObserver<Reply<GankResponEntity>>(mView) {
            @Override
            public void onNext(Reply<GankResponEntity> gankResponEntityReply) {
                show(gankResponEntityReply.getData(), true);
            }
        }, new GankCategoryModel.PARAMS(cate, ITEM_COUNT, ++currentPage));
    }

    private void show(GankResponEntity data, boolean loadMore) {
//        if (data.isError()) {
//            mView.onLoadFailed();
//        }
        List<ArticleItem> results = (List<ArticleItem>) mTransformer.transform(data.getResults());

        if (loadMore) {
            mView.loadMore(results);
        } else {
            mView.refresh(results);
        }
    }


}
