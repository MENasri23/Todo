package com.example.database.dao

import androidx.room.*
import com.example.model.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Query("SELECT * FROM categories WHERE owner_id = :userId")
    fun loadUserCategories(userId: Long): Flow<List<Category>>

    @Delete
    suspend fun removeCategory(category: Category): Int
}