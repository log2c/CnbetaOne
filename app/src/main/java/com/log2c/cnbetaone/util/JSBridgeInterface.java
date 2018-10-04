package com.log2c.cnbetaone.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.entity.ArticleContent;
import com.log2c.cnbetaone.ui.photoview.PhotoViewActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class JSBridgeInterface {
    private static final String TAG = JSBridgeInterface.class.getSimpleName();
    private String mTitle;
    private String mSubTitle;
    private String mIntro;
    private String mContent;
    private Context mContext;


    public JSBridgeInterface(ArticleContent content, Context context) {
        mContext = context;
        mTitle = content.getTitle();
        mSubTitle = String.format(context.getString(R.string.sub_title_format), getSubTitleTimeStr(content), content.getAid(), content.getView(), content.getComments());
        mIntro = content.getHomeText();
        mContent = content.getBodyText();
    }

    private static String getSubTitleTimeStr(ArticleContent content) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(content.getTime());
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

    @JavascriptInterface
    public void photoPreview(String[] photoList, int position) {
        Log.i(TAG, "photoPreview: ");
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.FLAG_IMG_POSITION, position);
        intent.putExtra(PhotoViewActivity.FLAG_IMG_LIST, photoList);
        mContext.startActivity(intent);
    }
}
