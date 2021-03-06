package com.log2c.cnbetaone.ui.detail;

import com.log2c.cnbetaone.di.scope.ActivityScoped;
import com.log2c.cnbetaone.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleDetailFragmentModel {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleDetailFragment addDetailFragment();

    @ActivityScoped
    @Binds
    abstract ArticleDetailFragmentContract.Presenter provideDetailFragmentPresenter(ArticleDetailFragmentPresenter presenter);

}
