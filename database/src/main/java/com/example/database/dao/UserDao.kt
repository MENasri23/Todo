package com.example.database.dao

import androidx.room.*
import com.example.model.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: com.example.model.entity.User): Long

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun findUserById(userId: Long): com.example.model.entity.User?

    @Delete
    suspend fun removeUser(user: com.example.model.entity.User): Int


}