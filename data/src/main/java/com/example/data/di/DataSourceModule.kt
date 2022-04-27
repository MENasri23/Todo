package com.example.data.di

import com.example.data.source.local.TaskLocalDataSource
import com.example.data.source.local.TaskLocalDataSourceImpl
import com.example.data.source.local.UserLocalDataSource
import com.example.data.source.local.UserLocalDataSourceImpl
import com.example.data.source.local.category.CategoryLocalDataSource
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

    @Binds
    abstract fun provideCategoryLocalDataSource(
        categoryLocal: CategoryLocalDataSource
    ): CategoryLocalDataSource

}