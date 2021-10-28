package com.og.rentatestapp.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.og.rentatestapp.model.User

class DatabaseTypeConverter {


    @TypeConverter
    fun fromUserToString(user: User): String {
        val type = object : TypeToken<User>() {}.type
        return Gson().toJson(user, type)
    }

    @TypeConverter
    fun fromStringToUser(json: String): User {
        val type = object : TypeToken<String>() {}.type
        return Gson().fromJson(json, type)
    }
}