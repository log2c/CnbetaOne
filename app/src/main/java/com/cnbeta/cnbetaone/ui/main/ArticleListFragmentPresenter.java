package com.cnbeta.cnbetaone.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleListFragmentPresenter implements ArticleListContract.Presenter {
    private static final String TAG = ArticleListFragmentPresenter.class.getSimpleName();
    @Nullable
    private String topicType;
    @Nullable
    private ArticleListContract.View mView;
    private LiveData<PagedList<ArticleSummary>> mArticleSummaryList;
    @NonNull
    private CnbetaApi mCnbetaApi;

    private boolean isInit = false;

    @Inject
    public ArticleListFragmentPresenter(ArticleListFragment fragment, CnbetaApi cnbetaApi, LiveData<PagedList<ArticleSummary>> pagedListLiveData) {
        if (fragment.getArguments() != null) {
            this.topicType = fragment.getArguments().getString(ArticleListFragment.TOPIC_ID);
        }
        mCnbetaApi = cnbetaApi;
        mArticleSummaryList = pagedListLiveData;
    }

    @Override
    public void takeView(@NonNull ArticleListContract.View view) {
        mView = view;
        if (isInit) {
            return;
        }
        mView.initAdapter(mArticleSummaryList);
        isInit = true;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    private void loadDataFromServer() {
        mCnbetaApi.articlesSign()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CApiObserver<CnbetaBaseResponse<List<ArticleSummary>>>() {
                    @Override
                    public void onSuccess(CnbetaBaseResponse<List<ArticleSummary>> listCnbetaBaseResponse) {
                        Log.i(TAG, "onSuccess: loadDataFromServer()");
                        mArticleSummaryList.getValue().addAll(listCnbetaBaseResponse.getResult());
                    }

                    @Override
                    public void onFail(CApiException e) {
                        Log.e(TAG, "onFail: ", e);
                    }
                });
    }
}
