package com.log2c.cnbetaone.data.datasource;

import android.arch.paging.DataSource;
import android.support.annotation.Nullable;

import com.log2c.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.log2c.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.log2c.cnbetaone.db.dao.ArticleSummaryDao;

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
