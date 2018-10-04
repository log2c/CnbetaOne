package com.log2c.cnbetaone.ui.main;

import com.log2c.cnbetaone.base.BasePresenter;
import com.log2c.cnbetaone.base.BaseView;

public interface MainActivityContract {

    interface Presenter extends BasePresenter<View> {

    }

    interface View extends BaseView<Presenter> {

    }
}
