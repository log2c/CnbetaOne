package com.log2c.cnbetaone.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.log2c.cnbetaone.base.BasePresenter;
import com.log2c.cnbetaone.base.BaseView;
import com.log2c.cnbetaone.entity.ArticleSummary;

public interface ArticleListFragmentContract {
    interface View extends BaseView<Presenter> {

        void initAdapter(LiveData<PagedList<ArticleSummary>> summaryList);

        void onDataLoaded();

        void showEmptyView();

        void hideEmptyView();
    }

    interface Presenter extends BasePresenter<View> {
        void reloadData(LifecycleOwner lifecycleOwner);
    }
}
