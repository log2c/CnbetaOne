package com.cnbeta.cnbetaone;

import com.cnbeta.cnbetaone.di.component.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
