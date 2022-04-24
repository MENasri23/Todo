package com.example.data.util

import com.example.model.result.Resource
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.toResource(): Flow<Resource<T>> =
    map { result ->
        Resource.success(result)
    }.onStart {
        emit(Resource.loading())
    }.catch { cause ->
        emit(Resource.error(cause))
    }



