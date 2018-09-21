package com.cnbeta.cnbetaone.api;

import com.cnbeta.cnbetaone.entity.ArticleContent;
import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CnbetaApi {

    @GET("/capi?method=Article.Lists")
    Call<CnbetaBaseResponse<List<ArticleSummary>>> articlesSign();

    @GET("/capi?method=Article.Lists")
    Call<CnbetaBaseResponse<List<ArticleSummary>>> topicArticlesSign(@Query("topicid") String topicId);

    @GET("/capi?method=Article.Lists")
    Call<CnbetaBaseResponse<List<ArticleSummary>>> newArticlesSign(@Query("topicid") String topicId, @Query("start_sid") long startSid);

    @GET("/capi?method=Article.Lists")
    Call<CnbetaBaseResponse<List<ArticleSummary>>> oldArticlesSign(@Query("topicid") String topicId, @Query("end_sid") long endSid);

    @GET("/capi?method=Article.NewsContent")
    Observable<CnbetaBaseResponse<ArticleContent>> articleContentSign(@Query("sid") long sid);

}
