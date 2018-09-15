package com.cnbeta.cnbetaone.imodel;

import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.di.scope.FragmentScoped;
import com.cnbeta.cnbetaone.ipresenter.ArticleListFragmentPresenter;
import com.cnbeta.cnbetaone.iview.ArticleListContract;
import com.cnbeta.cnbetaone.ui.fragment.ArticleListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleListModel {

    @ActivityScoped
    @Binds
    abstract ArticleListContract.Presenter provideFragmentPresenter(ArticleListFragmentPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleListFragment articleListFragment();
}
