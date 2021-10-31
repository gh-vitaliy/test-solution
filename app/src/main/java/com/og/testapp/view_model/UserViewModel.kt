package com.og.testapp.view_model

import androidx.databinding.Bindable
import com.og.testapp.model.entity.User
import com.og.testapp.model.repository.UserRepository
import com.og.testapp.model.util.ObservableViewModel
import javax.inject.Inject

class UserViewModel   : ObservableViewModel() {

    var user: User? = null
        get() {
            notifyChange()
            return field
        }
        set(value) {
            field = value
            notifyChange()
        }


    @get:Bindable
    val firstName: String?
        get() = user?.firstName

    @get:Bindable
    val lastName: String?
        get() = user?.lastName


}