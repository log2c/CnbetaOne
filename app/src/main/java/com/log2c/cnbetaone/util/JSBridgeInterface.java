package com.log2c.cnbetaone.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.entity.ArticleContent;
import com.log2c.cnbetaone.ui.photoview.PhotoViewActivity;

import java.util.Date;

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
        mSubTitle = String.format(context.getString(R.string.sub_title_format), getSubTitleTimeStr(content, context), content.getAid(), content.getMView(), content.getComments());
        mIntro = content.getHomeText();
        mContent = content.getBodyText();
    }

    private static String getSubTitleTimeStr(ArticleContent content, Context context) {
        Date articleDate = content.getTime();
        Date nowDate = new Date();
        long l = nowDate.getTime() - articleDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day > 0) {
            return String.format(context.getString(R.string.sub_title_day_time), day);
        } else if (hour > 0) {
            return String.format(context.getString(R.string.sub_title_hour_time), hour);
        } else if (min > 0) {
            return String.format(context.getString(R.string.sub_title_minute_time), min);
        } else {
            return String.format(context.getString(R.string.sub_title_second_time), second);
        }
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
