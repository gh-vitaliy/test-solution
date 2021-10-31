package com.og.testapp.model.di.module

import android.content.Context
import com.og.testapp.App
import com.og.testapp.view_model.UserListViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideContext(): Context = app

}