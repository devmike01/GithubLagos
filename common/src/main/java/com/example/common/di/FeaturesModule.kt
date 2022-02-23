package com.example.common.di

import com.example.common.schedulers.CommonSchedulers
import com.example.common.schedulers.CommonSchedulersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class CommonSchedulersModule {

    @Provides
    fun provideSchedulers(schedulers: CommonSchedulersImpl): CommonSchedulers {
        return schedulers
    }
}