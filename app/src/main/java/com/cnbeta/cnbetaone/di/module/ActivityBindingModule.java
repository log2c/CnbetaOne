package com.cnbeta.cnbetaone.di.module;


import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.ui.main.ArticleListModel;
import com.cnbeta.cnbetaone.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = ArticleListModel.class)
    abstract MainActivity mainActivity();
}
