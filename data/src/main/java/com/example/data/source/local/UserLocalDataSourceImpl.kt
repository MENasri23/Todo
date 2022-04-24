package com.example.data.source.local

import com.example.data.di.IODispatcher
import com.example.database.dao.UserDao
import com.example.model.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserLocalDataSource {

    override suspend fun saveUser(user: User) = withContext(dispatcher) {
        userDao.insertUser(user)
    }

    override suspend fun findUserById(userId: Long) = withContext(dispatcher) {
        userDao.findUserById(userId)
    }

    override suspend fun removeUser(user: User) = withContext(dispatcher) {
        userDao.removeUser(user)
    }
}