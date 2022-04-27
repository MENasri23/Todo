package com.example.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = CASCADE
        )
    ]
)
data class Category(
    @PrimaryKey() // TODO: Add auto generate flag!
    val id: Long,

    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Instant.now(),

    @ColumnInfo(name = "owner_id", index = true)
    val ownerId: Long
)
