package com.og.testapp

import android.app.Application
import com.og.testapp.model.di.DaggerUserCardComponent
import com.og.testapp.model.di.DaggerUserListComponent
import com.og.testapp.model.di.UserCardComponent
import com.og.testapp.model.di.UserListComponent
import com.og.testapp.model.di.module.ApiModule
import com.og.testapp.model.di.module.AppModule
import com.og.testapp.model.di.module.DatabaseModule

class App : Application() {

    val userListComponent: UserListComponent =
        DaggerUserListComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule())
            .build()



}