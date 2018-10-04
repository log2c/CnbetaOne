package com.log2c.cnbetaone.ui.setting;

import com.log2c.cnbetaone.di.scope.ActivityScoped;
import com.log2c.cnbetaone.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingsActivityModel {
    @ActivityScoped
    @Binds
    abstract SettingsActivityContract.Presenter providePresenter(SettingsActivityPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SettingsFragment settingsFragment();
}
