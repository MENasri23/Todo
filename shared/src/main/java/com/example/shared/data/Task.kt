package com.example.shared.data

import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.Instant
import java.util.*

@Entity(
    tableName = "tasks",
)
class Task(

    /**
     * the unique random id to store task in database.
     */
    val id: String = UUID.randomUUID().toString(),

    /**
     * The title of the task.
     */
    val title: String,

    val description: String? = null,

    /**
     * Hold the current state of the task.
     */
    val status: TaskStatus,

    /**
     * the user who is the owner of the task.
     */
    val ownerId: String,

    /**
     * When the task was created.
     */
    val createdAt: Instant = Instant.now(),

    /**
     * When to do it.
     */
    val dueAt: Instant? = null,
)


enum class TaskStatus {
    Todo,
    Doing,
    Done
}