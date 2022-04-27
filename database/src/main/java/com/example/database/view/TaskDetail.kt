package com.example.database.view

import androidx.room.DatabaseView
import androidx.room.Embedded
import com.example.model.entity.Category
import com.example.model.entity.TaskStatus
import com.example.model.entity.User
import java.time.Instant

@DatabaseView(
    """
        SELECT
            t.id, t.title, t.status, t.description, t.created_at AS createdAt, 
            t.due_at AS dueAt,
            u.id AS owner_id, u.username AS owner_username, u.first_name AS owner_first_name,
            u.last_name AS owner_last_name, u.email AS owner_email, u.image_url AS owner_image_url,
            c.id AS category_id, c.name AS category_name, c.created_at AS category_created_at, c.owner_id AS category_owner_id
        FROM tasks AS t 
        INNER JOIN users AS u ON u.id = t.owner_id
        INNER JOIN categories AS c ON c.id = t.category_id
    """,
    viewName = "task_detail"
)
data class TaskDetail(
    val id: String,

    val title: String,

    val description: String?,

    val status: TaskStatus,

    @Embedded(prefix = "owner_")
    val user: User,

    @Embedded(prefix = "category_")
    val category: Category,

    val createdAt: Instant,

    val dueAt: Instant?,
)