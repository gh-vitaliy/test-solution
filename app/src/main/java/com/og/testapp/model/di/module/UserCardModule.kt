package com.og.testapp.model.di.module

import android.content.Context
import android.os.Bundle
import com.og.testapp.view.UserCardFragment
import dagger.Module
import dagger.Provides

@Module
class UserCardModule(private val userCardFragment: UserCardFragment) {

    @Provides
    fun provideContext(): Context = userCardFragment.requireContext()

    @Provides
    fun provideBundle(): Bundle = userCardFragment.requireArguments()
}