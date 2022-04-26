package com.example.data.di

import com.example.data.source.local.TaskLocalDataSource
import com.example.data.source.local.TaskLocalDataSourceImpl
import com.example.data.source.local.UserLocalDataSource
import com.example.data.source.local.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DataSourceModule {

    @Binds
    abstract fun provideTaskLocalDataSource(
        taskLocal: TaskLocalDataSourceImpl
    ): TaskLocalDataSource

    @Binds
    abstract fun provideUserLocalDataSource(
        userLocal: UserLocalDataSourceImpl
    ): UserLocalDataSource

}