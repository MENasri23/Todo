package com.example.todo.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.TaskRepository
import com.example.model.entity.User
import com.example.model.result.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val currentUser: User
) : ViewModel() {

    val taskDetails = taskRepository
        .getUserTaskDetailsFlow(currentUser.id)
        .stateIn(
            initialValue = Resource.loading(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L)
        )
}