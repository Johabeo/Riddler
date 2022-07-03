package com.example.riddler

import androidx.room.Dao
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.repo.QuizRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Test

class TriviaQuestionsTest {
    val response_code = 555
    val result = mockk<TriviaQuestions.Question>()
    val results = listOf(result)
    val underTest = TriviaQuestions(
        response_code = response_code,
        results = results,
    )


    @Test
    //getResponse_code()
    fun test_getResponse_code() {
        val response_code = 4584
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )


    }

    @Test
    fun getResults() {
        val response_code = 4000
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    operator fun component1() {
        val response_code = 782
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    operator fun component2() {
        val response_code = 3029
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    fun copy() {
        val response_code = 1988
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    fun testToString() {
        val response_code = 4502
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    fun testHashCode() {
        val response_code = 348
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }

    @Test
    fun testEquals() {
        val response_code = 4815
        val result = mockk<TriviaQuestions.Question>()
        val results = listOf(result)
        val underTest = TriviaQuestions(
            response_code = response_code,
            results = results,
        )

    }
}