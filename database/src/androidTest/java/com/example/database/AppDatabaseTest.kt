package com.example.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import com.example.database.view.TaskDetail
import com.example.shared.data.Task
import com.example.shared.data.User
import com.example.test.shared.data.FakeData
import com.example.test.shared.data.MainCoroutineRule
import com.example.test.shared.data.runBlockingTest
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var taskDao: TaskDao

    @Before
    fun setupDb() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            )
            .allowMainThreadQueries()
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()

        userDao = database.userDao()
        taskDao = database.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun getTasks_whenNoTaskInserted() = coroutineRule.runBlockingTest {
        val tasks: List<TaskDetail> = taskDao.getTaskDetails().first()
        assertThat(tasks).isEmpty()
    }

    @Test
    fun getTaskDetails_whenNewTaskInserted() = coroutineRule.runBlockingTest {
        with(FakeData) {
            userDao.insertUser(user1)
            userDao.insertUser(user2)
            taskDao.insertTasks(listOf(task1, task2))

            val taskDetails = taskDao.getTaskDetails().first()
            val taskDetail1 = taskDetails[0]
            assertThat(taskDetails).hasSize(2)
            assertThat(taskDetail1.id).isEqualTo(task1.id)
        }
    }

    @Test
    fun loadUserTaskDetails_whenNewTasksInserted() = coroutineRule.runBlockingTest {
        with(FakeData) {
            val users = listOf(user1)
            val tasks = listOf(task2, task4)

            insertAll(userDao, taskDao, users, tasks)

            taskDao.loadUserTaskDetails(user1.id).first().let { taskDetails ->
                assertThat(taskDetails).hasSize(2)
                assertThat(taskDetails[0].id).isEqualTo(task2.id)
                assertThat(taskDetails[0].title).isEqualTo(task2.title)
                assertThat(taskDetails[0].description).isEqualTo(task2.description)
                assertThat(taskDetails[0].createdAt).isEqualTo(task2.createdAt)
                assertThat(taskDetails[0].dueAt).isEqualTo(task2.dueAt)
                assertThat(taskDetails[0].status).isEqualTo(task2.status)

            }
        }
    }


    @Test
    fun getUserById() = coroutineRule.runBlockingTest {
        userDao.insertUser(FakeData.user4)
        userDao.insertUser(FakeData.user3)

        val user = userDao.findUserById(FakeData.user4.id)
        assertThat(user?.id).isEqualTo(4L)
    }

    companion object {
        suspend fun insertAll(
            userDao: UserDao,
            taskDao: TaskDao,
            users: List<User>,
            tasks: List<Task>
        ) {
            insertUsers(userDao, users)
            insertTasks(taskDao, tasks)
        }

        suspend fun insertUsers(
            userDao: UserDao,
            users: List<User>
        ) {
            users.forEach { userDao.insertUser(it) }

        }

        suspend fun insertTasks(
            taskDao: TaskDao,
            tasks: List<Task>
        ) {
            taskDao.insertTasks(tasks)
        }
    }
}