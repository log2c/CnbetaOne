package com.log2c.cnbetaone.data.repository;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.log2c.cnbetaone.db.dao.ArticleSummaryDao;
import com.log2c.cnbetaone.entity.ArticleSummary;

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
        if (TextUtils.isEmpty(type) || type.equals("null")) {   // 全部 topic
            return mArticleSummaryDao.loadAllLimit().blockingFirst();
        }
        return mArticleSummaryDao.loadByTopicLimit(type).blockingFirst();
    }

    @Override
    public List<ArticleSummary> loadBefore(@Nullable String type, Long sid) {
        if (TextUtils.isEmpty(type) || type.equals("null")) {   // 全部 topic
            List<ArticleSummary> articleSummaries = mArticleSummaryDao.queryAfterBySid(sid).blockingFirst();
            return articleSummaries;
        }
        return mArticleSummaryDao.queryBeforeSid(sid, type).blockingFirst();
    }

    @Override
    public List<ArticleSummary> loadAfter(@Nullable String type, Long sid) {
        if (TextUtils.isEmpty(type) || type.equals("null")) {   // 全部 topic
            List<ArticleSummary> articleSummaries = mArticleSummaryDao.queryBeforeBySid(sid).blockingFirst();
            return articleSummaries;
        }
        return mArticleSummaryDao.queryAfterSid(sid, type).blockingFirst();
    }
}
