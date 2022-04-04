package com.example.database

import androidx.room.TypeConverter
import com.example.shared.data.TaskStatus
import java.time.Instant

class DatabaseTypeConverters {

    @TypeConverter
    fun longToInstant(value: Long): Instant {
        return Instant.ofEpochMilli(value)
    }

    @TypeConverter
    fun instantToLong(instant: Instant): Long {
        return instant.toEpochMilli()
    }

    @TypeConverter
    fun taskStatusToInt(taskStatus: TaskStatus): Int {
        return taskStatus.ordinal
    }

    @TypeConverter
    fun intToTaskStatus(value: Int): TaskStatus {
        return TaskStatus.values()[value]
    }


}