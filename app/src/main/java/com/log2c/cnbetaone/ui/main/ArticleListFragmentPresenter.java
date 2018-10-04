package com.log2c.cnbetaone.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.log2c.cnbetaone.entity.ArticleSummary;

import javax.inject.Inject;

public class ArticleListFragmentPresenter implements ArticleListFragmentContract.Presenter {
    private static final String TAG = ArticleListFragmentPresenter.class.getSimpleName();
    @Nullable
    private String mTopicType;
    @Nullable
    private ArticleListFragmentContract.View mView;
    private LiveData<PagedList<ArticleSummary>> mArticleSummaryList;

    private boolean isInit = false;

    @Inject
    public ArticleListFragmentPresenter(@Nullable String topicType, LiveData<PagedList<ArticleSummary>> pagedListLiveData) {
        mTopicType = topicType;
        mArticleSummaryList = pagedListLiveData;
    }

    @Override
    public void takeView(@NonNull ArticleListFragmentContract.View view) {
        mView = view;
        if (isInit) {
            return;
        }
        if (mArticleSummaryList.getValue() != null) {
            if (mArticleSummaryList.getValue().isEmpty()) {
                mView.showEmptyView();
            } else {
                mView.hideEmptyView();
            }
        }
        mView.initAdapter(mArticleSummaryList);
        isInit = true;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void reloadData(LifecycleOwner lifecycleOwner) {
        if (mArticleSummaryList.getValue() == null) {
            return;
        }
        mArticleSummaryList.getValue().getDataSource().invalidate();
        Observer<PagedList<ArticleSummary>> observer = new Observer<PagedList<ArticleSummary>>() {
            @Override
            public void onChanged(@Nullable PagedList<ArticleSummary> articleSummaries) {
                unBind();
                if (mView != null) {
                    if (articleSummaries != null && !articleSummaries.isEmpty()) {
                        mView.hideEmptyView();
                    } else {
                        mView.showEmptyView();
                    }
                    mView.onDataLoaded();
                }
            }

            private void unBind() {
                mArticleSummaryList.removeObserver(this);
            }
        };
        mArticleSummaryList.observe(lifecycleOwner, observer);
    }
}
