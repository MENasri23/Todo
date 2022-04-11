package com.example.database.dao

import androidx.room.*
import com.example.database.view.TaskDetail
import com.example.shared.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)

    @Transaction
    @Query("SELECT * FROM tasks AS t WHERE t.owner_id = :userId")
    fun loadUserTaskDetails(userId: Long): Flow<List<TaskDetail>>

    @Transaction
    @Query("SELECT * FROM tasks")
    fun getTaskDetails(): Flow<List<TaskDetail>>

    @Query("SELECT * FROM task_detail WHERE id = :id ")
    fun findTaskDetailById(id: String): Flow<TaskDetail?>

}
