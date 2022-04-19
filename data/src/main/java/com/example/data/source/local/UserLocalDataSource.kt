package com.example.data.source.local

import com.example.model.entity.User

interface UserLocalDataSource {

    suspend fun saveUser(user: User): Long

    suspend fun findUserById(userId: Long): User?

    suspend fun removeUser(user: User): Int
}