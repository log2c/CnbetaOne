package com.cnbeta.cnbetaone.ui.detail;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;
import com.cnbeta.cnbetaone.entity.ArticleContent;

public interface ArticleDetailFragmentContract {

    interface View extends BaseView<Presenter> {
        void showArgumentsError();

        String getTopicId();

        long getSid();

        void loadPage(ArticleContent articleContent);

        void showLoadingView();

        void showReloadView();

        void hideEmptyView();
    }

    interface Presenter extends BasePresenter<View> {
        void reload();
    }
}
