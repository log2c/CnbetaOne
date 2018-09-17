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

    @Query("SELECT * FROM article_summary WHERE sid < (:sid) ORDER BY pubTime")
    Flowable<List<ArticleSummary>> queryBySid(long sid);

    @Query("SELECT * FROM article_summary")
    Flowable<List<ArticleSummary>> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleSummary... summaries);

    @Delete
    void delete(ArticleSummary summary);

    @Update
    void update(ArticleSummary summary);
}
