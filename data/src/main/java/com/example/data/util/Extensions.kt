package com.example.data.util

import Resource
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.toResource(): Flow<Resource<T>> = flow {
    map {
        Resource.success(it)
    }.onStart {
        emit(Resource.loading())
    }.catch { cause ->
        emit(Resource.error(cause))
    }
}