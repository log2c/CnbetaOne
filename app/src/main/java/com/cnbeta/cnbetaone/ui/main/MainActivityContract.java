package com.cnbeta.cnbetaone.ui.main;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;

public interface MainActivityContract {

    interface Presenter extends BasePresenter<View> {

    }

    interface View extends BaseView<Presenter> {

    }
}
