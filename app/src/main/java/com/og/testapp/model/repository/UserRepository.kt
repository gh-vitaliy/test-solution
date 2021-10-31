package com.og.testapp.model.repository

import androidx.lifecycle.MutableLiveData
import com.og.testapp.model.database.UserDao
import com.og.testapp.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    val user = MutableLiveData<User>()

    suspend fun getUser(userId: Int) {
        withContext(Job() + Dispatchers.IO) {
            userDao.selectUserById(userId).blockingSubscribe { user ->
                this@UserRepository.user.postValue(user)
            }
        }
    }

}