package com.cnbeta.cnbetaone.iview;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;

public interface ArticleListContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {
    }
}
