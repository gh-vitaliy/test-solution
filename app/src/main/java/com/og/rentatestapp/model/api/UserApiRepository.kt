package com.og.rentatestapp.model.api

import android.util.Log
import com.og.rentatestapp.model.database.UserDbViewModel
import com.og.rentatestapp.view_model.UsersListViewModel
import retrofit2.Call
import retrofit2.Response

private const val TAG = "UserDbRepository"

class UserRepository {

    private var userApi: UserApi = RetrofitService().userApi
    private var userDbViewModel: UserDbViewModel = UserDbViewModel.getInstance()
    private val usersListViewModel = UsersListViewModel.getInstance()

     fun getUsers() {
        val usersApiOutput = userApi.getOutput()
        usersApiOutput.enqueue(object : retrofit2.Callback<ApiOutput> {
            override fun onResponse(call: Call<ApiOutput>, response: Response<ApiOutput>) {
                Log.d(TAG, response.body()?.data.toString())
                usersListViewModel.isApiError.value = false
                val usersList = response.body()?.data
                usersListViewModel.usersList?.value = usersList
                userDbViewModel.run {
                    usersList?.let { list ->
                        deleteAllUsers()
                        insertUsers(list)
                    }
                }
            }

            override fun onFailure(call: Call<ApiOutput>, t: Throwable) {
                Log.d(TAG, "Failed to get user data")
                usersListViewModel.isApiError.value = true
            }
        })

    }
}