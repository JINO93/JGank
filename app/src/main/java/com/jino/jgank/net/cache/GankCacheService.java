package com.jino.jgank.net.cache;

import com.jino.jgank.entity.GankResponEntity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by JINO on 2018/1/22.
 */

public interface GankCacheService {

    @LifeCache(duration = 12, timeUnit = TimeUnit.HOURS)
    Observable<Reply<GankResponEntity>> getCategroyData(Observable<GankResponEntity> oCateData,
                                                        DynamicKey dynamicKey);
}
