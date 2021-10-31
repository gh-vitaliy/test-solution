package com.og.testapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.og.testapp.model.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
}