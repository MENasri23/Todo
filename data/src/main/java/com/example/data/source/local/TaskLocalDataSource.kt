package com.example.data.source.local

import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {

    suspend fun insertTasks(tasks: List<Task>)

    fun getUserTaskDetails(userId: Long): Flow<List<TaskDetail>>

    fun findTaskDetailById(id: String): Flow<TaskDetail>

    suspend fun removeTasks(tasks: List<Task>): Boolean

}