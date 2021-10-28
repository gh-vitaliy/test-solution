package com.og.rentatestapp.model.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.og.rentatestapp.model.User
import kotlinx.coroutines.launch

class UserDbViewModel : ViewModel() {
    private val userDbRepository: UserDbRepository = UserDbRepository.getInstance()

    val userList: LiveData<List<User>> = userDbRepository.users
    fun getUserById(id: Int): LiveData<User> = userDbRepository.getUserById(id)

    fun deleteAllUsers() = viewModelScope.launch {
        userDbRepository.deleteAllUsers()
    }

    fun insertUsers(users: List<User>) = viewModelScope.launch {
        userDbRepository.insertUsers(users)
    }

    companion object {
        var INSTANCE: UserDbViewModel? = null

        fun getInstance(): UserDbViewModel {
            return INSTANCE ?: UserDbViewModel().also { INSTANCE = it }
        }
    }

}