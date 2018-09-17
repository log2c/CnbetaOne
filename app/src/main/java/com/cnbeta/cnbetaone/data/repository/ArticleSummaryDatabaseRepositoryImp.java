package com.cnbeta.cnbetaone.data.repository;

import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.db.dao.ArticleSummaryDao;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

import javax.inject.Inject;

public class ArticleSummaryDatabaseRepositoryImp implements ArticleSummaryRepository {

    ArticleSummaryDao mArticleSummaryDao;

    @Inject
    public ArticleSummaryDatabaseRepositoryImp(ArticleSummaryDao articleSummaryDao) {
        mArticleSummaryDao = articleSummaryDao;
    }

    @Override
    public List<ArticleSummary> loadOnInit(@Nullable String type) {
        return null;
    }

    @Override
    public List<ArticleSummary> loadBefore(@Nullable String type, Long sid) {
        return null;
    }

    @Override
    public List<ArticleSummary> loadAfter(@Nullable String type, Long sid) {
        return null;
    }
}
