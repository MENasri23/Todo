package com.example.data.repository

import com.example.model.result.Resource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.source.local.FakeTaskLocalDataSource
import com.example.test.shared.data.FakeData
import com.example.test.shared.data.MainCoroutineRule
import com.example.test.shared.data.runBlockingTest
import com.google.common.truth.Truth.*
import com.example.model.result.isSuccess
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TaskRepositoryTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var local: FakeTaskLocalDataSource

    private lateinit var taskRepository: TaskRepository

    @Before
    fun setup() {
        local = FakeTaskLocalDataSource()
        taskRepository = TaskRepository(local, Dispatchers.Main)
    }


    @Test
    fun insertTask_returnTrue() = coroutineRule.runBlockingTest {
        // given
        val task = FakeData.task1
        local.insertUser(listOf(FakeData.users.take(2).last()))

        // when
        taskRepository.saveTasks(listOf(task))

        // then
        val result =
            taskRepository.getTaskDetailByIdFlow(task.id).take(2).toList()


        assertThat(result).hasSize(2)
        assertThat(result[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(result[1].isSuccess).isTrue()

    }

    @Test
    fun `get user task details when user doesn't have any task`() = coroutineRule.runBlockingTest {
        val user = FakeData.user2

        val result = taskRepository.getUserTaskDetailsFlow(user.id).drop(1).first()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `load all tasks of the user and return success result`() = coroutineRule.runBlockingTest {
        val user = FakeData.user4
        val userTasks = FakeData.tasks.filter { it.ownerId == user.id }

        local.insertUser(listOf(user))
        taskRepository.saveTasks(userTasks)

        val result = taskRepository.getUserTaskDetailsFlow(user.id).drop(1).first()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.data).hasSize(userTasks.size)

    }

    @Test
    fun `remove task and get remaining task detials`() = coroutineRule.runBlockingTest {
        val user = FakeData.user1
        val userTasks = FakeData.tasks.filter { it.ownerId == user.id }

        local.insertUser(listOf(user))
        taskRepository.saveTasks(userTasks)

        local.removeTasks(listOf(userTasks[0]))
        val resultAfter = taskRepository.getUserTaskDetailsFlow(user.id).drop(1).first()

        assertThat(resultAfter.data).hasSize(userTasks.size - 1)

    }

}