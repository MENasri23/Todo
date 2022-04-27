package com.example.test.shared.data

import com.example.model.entity.Category
import com.example.model.entity.Task
import com.example.model.entity.TaskStatus
import com.example.model.entity.User

object FakeData {

    val user1 = User(
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

    val users = listOf(
        user1,
        user2,
        user3,
        user4,
    )

    val category1 = Category(
        id = 1L,
        name = "Personal",
        ownerId = user1.id
    )

    val category2 = Category(
        id = 2L,
        name = "Business",
        ownerId = user1.id
    )

    val category3 = Category(
        id = 3L,
        name = "Company",
        ownerId = user3.id
    )

    val category4 = Category(
        id = 3L,
        name = "Personal",
        ownerId = user4.id
    )

    val categories = listOf(
        category1,
        category2,
        category3,
        category4
    )

    val task1 = Task(
        id = "1",
        title = "Task 1",
        status = TaskStatus.Todo,
        ownerId = user2.id,
        categoryId = category1.id
    )

    val task2 = Task(
        id = "2",
        title = "Task 2",
        status = TaskStatus.Done,
        ownerId = user1.id,
        categoryId = category1.id
    )

    val task3 = Task(
        id = "3",
        title = "Task 3",
        status = TaskStatus.Doing,
        ownerId = 3L,
        categoryId = category3.id
    )

    val task4 = Task(
        id = "4",
        title = "Task 4",
        status = TaskStatus.Done,
        ownerId = 1L,
        categoryId = category1.id
    )

    val task5 = Task(
        id = "5",
        title = "Task 5",
        status = TaskStatus.Doing,
        ownerId = user3.id,
        categoryId = category3.id
    )

    val task6 = Task(
        id = "6",
        title = "Task 6",
        status = TaskStatus.Done,
        ownerId = user4.id,
        categoryId = category4.id
    )

    val tasks = listOf(
        task1,
        task2,
        task3,
        task4,
        task5,
        task6,
    )
}