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
    fun getAllTriviaQuestions() {
        val repo = mockk<TriviaRepo>()
        val underTest = ApiViewModel(
            repo = repo,
        )

    }

}

private infix fun Unit.Just(runs: Runs) {

}
