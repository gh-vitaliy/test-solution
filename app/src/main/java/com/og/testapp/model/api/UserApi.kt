package com.og.testapp.model.api

import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("api/users")
    fun getDataAsync(): Call<ApiOutput>

}