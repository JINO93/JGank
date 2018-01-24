package com.jino.baselibrary;

import com.jino.baselibrary.di.scope.PreFragment;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import io.rx_cache2.internal.RxCache;
import retrofit2.Retrofit;

/**
 * Created by JINO on 2018/1/19.
 */
@Singleton
public class RequestManager {

    private Lazy<Retrofit> retrofitLazy;
    private Lazy<RxCache> rxCacheLazy;

    private ConcurrentMap<String, Object> retrofitCache;
    private ConcurrentMap<String, Object> rxCacheCache;

    private Object retrofitLock = new Object();
    private Object rxCacheLock = new Object();

    @Inject
    public RequestManager(Lazy<Retrofit> retrofitLazy,Lazy<RxCache> rxCacheLazy) {
        this.retrofitLazy = retrofitLazy;
        this.rxCacheLazy = rxCacheLazy;

        retrofitCache = new ConcurrentHashMap<>();
        rxCacheCache = new ConcurrentHashMap<>();
    }


    public <T> T getRetrofitService(Class<T> service) {
        T retrofitService;
        synchronized (retrofitLock) {
            retrofitService = (T) retrofitCache.get(service.getCanonicalName());
            if (retrofitService == null) {
                retrofitService = retrofitLazy.get().create(service);
                retrofitCache.put(service.getCanonicalName(), retrofitService);
            }
        }
        return retrofitService;
    }


    public <T> T getCacheService(Class<T> service) {
        T retrofitService;
        synchronized (rxCacheLock) {
            retrofitService = (T) rxCacheCache.get(service.getCanonicalName());
            if (retrofitService == null) {
                retrofitService = rxCacheLazy.get().using(service);
                rxCacheCache.put(service.getCanonicalName(), retrofitService);
            }
        }
        return retrofitService;
    }
}
