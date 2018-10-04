package com.log2c.cnbetaone.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.log2c.cnbetaone.db.dao.ArticleContentDao;
import com.log2c.cnbetaone.db.dao.ArticleSummaryDao;
import com.log2c.cnbetaone.entity.ArticleContent;
import com.log2c.cnbetaone.entity.ArticleSummary;

@Database(entities = {ArticleSummary.class, ArticleContent.class}, version = 1)
public abstract class CnbetaDatabase extends RoomDatabase {

    public abstract ArticleSummaryDao articleSummaryDao();

    public abstract ArticleContentDao articleContentDao();
}
