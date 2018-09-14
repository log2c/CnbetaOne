package com.cnbeta.cnbetaone.di.module;


import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.imodel.ArticleListModel;
import com.cnbeta.cnbetaone.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
//    @ActivityScoped
//    @ContributesAndroidInjector(modules = PortalPresenterModule.class)
//    abstract PortalActivity portalActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ArticleListModel.class)
    abstract MainActivity mainActivity();
}
