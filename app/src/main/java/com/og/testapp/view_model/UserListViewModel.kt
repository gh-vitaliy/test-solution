package com.og.testapp.view_model

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.og.testapp.model.entity.User
import com.og.testapp.model.repository.UserListRepository
import com.og.testapp.model.util.LoadingStatus
import com.og.testapp.model.util.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserListViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ObservableViewModel() {
    var userList: MutableLiveData<List<User>> = userListRepository.users

    var loadingStatus: MutableLiveData<LoadingStatus> =
        MutableLiveData<LoadingStatus>()
            .apply { value = LoadingStatus(LoadingStatus.Status.LOADING) }
        get() {
            notifyChange()
            return field
        }
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    val isLoading: Boolean
        get() {
            notifyChange()
            return when (loadingStatus.value) {
                LoadingStatus.LOADING -> true
                LoadingStatus.LOADED -> false
                else -> true
            }
        }


    fun fetchData() {
        viewModelScope.launch(Job() + Dispatchers.IO) {
            try {
                loadingStatus.postValue(LoadingStatus.LOADING)
                userListRepository.getUsers()
                loadingStatus.postValue(LoadingStatus.LOADED)
            } catch (e: Exception) {
                loadingStatus.postValue(LoadingStatus.ERROR)
            }
        }
    }

    init {
        fetchData()
    }

}