package com.example.data.source.local.category

import com.example.database.dao.CategoryDao
import com.example.model.entity.Category
import kotlinx.coroutines.flow.Flow

class DefaultCategoryDataSource(
    private val categoryDao: CategoryDao
) : CategoryDataSource {

    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category)
    }

    override fun getUserCategories(userid: Long): Flow<List<Category>> {
        return categoryDao.loadUserCategories(userid)
    }
}