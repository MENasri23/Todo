package com.example.data.source.local.category

import com.example.model.entity.Category
import com.example.model.entity.User
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {

    suspend fun insertCategory(category: Category): Long

    fun getUserCategories(userid: Long): Flow<List<Category>>

}