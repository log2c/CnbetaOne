package com.cnbeta.cnbetaone.data.repository;

import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

import javax.inject.Inject;

public class ArticleSummaryServerRepositoryImp implements ArticleSummaryRepository {
    CnbetaApi mCnbetaApi;

    @Inject
    public ArticleSummaryServerRepositoryImp(CnbetaApi cnbetaApi) {
        mCnbetaApi = cnbetaApi;
    }

    @Override
    public List<ArticleSummary> loadOnInit(@Nullable String type) {
        if (type == null) {
            return mCnbetaApi.articlesSign().blockingFirst().getResult();
        }
        return mCnbetaApi.topicArticlesSign(type)
                .blockingFirst().getResult();
    }

    @Override
    public List<ArticleSummary> loadBefore(@Nullable String type, Long sid) {
        return mCnbetaApi.newArticlesSign(type, sid)
                .blockingFirst().getResult();
    }

    @Override
    public List<ArticleSummary> loadAfter(@Nullable String type, Long sid) {
        return mCnbetaApi.oldArticlesSign(type, sid)
                .blockingFirst().getResult();
    }
}
