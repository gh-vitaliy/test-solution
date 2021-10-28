package com.og.rentatestapp.view_model

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.og.rentatestapp.model.Callback
import com.og.rentatestapp.model.User

class UserViewModel(context: Context) : BaseObservable() {

    private var callback: Callback? = null

    init {
        callback = context as? Callback
    }

    var user: User? = null
        set(user) {
            field = user
            notifyChange()
        }

    @get:Bindable
    val firstName: String?
        get() = user?.firstName

    @get:Bindable
    val lastName: String?
        get() = user?.lastName

    @get:Bindable
    val email: String?
        get() = user?.email

    val avatar: String?
        get() = user?.avatar


    fun onUserItemClick() {
        user?.let { callback?.onUserItemClicked(it.id) }
    }
}