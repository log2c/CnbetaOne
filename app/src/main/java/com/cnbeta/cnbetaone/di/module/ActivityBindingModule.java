package com.cnbeta.cnbetaone.di.module;


import com.cnbeta.cnbetaone.ui.main.ArticleListFragmentProvider;
import com.cnbeta.cnbetaone.ui.main.MainActivity;
import com.cnbeta.cnbetaone.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class, ArticleListFragmentProvider.class})
    abstract MainActivity mainActivity();
}
