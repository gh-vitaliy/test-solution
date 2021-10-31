package com.og.testapp.model.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.og.testapp.model.entity.User

class DatabaseTypeConverter {

    @TypeConverter
    fun fromUserToJson(user: User): String {
        val type = object : TypeToken<User>() {}.type
        return Gson().toJson(user, type)
    }

    @TypeConverter
    fun fromJsonToUser(json: String): User {
        val type = object : TypeToken<String>() {}.type
        return Gson().fromJson(json, type)
    }
}