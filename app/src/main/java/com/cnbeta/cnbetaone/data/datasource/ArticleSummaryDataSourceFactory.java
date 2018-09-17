package com.cnbeta.cnbetaone.data.datasource;

import android.arch.paging.DataSource;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;

public class ArticleSummaryDataSourceFactory extends DataSource.Factory {
    ArticleSummaryServerRepositoryImp mServerRepository;
    ArticleSummaryDatabaseRepositoryImp mDatabaseRepository;
    @Nullable
    String mTopicId;

    public ArticleSummaryDataSourceFactory(@Nullable String topicId, ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository) {
        mServerRepository = serverRepository;
        mDatabaseRepository = databaseRepository;
        mTopicId = topicId;
    }

    @Override
    public DataSource create() {
        return new ArticleSummaryDataSource(mTopicId, mServerRepository, mDatabaseRepository);
    }
}
