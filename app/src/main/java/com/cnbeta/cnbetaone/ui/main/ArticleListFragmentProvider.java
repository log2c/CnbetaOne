package com.cnbeta.cnbetaone.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleListFragmentProvider {

    @ContributesAndroidInjector(modules = ArticleListFragmentModel.class)
    abstract ArticleListFragment provideArticleListFragment();
}
