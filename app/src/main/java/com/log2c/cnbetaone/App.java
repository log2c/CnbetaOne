package com.log2c.cnbetaone;

import com.facebook.stetho.Stetho;
import com.log2c.cnbetaone.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static App getApp() {
        return mApp;
    }
}
