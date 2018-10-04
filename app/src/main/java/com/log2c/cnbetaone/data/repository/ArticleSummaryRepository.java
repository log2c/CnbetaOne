package com.log2c.cnbetaone.data.repository;

import android.support.annotation.Nullable;

import com.log2c.cnbetaone.entity.ArticleSummary;

import java.util.List;

public interface ArticleSummaryRepository {

    List<ArticleSummary> loadOnInit(@Nullable String type);

    List<ArticleSummary> loadBefore(@Nullable String type, Long sid);

    List<ArticleSummary> loadAfter(@Nullable String type, Long sid);
}
