package com.og.testapp.model.repository

import androidx.lifecycle.MutableLiveData
import com.og.testapp.model.api.UserApi
import com.og.testapp.model.database.UserDao
import com.og.testapp.model.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val userApi: UserApi, private val userDao: UserDao
) {
    val users = MutableLiveData<List<User>>()

    suspend fun getUsers() {
        withContext(Job() + Dispatchers.IO) {
            try {
                val usersData = userApi.getDataAsync().await()
                userDao.deleteUsers().subscribe {
                    userDao.insertUsers(usersData.data).subscribe()
                }
            } catch (e: Exception) {
                throw e
            } finally {
                users.postValue(userDao.selectUsers().blockingGet())
            }
        }
    }


}