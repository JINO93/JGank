package com.jino.jgank.model;

import com.jino.baselibrary.RequestManager;
import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.baselibrary.model.BaseModel;
import com.jino.jgank.entity.GankSearchEntity;
import com.jino.jgank.net.GankService;
import com.jino.jgank.net.cache.GankCacheService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.Reply;

/**
 * Created by JINO on 2018/2/5.
 */
@PreActivity
public class GankSearchModel extends BaseModel<Reply<GankSearchEntity>, GankSearchModel.PARAMS> {

    @Inject
    public GankSearchModel(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    protected Observable<Reply<GankSearchEntity>> buildObservable(PARAMS params) {
        return mRequestManager.getCacheService(GankCacheService.class)
                .search(mRequestManager.getRetrofitService(GankService.class)
                                .search(params.keyword, params.count, params.page)
                        , new DynamicKey(params.keyword)).takeLast(2, TimeUnit.SECONDS);
    }

    public static class PARAMS {
        private String keyword;
        private int page;
        private int count;

        public PARAMS(String keyword, int page, int count) {
            this.keyword = keyword;
            this.page = page;
            this.count = count;
        }
    }
}
