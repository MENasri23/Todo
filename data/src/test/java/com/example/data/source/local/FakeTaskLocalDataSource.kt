package com.example.data.source.local

import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import com.example.test.shared.data.FakeData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTaskLocalDataSource : TaskLocalDataSource {

    private val taskDetails = listOf<TaskDetail>()

    override suspend fun insertTasks(tasks: List<Task>) {
        delay(1000L)
    }

    override fun getUserTaskDetails(userId: Long): Flow<List<TaskDetail>> {
        return flow { emit(taskDetails) }
    }

    override fun findTaskDetailById(id: String): Flow<TaskDetail?> {
        return flow {

            val random = (0..9).random()
            if (random == 9) {
                throw Throwable("Error fetching data")
            }
            delay(1_500L)
            val user = FakeData.user1
            val task = FakeData.task2

            val fakeTaskDetail = TaskDetail(
                id = task.id,
                title = task.title,
                description = task.description,
                status = task.status,
                user = user,
                createdAt = task.createdAt,
                dueAt = task.dueAt
            )
            emit(fakeTaskDetail)
        }
    }

    override suspend fun removeTasks(tasks: List<Task>): Boolean {
        delay(500L)
        return true
    }

}