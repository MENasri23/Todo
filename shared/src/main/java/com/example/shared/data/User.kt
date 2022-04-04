package com.example.shared.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey // TODO: Define with auto generated property
    val id: Long,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "username")
    val username: String? = null,

    @ColumnInfo(name = "first_name")
    val firstName: String? = null,

    @ColumnInfo(name = "last_name")
    val lastName: String? = null,

    @ColumnInfo(name = "image_url")
    val avatar: String? = null
)