package com.example.riddler

import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Test

class ApiViewModelTest {

    @Test
    fun getAllTriviaQuestions() {
        val repo = mockk<TriviaRepo>()
        val underTest = ApiViewModel(
            repo = repo,
        )

    }
}