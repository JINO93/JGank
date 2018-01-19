package com.jino.baselibrary.di.module;

import android.app.Application;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JINO on 2018/1/19.
 */
@Module
public class LibraryModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient httpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient);
        return builder.build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    @Singleton
    @Provides
    public RxCache provideRxCache(File cacheDir){
        RxCache.Builder builder = new RxCache.Builder();
        return builder.persistence(cacheDir, new GsonSpeaker());
    }

    @Singleton
    @Provides
    public File provideCacheDir(Application application){
        return application.getCacheDir();
    }

}
