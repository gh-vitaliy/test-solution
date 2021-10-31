package com.og.testapp.model.di.module

import android.content.Context
import androidx.room.Room
import com.og.testapp.model.database.Database
import com.og.testapp.model.database.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: Database): UserDao {
        return database.userDao
    }

}