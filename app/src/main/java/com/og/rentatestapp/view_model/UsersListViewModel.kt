package com.og.rentatestapp.view_model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.og.rentatestapp.model.User

private const val TAG = "UsersListViewModel"

class UsersListViewModel : BaseObservable() {

    var usersList: MutableLiveData<List<User>>? = MutableLiveData()

    @get:Bindable
    val isDataLoading: Boolean
        get() {
            notifyChange()
            return usersList?.value.isNullOrEmpty()
        }

    var isApiError: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        var INSTANCE: UsersListViewModel? = null

        fun getInstance(): UsersListViewModel {
            return INSTANCE ?: UsersListViewModel().also { INSTANCE = it }
        }

    }
}