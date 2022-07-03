package com.example.riddler

import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
@RelaxedMockK
class ApiViewModelTest {
    val repo = mockk<TriviaRepo>()
    val tQ = mockk<TriviaQuestions>()
    val underTest = ApiViewModel(
        repo = repo,
    )
    val quiz1 = Quiz("Joe", "Best Quize", "people Around the World", "General", 23)
    val triviaQuestions1 = TriviaQuestions(1, listOf())

    @Test
    fun toGetTrivia() {
        val repo = mockk<TriviaRepo>()
        val underTest = ApiViewModel(
            repo = repo,
        )

    }
}