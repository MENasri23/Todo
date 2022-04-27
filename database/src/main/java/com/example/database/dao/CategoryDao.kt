package com.example.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Query("SELECT * FROM categories WHERE owner_id = :userId")
    fun loadUserCategories(userId: Long): Flow<List<Category>>

    @Delete
    suspend fun removeCategory(category: Category): Long
}