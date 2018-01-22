package com.jino.jgank.model;

import com.jino.baselibrary.RequestManager;
import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.baselibrary.model.BaseModel;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.net.GankService;
import com.jino.jgank.net.cache.GankCacheService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * Created by JINO on 2018/1/22.
 */
@PreActivity
public class GankCategoryModel extends BaseModel<Reply<GankResponEntity>, GankCategoryModel.PARAMS> {

    @Inject
    public GankCategoryModel(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    protected Observable<Reply<GankResponEntity>> buildObservable(PARAMS params) {
        return mRequestManager.getCacheService(GankCacheService.class)
                .getCategroyData(mRequestManager.getRetrofitService(GankService.class)
                        .getCategroyData(params.cate, params.num, params.page), null);
    }

    public static class PARAMS {
        private String cate;
        private int num;
        private int page;

        public PARAMS(String cate, int num, int page) {
            this.cate = cate;
            this.num = num;
            this.page = page;
        }
    }
}
