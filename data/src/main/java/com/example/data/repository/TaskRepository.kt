package com.example.data.repository

import com.example.data.source.local.TaskLocalDataSource
import com.example.data.util.toResource
import com.example.model.entity.Task
import com.example.data.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val localDataSource: TaskLocalDataSource,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {


    suspend fun saveTasks(tasks: List<Task>) = withContext(dispatcher) {
        localDataSource.insertTasks(tasks)
    }

    fun getUserTaskDetailsFlow(userId: Long) = localDataSource.getUserTaskDetails(userId)
        .distinctUntilChanged()
        .toResource()
        .flowOn(dispatcher)

    fun getTaskDetailByIdFlow(id: String) = localDataSource.findTaskDetailById(id)
        .distinctUntilChanged()
        .toResource()
        .flowOn(dispatcher)

    suspend fun removeTasks(tasks: List<Task>) = withContext(dispatcher) {
        localDataSource.removeTasks(tasks)
    }

}