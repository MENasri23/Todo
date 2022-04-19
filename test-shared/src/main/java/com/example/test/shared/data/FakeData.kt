package com.example.test.shared.data

import com.example.model.entity.Task
import com.example.model.entity.TaskStatus
import com.example.model.entity.User

object FakeData {

    val user1 = com.example.model.entity.User(
        id = 1L,
        email = "me@email.com",
        firstName = "User1",
    )

    val user2 = User(
        id = 2L,
        firstName = "User2",
        email = "you@email.com",
        lastName = "nasri",
        avatar = ""
    )

    val user3 = User(
        id = 3L,
        email = "me@email.com",
        firstName = "User3",
    )

    val user4 = User(
        id = 4L,
        firstName = "User4",
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

    val task3 = Task(
        id = "3",
        title = "Task 3",
        status = TaskStatus.Doing,
        ownerId = 3L,
    )

    val task4 = Task(
        id = "4",
        title = "Task 4",
        status = TaskStatus.Done,
        ownerId = 1L
    )

    val task5 = Task(
        id = "5",
        title = "Task 5",
        status = TaskStatus.Doing,
        ownerId = 3L,
    )

    val task6 = Task(
        id = "6",
        title = "Task 6",
        status = TaskStatus.Done,
        ownerId = 4L
    )
}