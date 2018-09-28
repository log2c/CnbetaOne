package com.log2c.cnbetaone.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;

import com.log2c.cnbetaone.api.CnbetaApi;
import com.log2c.cnbetaone.data.datasource.ArticleSummaryDataSourceFactory;
import com.log2c.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.log2c.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.log2c.cnbetaone.db.CnbetaDatabase;
import com.log2c.cnbetaone.entity.ArticleSummary;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ArticleListFragmentModel {

    @Binds
    abstract ArticleListFragmentContract.Presenter provideFragmentPresenter(ArticleListFragmentPresenter presenter);

    @Nullable
    @Provides
    static String provideTopic(ArticleListFragment articleListFragment) {
        if (articleListFragment.getArguments() != null) {
            return articleListFragment.getArguments().getString(ArticleListFragment.TOPIC_ID);
        }
        return null;
    }

    @Provides
    static ArticleSummaryDatabaseRepositoryImp provideDatabase(CnbetaDatabase database) {
        return new ArticleSummaryDatabaseRepositoryImp(database.articleSummaryDao());
    }

    @Provides
    static ArticleSummaryServerRepositoryImp provideServer(CnbetaApi cnbetaApi, Application application) {
        return new ArticleSummaryServerRepositoryImp(cnbetaApi, application);
    }

    @Provides
    static ArticleSummaryDataSourceFactory provideArticleDataFactory(@Nullable String topicType, ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository, CnbetaDatabase cnbetaDatabase) {
        return new ArticleSummaryDataSourceFactory(topicType, serverRepository, databaseRepository, cnbetaDatabase.articleSummaryDao());
    }

    @SuppressWarnings("unchecked")
    @Provides
    static LiveData<PagedList<ArticleSummary>> provideArticlePagedList(ArticleSummaryDataSourceFactory factory) {
        return new LivePagedListBuilder(factory, new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build()).build();
    }

}
