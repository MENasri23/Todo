package com.example.model.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.time.Instant
import java.util.*

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = CASCADE
        )
    ]
)
data class Task(

    /**
     * the unique random id to store task in database.
     */
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    /**
     * The title of the task.
     */
    val title: String,

    val description: String? = null,

    /**
     * Hold the current state of the task.
     */
    val status: TaskStatus = TaskStatus.Todo,

    /**
     * the user who is the owner of the task.
     */
    @ColumnInfo(name = "owner_id", index = true)
    val ownerId: Long,

    /**
     * When the task was created.
     */
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Instant.now(),

    /**
     * When to do it.
     */
    @ColumnInfo(name = "due_at")
    val dueAt: Instant? = null,
)


enum class TaskStatus {
    Todo,
    Doing,
    Done
}