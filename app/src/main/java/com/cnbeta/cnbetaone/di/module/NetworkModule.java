package com.cnbeta.cnbetaone.di.module;


import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.network.CApiConverterFactory;
import com.cnbeta.cnbetaone.network.CnbetaApiInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
    public Retrofit provideRetrofit(HttpUrl baseURL, Gson gson) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Config.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new CnbetaApiInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(Config.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(CApiConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public CnbetaApi provideCnbetaApi(Retrofit retrofit) {
        return retrofit.create(CnbetaApi.class);
    }

    /**
     * 提供设置时间戳格式的 GSON
     *
     * @return gson
     * @see com.cnbeta.cnbetaone.entity.ArticleSummary
     * pubtime : 2018-09-14 23:00:24
     */
    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    }

}
