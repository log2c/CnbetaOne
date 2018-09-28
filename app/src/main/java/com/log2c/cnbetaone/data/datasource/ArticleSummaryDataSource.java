package com.log2c.cnbetaone.data.datasource;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.log2c.cnbetaone.data.repository.ArticleSummaryDatabaseRepositoryImp;
import com.log2c.cnbetaone.data.repository.ArticleSummaryRepository;
import com.log2c.cnbetaone.data.repository.ArticleSummaryServerRepositoryImp;
import com.log2c.cnbetaone.db.dao.ArticleSummaryDao;
import com.log2c.cnbetaone.entity.ArticleSummary;

import java.util.List;

public class ArticleSummaryDataSource extends ItemKeyedDataSource<Long, ArticleSummary> {
    private static final String TAG = ArticleSummaryDataSource.class.getSimpleName();

    @Nullable
    private String mSidType;
    ArticleSummaryRepository mServerRepository;
    ArticleSummaryRepository mDatabaseRepository;
    ArticleSummaryDao mArticleSummaryDao;

    public ArticleSummaryDataSource(@Nullable String sidType, ArticleSummaryServerRepositoryImp serverRepository, ArticleSummaryDatabaseRepositoryImp databaseRepository, ArticleSummaryDao summaryDao) {
        mArticleSummaryDao = summaryDao;
        this.mSidType = sidType;
        mServerRepository = serverRepository;
        mDatabaseRepository = databaseRepository;
        if (mSidType == null) {
            mSidType = "null";
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<ArticleSummary> callback) {
        List<ArticleSummary> serverData = mServerRepository.loadOnInit(mSidType);
        mArticleSummaryDao.insertIgnore(serverData.toArray(new ArticleSummary[serverData.size()]));
        List<ArticleSummary> dbData = mDatabaseRepository.loadOnInit(mSidType);
        callback.onResult(dbData);
    }

    // 加载旧数据
    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<ArticleSummary> callback) {
        List<ArticleSummary> serverData = mServerRepository.loadAfter(mSidType, params.key);    // 先从服务器加载
        mArticleSummaryDao.insertIgnore(serverData.toArray(new ArticleSummary[serverData.size()]));   // 保存到数据库中
        List<ArticleSummary> dbData = mDatabaseRepository.loadAfter(mSidType, params.key);
        callback.onResult(dbData);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<ArticleSummary> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull ArticleSummary item) {
        return item.getSid();
    }
}
