package com.og.rentatestapp.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitService"
private const val BASE_URL = "https://reqres.in/"

class RetrofitService {

    val userApi: UserApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userApi = retrofit.create(UserApi::class.java)
    }


}

