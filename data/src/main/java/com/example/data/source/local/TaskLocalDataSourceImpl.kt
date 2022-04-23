package com.example.data.source.local

import com.example.database.dao.TaskDao
import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskLocalDataSourceImpl(
    private val taskDao: TaskDao
) : TaskLocalDataSource {

    override suspend fun insertTasks(tasks: List<Task>) = taskDao.insertTasks(tasks)


    override fun getUserTaskDetails(userId: Long): Flow<List<TaskDetail>> {
        return taskDao.loadUserTaskDetails(userId)
    }

    override fun findTaskDetailById(id: String): Flow<TaskDetail?> {
        return taskDao.findTaskDetailById(id)
    }

    override suspend fun removeTasks(tasks: List<Task>) = taskDao.removeTasks(tasks) != 0
}