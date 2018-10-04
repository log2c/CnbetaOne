package com.log2c.cnbetaone.di.module;

import com.log2c.cnbetaone.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

/**
 * Gank API Module
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse(Constants.CnbetaUrl.BASE_URL);
    }
}
