package com.cnbeta.cnbetaone.data.repository;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;

public class ArticleSummaryServerRepositoryImp implements ArticleSummaryRepository {
    private CnbetaApi mCnbetaApi;

    @Inject
    public ArticleSummaryServerRepositoryImp(CnbetaApi cnbetaApi) {
        mCnbetaApi = cnbetaApi;
    }

    @Override
    public List<ArticleSummary> loadOnInit(@Nullable String type) {
        if (TextUtils.isEmpty(type) || type.equals("null")) {
            return wrapper(mCnbetaApi.articlesSign());
        }
        return mCnbetaApi.topicArticlesSign(type)
                .blockingFirst().getResult();
    }

    @Override
    public List<ArticleSummary> loadBefore(@Nullable String type, Long sid) {
        return wrapper(mCnbetaApi.newArticlesSign(type, sid));
    }

    @Override
    public List<ArticleSummary> loadAfter(@Nullable String type, Long sid) {
        return wrapper(mCnbetaApi.oldArticlesSign(type, sid));
    }

    private List<ArticleSummary> wrapper(Observable<CnbetaBaseResponse<List<ArticleSummary>>> responseObservable) {
        return Flowable.create((FlowableOnSubscribe<List<ArticleSummary>>) emitter -> responseObservable.subscribe(new CApiObserver<CnbetaBaseResponse<List<ArticleSummary>>>() {
            @Override
            public void onSuccess(CnbetaBaseResponse<List<ArticleSummary>> listCnbetaBaseResponse) {
                emitter.onNext(listCnbetaBaseResponse.getResult());
                emitter.onComplete();
            }

            @Override
            public void onFail(CApiException e) {
                emitter.onError(e);
            }
        }), BackpressureStrategy.BUFFER).blockingFirst();
    }

}
