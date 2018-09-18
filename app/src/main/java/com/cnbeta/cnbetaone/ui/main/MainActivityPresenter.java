package com.cnbeta.cnbetaone.ui.main;

import android.util.Log;

import javax.inject.Inject;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    public static final String TAG = MainActivityPresenter.class.getSimpleName();

    @Inject
    public MainActivityPresenter() {
        Log.i(TAG, "MainActivityPresenter");
    }

    @Override
    public void takeView(MainActivityContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
