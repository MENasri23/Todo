package com.example.todo.di

import com.example.data.di.CpuDispatcher
import com.example.data.di.IODispatcher
import com.example.data.di.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatcherModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @CpuDispatcher
    @Provides
    fun provideCpuDispatcher(): CoroutineDispatcher = Dispatchers.Default
}



