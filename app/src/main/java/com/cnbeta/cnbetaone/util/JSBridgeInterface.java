package com.cnbeta.cnbetaone.util;

import android.webkit.JavascriptInterface;

import com.cnbeta.cnbetaone.entity.ArticleContent;

public class JSBridgeInterface {
    private String mTitle;
    private String mSubTitle;
    private String mIntro;
    private String mContent;

    public JSBridgeInterface(String title, String subTitle, String intro, String content) {
        mTitle = title;
        mSubTitle = subTitle;
        mIntro = intro;
        mContent = content;
    }

    public JSBridgeInterface(ArticleContent content) {
        mTitle = content.getTitle();
        mSubTitle = content.getInputTime() + " " + content.getAid() + " " + content.getMView() + " 阅读" + content.getCollectNum() + "";
        mIntro = content.getHomeText();
        mContent = content.getBodyText();
    }

    @JavascriptInterface
    public String getTitle() {
        return mTitle;
    }

    @JavascriptInterface
    public String getSubTitle() {
        return mSubTitle;
    }

    @JavascriptInterface
    public String getIntro() {
        return mIntro;
    }

    @JavascriptInterface
    public String getContent() {
        return mContent;
    }
}
