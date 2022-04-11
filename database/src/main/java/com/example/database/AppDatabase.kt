package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.dao.TaskDao
import com.example.database.dao.UserDao
import com.example.database.view.TaskDetail
import com.example.shared.data.Task
import com.example.shared.data.User

@Database(
    entities = [Task::class, User::class],
    views = [TaskDetail::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}