package com.cnbeta.cnbetaone.di.component;

import android.app.Application;

import com.cnbeta.cnbetaone.App;
import com.cnbeta.cnbetaone.di.module.ActivityBindingModule;
import com.cnbeta.cnbetaone.di.module.ApiServiceModule;
import com.cnbeta.cnbetaone.di.module.ApplicationModule;
import com.cnbeta.cnbetaone.di.module.DatabaseModule;
import com.cnbeta.cnbetaone.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ActivityBindingModule.class,
                AndroidSupportInjectionModule.class,
                ApiServiceModule.class,
                NetworkModule.class,
                DatabaseModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
