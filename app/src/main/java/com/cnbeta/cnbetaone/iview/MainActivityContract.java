package com.cnbeta.cnbetaone.iview;

import com.cnbeta.cnbetaone.base.BasePresenter;
import com.cnbeta.cnbetaone.base.BaseView;

public interface MainActivityContract {

    interface IPresenter extends BasePresenter<IView> {

        void doRequest();

    }

    interface IView extends BaseView<IPresenter> {

    }
}
