package com.cnbeta.cnbetaone.data.datasource;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryRepository;
import com.cnbeta.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

public class ArticleSummaryDataSource extends ItemKeyedDataSource<Long, ArticleSummary> {
    private static final String TAG = ArticleSummaryDataSource.class.getSimpleName();

    @Nullable
    private String mSidType;
    ArticleSummaryRepository mServerRepository;
    ArticleSummaryRepository mDatabaseRepository;

    public ArticleSummaryDataSource(@Nullable String sidType, ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository) {
        this.mSidType = sidType;
        mServerRepository = serverRepository;
        mDatabaseRepository = databaseRepository;
        if (mSidType == null) {
            mSidType = "null";
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<ArticleSummary> callback) {
        callback.onResult(mServerRepository.loadOnInit(mSidType));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<ArticleSummary> callback) {
        List<ArticleSummary> serverData = mServerRepository.loadAfter(mSidType, params.key);
        callback.onResult(serverData);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<ArticleSummary> callback) {
        List<ArticleSummary> serverData = mServerRepository.loadBefore(mSidType, params.key);
        callback.onResult(serverData);
    }

    @NonNull
    @Override
    public Long getKey(@NonNull ArticleSummary item) {
        return item.getSid();
    }
}
