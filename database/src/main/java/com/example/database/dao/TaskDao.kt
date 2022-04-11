package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.view.TaskDetail
import com.example.shared.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)

    @Query("SELECT * FROM tasks AS t WHERE t.owner_id = :userId")
    fun loadUserTaskDetails(userId: Long): Flow<List<TaskDetail>>

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskDetail>>
}
