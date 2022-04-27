package com.example.data.source.local.category

import com.example.model.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun insertCategory(category: Category): Long

    fun getUserCategories(userid: Long): Flow<List<Category>>

    suspend fun removeCategory(category: Category): Int

}