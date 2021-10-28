package com.og.rentatestapp.model.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.og.rentatestapp.RentaTestApplication
import com.og.rentatestapp.model.User

private const val DATABASE_NAME = "RENTA_TEST_APP_DATABASE"

@Database(entities = [User::class], version = 1)
@TypeConverters(DatabaseTypeConverter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: DatabaseService? = null

        fun get(): DatabaseService {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    RentaTestApplication.applicationContext(),
                    DatabaseService::class.java,
                    DATABASE_NAME
                )
                    .build()

            return INSTANCE!!
        }
    }
}