package com.cnbeta.cnbetaone.data.repository;

import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

public interface ArticleSummaryRepository {

    List<ArticleSummary> loadOnInit(@Nullable String type);

    List<ArticleSummary> loadBefore(@Nullable String type, Long sid);

    List<ArticleSummary> loadAfter(@Nullable String type, Long sid);
}
