package com.cnbeta.cnbetaone.ui.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.db.CnbetaDatabase;
import com.cnbeta.cnbetaone.entity.ArticleContent;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleDetailFragmentPresenter implements ArticleDetailFragmentContract.Presenter {
    public static final String TAG = ArticleDetailFragmentPresenter.class.getSimpleName();
    @Nullable
    ArticleDetailFragmentContract.View mView;
    private CnbetaApi mCnbetaApi;
    private long mSid;
    private String mTopicId;
    private CnbetaDatabase mCnbetaDatabase;

    @Inject
    public ArticleDetailFragmentPresenter(CnbetaApi cnbetaApi, CnbetaDatabase cnbetaDatabase) {
        mCnbetaApi = cnbetaApi;
        mCnbetaDatabase = cnbetaDatabase;
    }

    @Override
    public void takeView(@NonNull ArticleDetailFragmentContract.View view) {
        mView = view;
        mTopicId = view.getTopicId();
        mSid = view.getSid();
        if (TextUtils.isEmpty(mTopicId) || mSid == -1) {
            view.showArgumentsError();
            return;
        }
        mCnbetaApi.articleContentSign(mSid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CApiObserver<CnbetaBaseResponse<ArticleContent>>() {
                    @Override
                    public void onSuccess(CnbetaBaseResponse<ArticleContent> articleContentCnbetaBaseResponse) {
                        Log.i(TAG, "onSuccess: ");
                    }

                    @Override
                    public void onFail(CApiException e) {
                        Log.e(TAG, "onFail: ", e);
                    }
                });
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
