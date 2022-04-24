package com.example.data.di

import com.example.data.source.local.TaskLocalDataSource
import com.example.data.source.local.TaskLocalDataSourceImpl
import com.example.data.source.local.UserLocalDataSource
import com.example.data.source.local.UserLocalDataSourceImpl
import com.example.database.di.DatabaseModule
import dagger.Binds
import dagger.Module


@Module(includes = [DatabaseModule::class])
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