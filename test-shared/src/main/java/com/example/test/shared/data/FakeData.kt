package com.example.test.shared.data

import com.example.shared.data.Task
import com.example.shared.data.TaskStatus
import com.example.shared.data.User

object FakeData {

    val user1 = User(
        id = 1L,
        email = "me@email.com",
        firstName = "Mohammad",
    )

    val user2 = User(
        id = 2L,
        firstName = "Ezkar",
        email = "you@email.com",
        lastName = "nasri",
        avatar = ""
    )

    val task1 = Task(
        id = "1",
        title = "Task 1",
        status = TaskStatus.Todo,
        ownerId = 2L,
    )

    val task2 = Task(
        id = "2",
        title = "Task 2",
        status = TaskStatus.Done,
        ownerId = 1L
    )
}