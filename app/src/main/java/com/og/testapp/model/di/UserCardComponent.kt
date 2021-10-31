package com.og.testapp.model.di

import com.og.testapp.model.di.module.DatabaseModule
import com.og.testapp.model.di.module.UserCardModule
import com.og.testapp.view.UserCardFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, UserCardModule::class])
interface UserCardComponent {
    fun inject(userCardFragment: UserCardFragment)
}