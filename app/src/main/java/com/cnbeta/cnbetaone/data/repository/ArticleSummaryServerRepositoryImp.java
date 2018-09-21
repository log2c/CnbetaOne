package com.cnbeta.cnbetaone.data.repository;

import android.app.Application;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.api.CnbetaApi;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;
import com.cnbeta.cnbetaone.exception.CApiException;
import com.cnbeta.cnbetaone.rxjava.CApiObserver;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;

public class ArticleSummaryServerRepositoryImp implements ArticleSummaryRepository {
    private CnbetaApi mCnbetaApi;
    private Application mApplication;

    @Inject
    public ArticleSummaryServerRepositoryImp(CnbetaApi cnbetaApi, Application application) {
        mCnbetaApi = cnbetaApi;
        mApplication = application;
    }

    @Override
    public List<ArticleSummary> loadOnInit(@Nullable String type) {
        if (TextUtils.isEmpty(type) || type.equals("null")) {
            return filter(mCnbetaApi.articlesSign());
        }
        return filter(mCnbetaApi.topicArticlesSign(type));
    }

    @Override
    public List<ArticleSummary> loadBefore(@Nullable String type, Long sid) {
        return filter(mCnbetaApi.newArticlesSign(type, sid));
    }

    @Override
    public List<ArticleSummary> loadAfter(@Nullable String type, Long sid) {
        return filter(mCnbetaApi.oldArticlesSign(type, sid));
    }

    @SuppressWarnings("ThrowableNotThrown")
    private List<ArticleSummary> filter(Call<CnbetaBaseResponse<List<ArticleSummary>>> responseCall) {
        try {
            return Objects.requireNonNull(responseCall.execute().body()).getResult();
        } catch (Exception e) {
            e.printStackTrace();
            CApiException cApiException = CApiObserver.processException(e.getCause());
            String errorMessage;
            switch (cApiException.getErrorCode()) {
                case 1: // HTTP 异常
                    errorMessage = mApplication.getResources().getString(R.string.network_error);
                    break;
                case 2: // 转换器等异常
                    errorMessage = mApplication.getResources().getString(R.string.okhttp_error);
                    break;
                default:    //未知
                    errorMessage = mApplication.getResources().getString(R.string.unknown_error);
                    break;
            }

            Toast.makeText(mApplication, errorMessage, Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
