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
import java.util.Calendar;
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
        Calendar rightNow = Calendar.getInstance();
        Calendar publish = Calendar.getInstance();
        publish.setTime(publishTime);
        long l = rightNow.getTime().getTime() - publishTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long second = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String intervalStr;
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        String dateStr = sf.format(publishTime);
        int days = rightNow.get(Calendar.DAY_OF_YEAR) - publish.get(Calendar.DAY_OF_YEAR);
        if (publish.get(Calendar.YEAR) != rightNow.get(Calendar.YEAR)) {    // 不解释
            intervalStr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(publishTime);
            textView.setText(intervalStr);
            return;
        }
        switch (days) {
            case 0: // 今天
                if (hour == 0 && min == 0) {    // *秒前发布
                    intervalStr = String.format(textView.getContext().getString(R.string.second_ago), second);
                } else if (hour == 0) {  // *分钟前发布
                    intervalStr = String.format(textView.getContext().getString(R.string.minutes_ago), min);
                } else if (hour <= 3) { // 小于等于三小时内
                    intervalStr = String.format(textView.getContext().getString(R.string.hour_ago), hour);
                } else {
                    intervalStr = String.format(textView.getContext().getString(R.string.today_time), dateStr);
                }
                break;
            case 1: // 昨天
                intervalStr = textView.getContext().getString(R.string.yesterday) + " " + new SimpleDateFormat("HH:mm", Locale.CHINA).format(publishTime);
                break;
            default:    // 前天及之前
                intervalStr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(publishTime);
                break;
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