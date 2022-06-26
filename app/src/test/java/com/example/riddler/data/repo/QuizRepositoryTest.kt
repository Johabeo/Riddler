package com.example.riddler.data.repo

import com.example.riddler.data.dao.QuizDao
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Test
@RelaxedMockK
class QuizRepositoryTest {
    val dao = mockk<QuizDao>(relaxed = true)
    val underTest = QuizRepository(
        dao = dao,
    )


    @Test
    fun getQuizQuestion() {
    }

    @Test
    fun insertQuiz() {
    }

    @Test
    fun insertFavoriteQuiz() {
    }

    @Test
    fun insertQuestions() {
    }
}