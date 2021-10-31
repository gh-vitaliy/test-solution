package com.og.testapp.model.di

import com.og.testapp.model.di.module.ApiModule
import com.og.testapp.model.di.module.AppModule
import com.og.testapp.model.di.module.DatabaseModule
import com.og.testapp.view.UserCardFragment
import com.og.testapp.view.UserListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, DatabaseModule::class])
interface UserListComponent {
    fun inject(userListFragment: UserListFragment)
}