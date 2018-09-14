package com.cnbeta.cnbetaone.di.module;

import com.cnbeta.cnbetaone.Constants;

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
