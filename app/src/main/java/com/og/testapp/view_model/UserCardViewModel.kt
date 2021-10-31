package com.og.testapp.view_model

import android.os.Bundle
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.og.testapp.model.entity.User
import com.og.testapp.model.repository.UserRepository
import com.og.testapp.model.util.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val USER_ID = "UserId"


class UserCardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val bundle: Bundle
) : ObservableViewModel() {
    private val userId: Int
        get() {
            notifyChange()
            return bundle.getInt(USER_ID)
        }

    var user: MutableLiveData<User> = userRepository.user
        set(value) {
            field = value
            notifyChange()
        }
        get() {
            notifyChange()
            return field
        }


    @get:Bindable
    val firstName: String?
        get() = user.value?.firstName

    @get:Bindable
    val lastName: String?
        get() = user.value?.lastName

    @get:Bindable
    val email: String?
        get() = user.value?.email

    private fun fetchData() {
        viewModelScope.launch(Job() + Dispatchers.IO) {
            userRepository.getUser(userId)
        }
    }

    init {
        fetchData()
    }
}