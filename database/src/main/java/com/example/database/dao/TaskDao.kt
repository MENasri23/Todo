package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shared.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: Task): String

    @Insert
    suspend fun insertTasks(tasks: List<Task>)

    @Query("SELECT * FROM tasks AS t WHERE t.owner_id = :userId")
    fun loadUserTasks(userId: Long): Flow<List<Task>>
}
