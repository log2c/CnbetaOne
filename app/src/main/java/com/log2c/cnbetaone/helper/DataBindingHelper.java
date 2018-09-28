package com.log2c.cnbetaone.helper;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.entity.ArticleSummary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBindingHelper {

    /**
     * 1.加载图片,无需手动调用此方法
     * 2.使用@BindingAdapter注解设置自定义属性的名称，imageUrl就是属性的名称，
     * 当ImageView中使用imageUrl属性时，会自动调用loadImage方法，
     *
     * @param imageView ImageView
     * @param url       图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .into(imageView);
    }

    @BindingAdapter({"timeInterval"})
    public static void getIntervalStr(TextView textView, Date publishTime) {
        long l = new Date().getTime() - publishTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String intervalStr;
        SimpleDateFormat sf = new SimpleDateFormat("a hh:mm", Locale.CHINA);
        String dateStr = sf.format(publishTime);
        if (day > 0) { // 1天及以上的新闻
            if (day == 1) {    //昨天
                intervalStr = textView.getContext().getString(R.string.yesterday) + " " + dateStr;
            } else if (day <= 3) { // 1<day<=3
                intervalStr = String.format(textView.getContext().getString(R.string.days_ago), day);
            } else {
                intervalStr = new SimpleDateFormat("yyyy-MM-dd ahh:mm:ss", Locale.CHINA).format(publishTime);
            }
        } else if (hour > 0) {
            intervalStr = String.format(textView.getContext().getString(R.string.hour_ago), hour) + " " + dateStr;
        } else if (min > 0) {
            intervalStr = String.format(textView.getContext().getString(R.string.minutes_ago), min);
        } else {
            intervalStr = String.format(textView.getContext().getString(R.string.second_ago), second);
        }
        textView.setText(intervalStr);
    }

    @BindingAdapter({"articleSummary"})
    public static void setArticleSummaryTitle(TextView textView, ArticleSummary articleSummary) {
        if (articleSummary == null) {
            return;
        }
        if (articleSummary.isViewed()) {
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.app_color_viewed));
            textView.setText(articleSummary.getTitle());
        } else {
            textView.setTextColor(Color.BLACK);
            textView.setText(articleSummary.getTitle());
        }

    }
}