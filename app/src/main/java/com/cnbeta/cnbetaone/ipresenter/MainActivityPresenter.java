package com.cnbeta.cnbetaone.ipresenter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.iview.MainActivityContract;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.IPresenter {
    public static final String TAG = MainActivityPresenter.class.getSimpleName();
    @Nullable
    private String mArticleType;
    private CnbetaApi mCnbetaApi;

    @Inject
    public MainActivityPresenter(@Nullable String articleType, CnbetaApi cnbetaApi) {
        Log.i(TAG, "MainActivityPresenter");
        mArticleType = articleType;
        mCnbetaApi = cnbetaApi;
    }

    @Override
    public void takeView(MainActivityContract.IView view) {

    }

    @Override
    public void dropView() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void doRequest() {
        mCnbetaApi.articlesSign()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(res -> Log.i(TAG, "doRequest: " + res.toString()), e -> Log.e(TAG, "doRequest: ", e));
    }
}
