package com.og.rentatestapp.model.database

import androidx.lifecycle.LiveData
import com.og.rentatestapp.model.User

class UserDbRepository {
    private val database = DatabaseService.get()
    private val userDao = database.userDao()

    val users: LiveData<List<User>> = userDao.getUsers()
    fun getUserById(id: Int): LiveData<User> = userDao.getUserById(id)

    suspend fun insertUsers(users: List<User>) = userDao.insertUsers(users)
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

    companion object {
        var INSTANCE: UserDbRepository? = null

        fun getInstance(): UserDbRepository {
            return INSTANCE ?: UserDbRepository().also { INSTANCE = it }
        }
    }
}