package com.cnbeta.cnbetaone.api;

import com.cnbeta.cnbetaone.entity.ArticleSummary;
import com.cnbeta.cnbetaone.entity.CnbetaBaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CnbetaApi {

    @GET("/capi?method=Article.Lists")
    Observable<CnbetaBaseResponse<List<ArticleSummary>>> articlesSign();

    @GET("capi")
    Observable<String> topicArticlesSign(@Query("topicid") String topicId);

    @GET("capi")
    Observable<String> newArticlesSign(@Query("topicid") String topicId, @Query("start_sid") int startSid);

    @GET("capi")
    Observable<String> oldArticlesSign(@Query("topicid") String topicId, @Query("end_sid") int endSid);

    @GET("capi")
    Observable<String> articleContentSign(@Query("topicid") String topicId, @Query("sid") int sid);

}
