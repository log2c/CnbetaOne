package com.log2c.cnbetaone.di.module;


import com.log2c.cnbetaone.di.scope.ActivityScoped;
import com.log2c.cnbetaone.ui.detail.ArticleDetailActivity;
import com.log2c.cnbetaone.ui.detail.ArticleDetailActivityModule;
import com.log2c.cnbetaone.ui.detail.ArticleDetailFragmentModel;
import com.log2c.cnbetaone.ui.main.ArticleListFragmentProvider;
import com.log2c.cnbetaone.ui.main.MainActivity;
import com.log2c.cnbetaone.ui.main.MainActivityModule;
import com.log2c.cnbetaone.ui.setting.SettingsActivity;
import com.log2c.cnbetaone.ui.setting.SettingsActivityModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainActivityModule.class, ArticleListFragmentProvider.class})
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ArticleDetailActivityModule.class, ArticleDetailFragmentModel.class})
    abstract ArticleDetailActivity articleDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SettingsActivityModel.class)
    abstract SettingsActivity settingsActivity();
}
