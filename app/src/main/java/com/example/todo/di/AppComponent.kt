package com.example.todo.di

import android.content.Context
import com.example.data.di.DataSourceModule
import com.example.data.di.DispatcherModule
import com.example.database.di.DatabaseModule
import com.example.todo.TodoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        DatabaseModule::class,
        DispatcherModule::class,
        DataSourceModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(todoApp: TodoApplication)

}