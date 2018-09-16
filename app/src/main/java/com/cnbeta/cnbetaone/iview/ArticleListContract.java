package com.cnbeta.cnbetaone.iview;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

public interface ArticleListContract {
    interface View extends BaseView<Presenter> {

        void initAdapter(List<ArticleSummary> summaryList);

        void notifyDataSetChanged();

    }

    interface Presenter extends BasePresenter<View> {
    }
}
