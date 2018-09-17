package com.cnbeta.cnbetaone.data.datasource;

import android.arch.paging.DataSource;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.cnbeta.cnbetaone.db.dao.ArticleSummaryDao;

public class ArticleSummaryDataSourceFactory extends DataSource.Factory {
    ArticleSummaryServerRepositoryImp mServerRepository;
    ArticleSummaryDatabaseRepositoryImp mDatabaseRepository;
    ArticleSummaryDao mArticleSummaryDao;
    @Nullable
    String mTopicId;

    public ArticleSummaryDataSourceFactory(@Nullable String topicId, ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository, ArticleSummaryDao summaryDao) {
        mServerRepository = serverRepository;
        mDatabaseRepository = databaseRepository;
        mTopicId = topicId;
        mArticleSummaryDao = summaryDao;
    }

    @Override
    public DataSource create() {
        return new ArticleSummaryDataSource(mTopicId, mServerRepository, mDatabaseRepository, mArticleSummaryDao);
    }
}
