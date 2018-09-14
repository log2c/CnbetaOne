package com.cnbeta.cnbetaone.imodel;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.ui.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ArticleListModel {
    public static final String ARTICLE_TYPE = "article_type";

    @Provides
    @ActivityScoped
    @Nullable
    static String provideArticleType(MainActivity activity) {
        return activity.getIntent().getStringExtra(ARTICLE_TYPE);
    }

}
