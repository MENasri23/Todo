package com.example.database.dao

import androidx.room.*
import com.example.database.view.TaskDetail
import com.example.model.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: com.example.model.entity.Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<com.example.model.entity.Task>)

    @Transaction
    @Query("SELECT * FROM task_detail AS t WHERE t.owner_id = :userId")
    fun loadUserTaskDetails(userId: Long): Flow<List<TaskDetail>>

    @Transaction
    @Query("SELECT * FROM task_detail")
    fun getTaskDetails(): Flow<List<TaskDetail>>

    @Query("SELECT * FROM task_detail WHERE id = :id ")
    fun findTaskDetailById(id: String): Flow<TaskDetail?>

    @Delete
    suspend fun remove(task: com.example.model.entity.Task): Int

}
