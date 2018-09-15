package com.cnbeta.cnbetaone.ipresenter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.db.CnbetaDatabase;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.iview.MainActivityContract;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.IPresenter {
    public static final String TAG = MainActivityPresenter.class.getSimpleName();
    @Nullable
    private String mArticleType;
    private CnbetaApi mCnbetaApi;
    private CnbetaDatabase mCnbetaDatabase;

    @Inject
    public MainActivityPresenter(CnbetaApi cnbetaApi, CnbetaDatabase cnbetaDatabase) {
        Log.i(TAG, "MainActivityPresenter");
        mCnbetaApi = cnbetaApi;
        mCnbetaDatabase = cnbetaDatabase;
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
//                .subscribe(res -> {
//                }, e -> Log.e(TAG, "doRequest: ", e));
                .subscribe(new CApiObserver<CnbetaBaseResponse<List<ArticleSummary>>>() {
                    @Override
                    public void onSuccess(CnbetaBaseResponse<List<ArticleSummary>> listCnbetaBaseResponse) {
                        if (listCnbetaBaseResponse.getResult().size() == 0)
                            return;
                        ArticleSummary[] summaries = new ArticleSummary[listCnbetaBaseResponse.getResult().size()];
                        listCnbetaBaseResponse.getResult().toArray(summaries);
                        Completable.fromAction(() -> mCnbetaDatabase.articleSummaryDao()
                                .insert(summaries))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() ->
                                                Log.i(TAG, "insert success")
                                        , e -> Log.e(TAG, "insert exception ", e));
                    }

                    @Override
                    public void onFail(CApiException e) {
                        Log.i(TAG, "onFail: " + e.getMessage());
                    }
                });
    }
}
