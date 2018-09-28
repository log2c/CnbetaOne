package com.log2c.cnbetaone.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.log2c.cnbetaone.db.CnbetaDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    CnbetaDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app,
                CnbetaDatabase.class, "cnbeta_db")
                .build();
    }
}
