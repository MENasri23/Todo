package com.example.database.dao

import androidx.room.*
import com.example.model.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun findUserById(userId: Long): User?

    @Delete
    suspend fun removeUser(user: User): Int


}