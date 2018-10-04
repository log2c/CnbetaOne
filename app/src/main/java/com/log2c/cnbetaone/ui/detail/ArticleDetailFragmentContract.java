package com.log2c.cnbetaone.ui.detail;

import android.content.Context;

import com.log2c.cnbetaone.base.BasePresenter;
import com.log2c.cnbetaone.base.BaseView;
import com.log2c.cnbetaone.entity.ArticleContent;

public interface ArticleDetailFragmentContract {

    interface View extends BaseView<Presenter> {
        void showArgumentsError();

        String getTitle();

        long getSid();

        void loadPage(ArticleContent articleContent);

        void showLoadingView();

        void showReloadView();

        void hideEmptyView();
    }

    interface Presenter extends BasePresenter<View> {
        void reload();

        void openWithBrowser(Context context);
    }
}
