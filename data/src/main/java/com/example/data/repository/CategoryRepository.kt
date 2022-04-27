package com.example.data.repository

import com.example.data.di.IODispatcher
import com.example.data.source.local.category.CategoryLocalDataSource
import com.example.data.util.toResource
import com.example.model.entity.Category
import com.example.model.entity.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val localDataSource: CategoryLocalDataSource,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun saveCategory(category: Category) = withContext(dispatcher) {
        localDataSource.insertCategory(category)
    }

    fun getUserCategoriesFlow(userId: Long) = localDataSource.getUserCategories(userId)
        .distinctUntilChanged()
        .toResource()
        .flowOn(dispatcher)

    suspend fun removeCategory(category: Category) = withContext(dispatcher) {
        localDataSource.removeCategory(category)
    }
}