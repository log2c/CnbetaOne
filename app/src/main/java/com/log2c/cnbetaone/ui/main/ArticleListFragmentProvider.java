package com.log2c.cnbetaone.ui.main;

import com.log2c.cnbetaone.di.scope.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleListFragmentProvider {
    @FragmentScoped
    @ContributesAndroidInjector(modules = ArticleListFragmentModel.class)
    abstract ArticleListFragment provideArticleListFragment();
}
