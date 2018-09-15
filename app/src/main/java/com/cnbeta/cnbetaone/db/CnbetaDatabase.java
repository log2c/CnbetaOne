package com.cnbeta.cnbetaone.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cnbeta.cnbetaone.db.dao.ArticleSummaryDao;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

@Database(entities = {ArticleSummary.class}, version = 1)
public abstract class CnbetaDatabase extends RoomDatabase {

    public abstract ArticleSummaryDao articleSummaryDao();
}
