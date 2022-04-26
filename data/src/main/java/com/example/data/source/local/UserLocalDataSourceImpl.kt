package com.example.data.source.local

import com.example.database.dao.UserDao
import com.example.model.entity.User
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun saveUser(user: User) = userDao.insertUser(user)

    override suspend fun findUserById(userId: Long) = userDao.findUserById(userId)

    override suspend fun removeUser(user: User) = userDao.removeUser(user)
}