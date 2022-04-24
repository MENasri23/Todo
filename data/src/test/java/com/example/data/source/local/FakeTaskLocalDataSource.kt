package com.example.data.source.local

import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import com.example.model.entity.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaskLocalDataSource : TaskLocalDataSource {

    val tasks = mutableListOf<Task>()
    val users = mutableListOf<User>()

    override suspend fun insertTasks(tasks: List<Task>) {
//        delay(1000L)
        this.tasks.addAll(tasks)
    }

    override fun getUserTaskDetails(userId: Long): Flow<List<TaskDetail>> {
        return flow {
            delay(1000L)

            val userTaskDetails = tasks
                .filter { it.ownerId == userId }
                .mapNotNull {
                    getUserById(userId)?.let { user ->
                        it.toTaskDetail(user)
                    }
                }

            emit(userTaskDetails)
        }
    }

    override fun findTaskDetailById(id: String): Flow<TaskDetail?> {
        return flow {
            val task = tasks.first { it.id == id }
            val user = getUserById(task.ownerId)
            check(user != null) { "User can't be null when you discover its associated task" }
            emit(
                task.toTaskDetail(user = user)
            )
        }
    }

    override suspend fun removeTasks(tasks: List<Task>): Boolean {
//        delay(500L)
        this.tasks.removeAll(tasks)
        return true
    }

    suspend fun insertUser(users: List<User>) {
        this.users.addAll(users)
    }

    private fun getUserById(userId: Long) = users.firstOrNull { it.id == userId }

    private suspend fun Task.toTaskDetail(user: User) = TaskDetail(
        id = id,
        title = title,
        description = description,
        status = status,
        user = user,
        createdAt = createdAt,
        dueAt = dueAt
    )

}