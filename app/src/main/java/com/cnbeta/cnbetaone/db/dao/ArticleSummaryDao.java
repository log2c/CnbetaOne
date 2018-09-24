package com.cnbeta.cnbetaone.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ArticleSummaryDao {

    /**
     * 加载所有数据
     *
     * @return 数据
     */
    @Query("SELECT * FROM article_summary ORDER BY pubTime DESC LIMIT 20")
    Flowable<List<ArticleSummary>> loadAllLimit();

    /**
     * 按 topic 加载数据
     *
     * @param topic topic
     * @return 数据
     */
    @Query("SELECT * FROM article_summary WHERE topic = (:topic) ORDER BY pubTime DESC LIMIT 20")
    Flowable<List<ArticleSummary>> loadByTopicLimit(String topic);

    @Query("SELECT * FROM article_summary WHERE sid < (:sid) ORDER BY pubTime DESC limit 20")
    Flowable<List<ArticleSummary>> queryBeforeBySid(long sid);

    @Query("SELECT * FROM article_summary WHERE sid > (:sid) ORDER BY pubTime DESC limit 20")
    Flowable<List<ArticleSummary>> queryAfterBySid(long sid);

    @Query("SELECT * FROM article_summary WHERE topic =(:topic) AND sid > (:sid) ORDER BY pubTime DESC limit 20")
    Flowable<List<ArticleSummary>> queryAfterSid(long sid, String topic);

    @Query("SELECT * FROM article_summary WHERE topic =(:topic) AND sid < (:sid) ORDER BY pubTime limit 20")
    Flowable<List<ArticleSummary>> queryBeforeSid(long sid, String topic);

    @Query("SELECT * FROM article_summary")
    Flowable<List<ArticleSummary>> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleSummary... summaries);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIgnore(ArticleSummary... summaries);

    @Delete
    void delete(ArticleSummary summary);

    @Update
    void update(ArticleSummary summary);

    @Query("UPDATE article_summary SET viewed = (:viewed) WHERE sid =(:sid)")
    void update(long sid, boolean viewed);
}
