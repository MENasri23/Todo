package com.example.data.repository

import com.example.test.shared.data.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class TaskRepositoryTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()


}