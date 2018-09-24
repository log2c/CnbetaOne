package com.cnbeta.cnbetaone.di.module;


import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.ui.detail.ArticleDetailActivity;
import com.cnbeta.cnbetaone.ui.detail.ArticleDetailActivityModule;
import com.cnbeta.cnbetaone.ui.detail.ArticleDetailFragmentModel;
import com.cnbeta.cnbetaone.ui.main.ArticleListFragmentProvider;
import com.cnbeta.cnbetaone.ui.main.MainActivity;
import com.cnbeta.cnbetaone.ui.main.MainActivityModule;
import com.cnbeta.cnbetaone.ui.setting.SettingsActivity;
import com.cnbeta.cnbetaone.ui.setting.SettingsActivityModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class, ArticleListFragmentProvider.class})
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ArticleDetailActivityModule.class, ArticleDetailFragmentModel.class})
    abstract ArticleDetailActivity articleDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SettingsActivityModel.class)
    abstract SettingsActivity settingsActivity();
}
