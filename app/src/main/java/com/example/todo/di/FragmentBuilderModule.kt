package com.example.todo.di

import com.example.todo.ui.tasks.TasksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeTasksFragment(): TasksFragment

}