package com.cnbeta.cnbetaone.network;

import com.cnbeta.cnbetaone.Constants;

import java.io.IOException;
import java.security.InvalidParameterException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * CnbetaAPI 接口拦截器
 * 添加必要参数
 * <p>
 * app_key=10000&format=json&timestamp=?&v=1.0
 */

public class CnbetaApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl httpUrl = chain.request().url();
        Request originRequest = chain.request();
        if (!chain.request().url().toString().startsWith(Constants.CnbetaUrl.BASE_URL + "capi")) { // 只处理 Cnbeta API
            return chain.proceed(originRequest);
        }
        long timestamp = System.currentTimeMillis();
        String signStr = null;
        for (String name : httpUrl.queryParameterNames()) {
            String value = httpUrl.queryParameter(name);
            if (name.equals("method") && value != null) {
                switch (value) {
                    case "Article.Lists":
                        signStr = processArticleLists(httpUrl, timestamp);
                        break;
                    case "Article.NewsContent":
                        signStr = processArticleContentSign(httpUrl, timestamp);
                        break;
                }
            }
        }
        HttpUrl.Builder builder = httpUrl.newBuilder()
                .addQueryParameter("app_key", "10000")
                .addQueryParameter("format", "json")
                .addQueryParameter("v", "1.0")
                .addQueryParameter("mpuffgvbvbttn3Rc", null)
                .addQueryParameter("timestamp", timestamp + "");
        if (signStr == null) {
            throw new InvalidParameterException();  // 参数错误
        }
        builder.addQueryParameter("sign", signStr);
        return chain.proceed(originRequest.newBuilder().url(builder.build()).build());
    }

    private String processArticleContentSign(HttpUrl httpUrl, long timestamp) {
        if (httpUrl.queryParameter("sid") == null) {
            return null;
        }
        return CnBetaSignUtil.articleContentSign(timestamp, Integer.parseInt(httpUrl.queryParameter("sid")));
    }

    /**
     * Method 为 Article.Lists
     *
     * @param url url
     * @return hash sign
     */
    private String processArticleLists(HttpUrl url, long timestamp) {
        if (url.queryParameter("topicid") == null) {
            return CnBetaSignUtil.articlesSign(timestamp);
        }

        if (url.queryParameter("end_sid") != null) {
            return CnBetaSignUtil.oldArticlesSign(timestamp, url.queryParameter("topicid"), Integer.parseInt(url.queryParameter("end_sid")));
        }

        if (url.queryParameter("start_sid") != null) {
            return CnBetaSignUtil.newArticlesSign(timestamp, url.queryParameter("topicid"), Integer.parseInt(url.queryParameter("start_sid")));
        }
        return CnBetaSignUtil.topicArticlesSign(timestamp, url.queryParameter("topicid"));
    }
}
