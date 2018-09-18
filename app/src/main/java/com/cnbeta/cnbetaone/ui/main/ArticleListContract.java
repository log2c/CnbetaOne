package com.cnbeta.cnbetaone.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

public interface ArticleListContract {
    interface View extends BaseView<Presenter> {

        void initAdapter(LiveData<PagedList<ArticleSummary>> summaryList);

    }

    interface Presenter extends BasePresenter<View> {
    }
}
