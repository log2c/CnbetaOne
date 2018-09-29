package com.log2c.cnbetaone.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.log2c.cnbetaone.BR;
import com.log2c.cnbetaone.util.DateConverter;

import java.util.Date;

@Entity(primaryKeys = "sid", tableName = "article_summary")
@TypeConverters(DateConverter.class)
public class ArticleSummary extends BaseObservable {
    /**
     * sid : 768093
     * title : 14nm产能不足？英特尔：需求太旺盛了
     * pubtime : 2018-09-14 23:00:24
     * summary : 这两年，PC处理器市场呈现出了十几年来难得一见的繁荣景象，英特尔、AMD两家巨头竞相推出更强有力的新产品，让整个行业和消费者大呼过瘾。不过最近，市场上不断有消息称，英特尔14nm工艺正面临严重的产能不足，导致供应极为紧张，会对整个PC产业链造成巨大的冲击，甚至有英特尔的客户不得不推荐AMD平台，还有说法称英特尔可能会将部分14nm工艺订单转交给台积电(主要是300系列芯片组)，以缓解紧张局势。
     * topic : 159
     * counter : 0
     * comments : 0
     * ratings : 0
     * score : 0
     * ratings_story : 0
     * score_story : 0
     * topic_logo : https://static.cnbetacdn.com/topics/657f850ea9a33c2.png
     * thumb : https://static.cnbetacdn.com/thumb/mini/article/2018/0914/ec0b229a3ddfda5.jpg
     */

    private long sid;
    private String title;
    @SerializedName("pubtime")
    private Date pubTime;
    private String summary;
    private String topic;
    private String counter;
    private String comments;
    private String ratings;
    private String score;
    @SerializedName("ratings_story")
    private String ratingsStory;
    @SerializedName("score_story")
    private String scoreStory;
    @SerializedName("topic_logo")
    private String topicLogo;
    private String thumb;

    private boolean viewed = false; // 已查看

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public boolean isViewed() {
        return viewed;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
        notifyPropertyChanged(BR.viewed);
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRatingsStory() {
        return ratingsStory;
    }

    public void setRatingsStory(String ratingsStory) {
        this.ratingsStory = ratingsStory;
    }

    public String getScoreStory() {
        return scoreStory;
    }

    public void setScoreStory(String scoreStory) {
        this.scoreStory = scoreStory;
    }

    public String getTopicLogo() {
        return topicLogo;
    }

    public void setTopicLogo(String topicLogo) {
        this.topicLogo = topicLogo;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
