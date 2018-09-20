package com.cnbeta.cnbetaone.ui.detail;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;

public interface ArticleDetailFragmentContract {

    interface View extends BaseView<Presenter> {
        void showArgumentsError();

        String getTopicId();

        long getSid();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
