package com.example.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.database.AppDatabase
import com.example.test.shared.data.FakeData
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
object DatabaseModule {

    private lateinit var database: AppDatabase

    private const val DATABASE_NAME = "todo-db"

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            // TODO: Remove this callback in production
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch {
                        insertFakeData()
                    }
                }
            })
            .build().also { database = it }
    }


    suspend fun insertFakeData() {
        with(database) {
            val userDao = userDao()
            for (user in FakeData.users) {
                userDao.insertUser(user)
            }
            taskDao().insertTasks(FakeData.tasks)
        }

    }

    @Provides
    fun provideTaskDao(database: AppDatabase) = database.taskDao()

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()
}