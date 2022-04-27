package com.example.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.database.dao.CategoryDao
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import com.example.database.view.TaskDetail
import com.example.model.entity.Category
import com.example.model.entity.Task
import com.example.model.entity.User
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
    private lateinit var categoryDao: CategoryDao

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
        categoryDao = database.categoryDao()
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
            insertData(listOf(user1, user2), listOf(task1, task2), listOf(category1))

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
            val categories = listOf(category1)

            insertData(users, tasks, categories)

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
        insertUsers(userDao, listOf(FakeData.user4, FakeData.user3))

        val user = userDao.findUserById(FakeData.user4.id)
        assertThat(user?.id).isEqualTo(4L)
    }

    @Test
    fun getUserTaskDetails_whenSomeTaskRemoved() = coroutineRule.runBlockingTest {
        val users = listOf(FakeData.user1, FakeData.user4)
        val tasks = listOf(FakeData.task2, FakeData.task4, FakeData.task6)
        val categories = listOf(FakeData.category1, FakeData.category4)

        insertData(users, tasks, categories)

        taskDao.removeTasks(listOf(FakeData.task2))

        val userTaskDetails = taskDao
            .loadUserTaskDetails(FakeData.user1.id)
            .first()

        assertThat(userTaskDetails).hasSize(1)

    }

    @Test
    fun removeTask_WhenTaskTableIsEmpty() = coroutineRule.runBlockingTest {
        val removedCount = taskDao.removeTasks(listOf(FakeData.task1))
        assertThat(removedCount).isEqualTo(0)
    }

    @Test
    fun removeUserTasks_whenUserDeleted() = coroutineRule.runBlockingTest {
        val user = FakeData.user1
        val tasks = listOf(FakeData.task2, FakeData.task4)
        val categories = listOf(FakeData.category1, FakeData.category4)

        insertData(listOf(user), tasks, categories)

        val removedCount = userDao.removeUser(user)
        val currentUserTaskDetails = taskDao.loadUserTaskDetails(user.id).first()

        assertThat(removedCount).isEqualTo(1)
        assertThat(currentUserTaskDetails).isEmpty()

    }

    @Test
    fun removeTasks_taskTableIsNotEmpty() = coroutineRule.runBlockingTest {
        val user = FakeData.user1
        val tasks = listOf(FakeData.task2, FakeData.task4)
        val categoreis = listOf(FakeData.category1, FakeData.category4)

        insertData(listOf(user), tasks, categoreis)

        assertThat(taskDao.removeTasks(tasks)).isNotEqualTo(0)
    }

    suspend fun insertData(
        users: List<User>,
        tasks: List<Task>,
        categories: List<Category>
    ) {
        insertAll(userDao, taskDao, categoryDao, users, tasks, categories)
    }

    companion object {
        suspend fun insertAll(
            userDao: UserDao,
            taskDao: TaskDao,
            categoryDao: CategoryDao,
            users: List<User>,
            tasks: List<Task>,
            categories: List<Category>
        ) {
            insertUsers(userDao, users)
            insertTasks(taskDao, tasks)
            insertCategories(categoryDao, categories)
        }

        suspend fun insertUsers(
            userDao: UserDao,
            users: List<com.example.model.entity.User>
        ) {
            users.forEach { userDao.insertUser(it) }

        }

        suspend fun insertTasks(
            taskDao: TaskDao,
            tasks: List<Task>
        ) {
            taskDao.insertTasks(tasks)
        }

        suspend fun insertCategories(
            categoryDao: CategoryDao,
            categories: List<Category>
        ) {
            categories.forEach { categoryDao.insertCategory(it) }
        }
    }
}