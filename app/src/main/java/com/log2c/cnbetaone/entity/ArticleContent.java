package com.log2c.cnbetaone.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.log2c.cnbetaone.util.DateConverter;

import java.util.Date;

@Entity(primaryKeys = "sid", tableName = "article_content")
@TypeConverters(DateConverter.class)
public class ArticleContent {
    private long sid;
    @SerializedName("catid")
    private String catId;
    private String topic;
    private String aid;
    @SerializedName("user_id")
    private String userId;
    private String title;
    private String style;
    private String keywords;
    @SerializedName("hometext")
    private String homeText;
    @SerializedName("listorder")
    private long listOrder;
    private long comments;
    private long counter;
    @SerializedName("mview")
    private long view;
    @SerializedName("collectnum")
    private String collectNum;
    private long good;
    private long bad;
    private long score;
    private long ratings;
    @SerializedName("score_story")
    private long scoreStory;
    @SerializedName("ratings_story")
    private long ratingsStory;
    @SerializedName("pollid")
    private long pollId;
    @SerializedName("queueid")
    private long queueId;
    @SerializedName("ifcom")
    private String ifCom;
    @SerializedName("ishome")
    private String isHome;
    private String elite;
    private int status;
    @SerializedName("inputtime")
    private String inputTime;
    @SerializedName("updatetime")
    private String updateTime;
    private String thumb;
    private String source;
    @SerializedName("sourceid")
    private String sourceId;
    private String premium;
    @SerializedName("data_id")
    private String dataId;
    @SerializedName("bodytext")
    private String bodyText;
    private String relation;
    @SerializedName("relation_show")
    private String relationShow;
    @SerializedName("shorttitle")
    private String shortTitle;
    private String brief;
    private Date time;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getHomeText() {
        return homeText;
    }

    public void setHomeText(String homeText) {
        this.homeText = homeText;
    }

    public long getListOrder() {
        return listOrder;
    }

    public void setListOrder(long listOrder) {
        this.listOrder = listOrder;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public long getGood() {
        return good;
    }

    public void setGood(long good) {
        this.good = good;
    }

    public long getBad() {
        return bad;
    }

    public void setBad(long bad) {
        this.bad = bad;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getRatings() {
        return ratings;
    }

    public void setRatings(long ratings) {
        this.ratings = ratings;
    }

    public long getScoreStory() {
        return scoreStory;
    }

    public void setScoreStory(long scoreStory) {
        this.scoreStory = scoreStory;
    }

    public long getRatingsStory() {
        return ratingsStory;
    }

    public void setRatingsStory(long ratingsStory) {
        this.ratingsStory = ratingsStory;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }

    public String getIfCom() {
        return ifCom;
    }

    public void setIfCom(String ifCom) {
        this.ifCom = ifCom;
    }

    public String getIsHome() {
        return isHome;
    }

    public void setIsHome(String isHome) {
        this.isHome = isHome;
    }

    public String getElite() {
        return elite;
    }

    public void setElite(String elite) {
        this.elite = elite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelationShow() {
        return relationShow;
    }

    public void setRelationShow(String relationShow) {
        this.relationShow = relationShow;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
