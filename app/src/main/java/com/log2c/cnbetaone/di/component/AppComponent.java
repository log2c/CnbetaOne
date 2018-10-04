package com.log2c.cnbetaone.di.component;

import android.app.Application;

import com.log2c.cnbetaone.App;
import com.log2c.cnbetaone.di.module.ActivityBindingModule;
import com.log2c.cnbetaone.di.module.ApiServiceModule;
import com.log2c.cnbetaone.di.module.ApplicationModule;
import com.log2c.cnbetaone.di.module.DatabaseModule;
import com.log2c.cnbetaone.di.module.NetworkModule;

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
