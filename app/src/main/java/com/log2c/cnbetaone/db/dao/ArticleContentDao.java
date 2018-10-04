package com.log2c.cnbetaone.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.log2c.cnbetaone.entity.ArticleContent;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ArticleContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleContent... articleContents);

    @Delete
    void delete(ArticleContent articleContent);

    @Update
    void update(ArticleContent articleContent);

    @Query("SELECT * FROM article_content WHERE sid = (:sid) LIMIT 1")
    Flowable<List<ArticleContent>> queryBySid(long sid);

    @Query("SELECT * FROM article_content")
    Flowable<ArticleContent[]> loadAll();
}
