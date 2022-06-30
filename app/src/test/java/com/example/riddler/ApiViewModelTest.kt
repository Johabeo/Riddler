package com.example.riddler

import com.example.riddler.data.model.Questions
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.Test
@RelaxedMockK
class ApiViewModelTest {
    val repo = mockk<TriviaRepo>()
    val Tq = mockk<TriviaQuestions>()
    val underTest = ApiViewModel(
        repo = repo,
    )

    @Test
    fun testGetAllTriviaQuestions() {

        coEvery { underTest.getAllTriviaQuestions(1,1,"Hard", "Coding") } just Runs
    }
}

private infix fun Unit.Just(runs: Runs) {

}
