package com.jino.baselibrary.di.module;

import android.app.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by JINO on 2018/1/19.
 */
@Module
public class LibraryModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient httpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gank.io/api/")
                .client(httpClient);
        return builder.build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logInterceptor);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        return builder.build();
    }

    @Singleton
    @Provides
    RxCache provideRxCache(File cacheDir) {
        RxCache.Builder builder = new RxCache.Builder();
        return builder.persistence(cacheDir, new GsonSpeaker());
    }

    @Singleton
    @Provides
    File provideCacheDir(Application application) {
        return application.getCacheDir();
    }

}
