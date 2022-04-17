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
import com.example.test.shared.data.FakeData
import com.example.test.shared.data.MainCoroutineRule
import com.example.test.shared.data.runBlockingTest
import com.google.common.truth.Truth
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
        Truth.assertThat(tasks).isEmpty()
    }

    @Test
    fun getTaskDetails_whenNewTaskInserted() = coroutineRule.runBlockingTest {
        with(FakeData) {
            userDao.insertUser(user1)
            userDao.insertUser(user2)
            taskDao.insertTasks(listOf(task1, task2))

            val taskDetails = taskDao.getTaskDetails().first()
            val taskDetail1 = taskDetails[0]
            Truth.assertThat(taskDetails).hasSize(2)
            Truth.assertThat(taskDetail1.id).isEqualTo(task1.id)
        }
    }
}