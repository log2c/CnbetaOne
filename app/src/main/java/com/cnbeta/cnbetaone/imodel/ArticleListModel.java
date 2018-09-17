package com.cnbeta.cnbetaone.imodel;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.data.datasource.ArticleSummaryDataSourceFactory;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.cnbeta.cnbetaone.db.CnbetaDatabase;
import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.di.scope.FragmentScoped;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.ipresenter.ArticleListFragmentPresenter;
import com.cnbeta.cnbetaone.iview.ArticleListContract;
import com.cnbeta.cnbetaone.ui.fragment.ArticleListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleListModel {

    @ActivityScoped
    @Binds
    abstract ArticleListContract.Presenter provideFragmentPresenter(ArticleListFragmentPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleListFragment articleListFragment();

    @Provides
    @ActivityScoped
    static ArticleSummaryDatabaseRepositoryImp provideDatabase(CnbetaDatabase database) {
        return new ArticleSummaryDatabaseRepositoryImp(database.articleSummaryDao());
    }

    @Provides
    @ActivityScoped
    static ArticleSummaryServerRepositoryImp provideServer(CnbetaApi cnbetaApi) {
        return new ArticleSummaryServerRepositoryImp(cnbetaApi);
    }

    @Provides
    @ActivityScoped
    static ArticleSummaryDataSourceFactory provideArticleDataFactory(ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository) {
        return new ArticleSummaryDataSourceFactory(null, serverRepository, databaseRepository);
    }

    @Provides
    @ActivityScoped
    static LiveData<PagedList<ArticleSummary>> provideArticlePagedList(ArticleSummaryDataSourceFactory factory) {
        return new LivePagedListBuilder(factory, new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()).build();
    }

}
