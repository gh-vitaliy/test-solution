package com.og.rentatestapp

import android.app.Application
import android.content.Context
import com.og.rentatestapp.model.database.UserDbViewModel

class RentaTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        UserDbViewModel.getInstance()
    }

    companion object {
        var INSTANCE: RentaTestApplication? = null

        fun applicationContext(): Context = INSTANCE!!.applicationContext
    }
}