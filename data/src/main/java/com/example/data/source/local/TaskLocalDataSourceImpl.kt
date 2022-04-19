package com.example.data.source.local

import com.example.database.dao.TaskDao
import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import kotlinx.coroutines.flow.Flow

class TaskLocalDataSourceImpl(
    private val taskDao: TaskDao
) : TaskLocalDataSource {

    override suspend fun insertTasks(tasks: List<Task>) {
        taskDao.insertTasks(tasks)
    }

    override fun getUserTaskDetails(userId: Long): Flow<List<TaskDetail>> {
        return taskDao.loadUserTaskDetails(userId)
    }

    override fun findTaskDetailById(id: String): Flow<TaskDetail?> {
        return taskDao.findTaskDetailById(id)
    }

    override suspend fun removeTasks(tasks: List<Task>): Boolean {
        return taskDao.removeTasks(tasks) != 0
    }
}