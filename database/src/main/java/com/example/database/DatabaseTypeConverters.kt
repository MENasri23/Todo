package com.example.database

import androidx.room.TypeConverter
import com.example.model.entity.TaskStatus
import java.time.Instant

class DatabaseTypeConverters {

    @TypeConverter
    fun longToInstant(value: Long?): Instant? {
        return value?.let { Instant.ofEpochMilli(value) }
    }

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    @TypeConverter
    fun taskStatusToInt(taskStatus: com.example.model.entity.TaskStatus): Int {
        return taskStatus.ordinal
    }

    @TypeConverter
    fun intToTaskStatus(value: Int): com.example.model.entity.TaskStatus {
        return com.example.model.entity.TaskStatus.values()[value]
    }


}