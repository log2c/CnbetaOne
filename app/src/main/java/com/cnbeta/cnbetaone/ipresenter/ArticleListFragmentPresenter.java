package com.cnbeta.cnbetaone.ipresenter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.iview.ArticleListContract;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;
import com.cnbeta.cnbetaone.ui.fragment.ArticleListFragment;

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
    private ObservableArrayList<ArticleSummary> mArticleSummaryList;
    @NonNull
    private CnbetaApi mCnbetaApi;

    @Inject
    public ArticleListFragmentPresenter(ArticleListFragment fragment, CnbetaApi cnbetaApi) {
        if (fragment.getArguments() != null) {
            this.topicType = fragment.getArguments().getString(ArticleListFragment.TOPIC_ID);
        }
        mCnbetaApi = cnbetaApi;
    }

    @Override
    public void takeView(@NonNull ArticleListContract.View view) {
        mView = view;
        if (mArticleSummaryList == null) {
            mArticleSummaryList = new ObservableArrayList<>();
            mArticleSummaryList.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<ArticleSummary>>() {
                @Override
                public void onChanged(ObservableList<ArticleSummary> sender) {

                }

                @Override
                public void onItemRangeChanged(ObservableList<ArticleSummary> sender, int positionStart, int itemCount) {

                }

                @Override
                public void onItemRangeInserted(ObservableList<ArticleSummary> sender, int positionStart, int itemCount) {
                    if (mView != null) {
                        mView.notifyDataSetChanged();
                    }
                }

                @Override
                public void onItemRangeMoved(ObservableList<ArticleSummary> sender, int fromPosition, int toPosition, int itemCount) {

                }

                @Override
                public void onItemRangeRemoved(ObservableList<ArticleSummary> sender, int positionStart, int itemCount) {

                }
            });
            mView.initAdapter(mArticleSummaryList);
            loadDataFromServer();
        }
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
                        mArticleSummaryList.addAll(listCnbetaBaseResponse.getResult());
                    }

                    @Override
                    public void onFail(CApiException e) {
                        Log.e(TAG, "onFail: ", e);
                    }
                });
    }
}
