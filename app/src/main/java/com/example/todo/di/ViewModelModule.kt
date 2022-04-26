package com.example.todo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.ui.tasks.TasksViewModel
import com.example.todo.viewmodel.TodoViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(viewModel: TasksViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory

}