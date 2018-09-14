package com.cnbeta.cnbetaone.di.module;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network Module
 */
@Module
public class NetworkModule {
    public static class Config {
        public static final int HTTP_CACHE_SIZE = 20 * 1024 * 1024;
        public static final int HTTP_CONNECT_TIMEOUT = 15 * 1000;
        public static final int HTTP_READ_TIMEOUT = 20 * 1000;

    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(HttpUrl baseURL) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
