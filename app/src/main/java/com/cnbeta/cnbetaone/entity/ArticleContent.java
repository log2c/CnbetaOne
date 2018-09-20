package com.cnbeta.cnbetaone.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleContent {
    private long sid;
    @SerializedName("catid")
    private String catId;
    private String topic;
    private String aid;
    private String user_id;
    private String title;
    private String style;
    private String keywords;
    @SerializedName("hometext")
    private String homeText;
    @SerializedName("listorder")
    private String listOrder;
    private String comments;
    private String counter;
    @SerializedName("mview")
    private String mView;
    @SerializedName("collectnum")
    private String collectNum;
    private String good;
    private String bad;
    private String score;
    private String ratings;
    private String score_story;
    private String ratings_story;
    @SerializedName("pollid")
    private String pollId;
    @SerializedName("queueid")
    private String queueId;
    @SerializedName("ifcom")
    private String ifCom;
    @SerializedName("ishome")
    private String isHome;
    private String elite;
    private String status;
    @SerializedName("inputtime")
    private String inputTime;
    @SerializedName("updatetime")
    private String updateTime;
    private String thumb;
    private String source;
    @SerializedName("sourceid")
    private String sourceId;
    private String premium;
    private String data_id;
    @SerializedName("bodytext")
    private String bodyText;
    private String relation;
    private String relation_show;
    @SerializedName("shorttitle")
    private String shortTitle;
    private String brief;
    private Date time;

}
