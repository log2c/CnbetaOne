package com.cnbeta.cnbetaone.ui.detail;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.db.CnbetaDatabase;
import com.cnbeta.cnbetaone.entity.ArticleContent;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleDetailFragmentPresenter implements ArticleDetailFragmentContract.Presenter {
    public static final String TAG = ArticleDetailFragmentPresenter.class.getSimpleName();
    @Nullable
    ArticleDetailFragmentContract.View mView;
    private CnbetaApi mCnbetaApi;
    private long mSid;
    private CnbetaDatabase mCnbetaDatabase;
    private boolean mIsLoaded = false;

    @Inject
    public ArticleDetailFragmentPresenter(CnbetaApi cnbetaApi, CnbetaDatabase cnbetaDatabase) {
        mCnbetaApi = cnbetaApi;
        mCnbetaDatabase = cnbetaDatabase;
    }

    @Override
    public void takeView(@NonNull ArticleDetailFragmentContract.View view) {
        mView = view;
        mSid = view.getSid();
        if (mIsLoaded) {
            return;
        }
        if (mSid == -1) {
            view.showArgumentsError();
            return;
        }
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        if (mView != null) {
            mView.showLoadingView();
        }
        mCnbetaDatabase.articleContentDao().queryBySid(mSid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleContents -> {
                    if (articleContents.isEmpty()) {
                        loadContentFromServer();
                        return;
                    }
                    onContentLoaded(articleContents.get(0));
                }, throwable -> loadContentFromServer());
    }

    private void onContentLoaded(ArticleContent articleContent) {
        mIsLoaded = true;
        if (mView != null) {
            mView.loadPage(articleContent);
            mView.hideEmptyView();
        }
    }

    private void loadContentFromServer() {
        mCnbetaApi.articleContentSign(mSid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CApiObserver<CnbetaBaseResponse<ArticleContent>>() {
                    @Override
                    public void onSuccess(CnbetaBaseResponse<ArticleContent> articleContentCnbetaBaseResponse) {
                        onContentLoaded(articleContentCnbetaBaseResponse.getResult());
                        saveContentToDB(articleContentCnbetaBaseResponse.getResult());
                    }

                    @Override
                    public void onFail(CApiException e) {
                        if (mView != null) {
                            mView.showReloadView();
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void saveContentToDB(ArticleContent result) {
        Flowable.just(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe((n) -> mCnbetaDatabase.articleContentDao().insert(result));
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void reload() {
        if (mView != null) {
            mView.showLoadingView();
        }
        loadContentFromServer();
    }
}
