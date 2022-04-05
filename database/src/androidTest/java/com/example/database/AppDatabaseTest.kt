package com.example.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

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
            .build()

        userDao = database.userDao()
        taskDao = database.taskDao()
    }

    @After
    fun closeDb() {
        database.close()
    }



}