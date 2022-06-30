package com.example.riddler.data.repo

import androidx.lifecycle.LiveData
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.model.FavoriteQuiz
import com.example.riddler.data.model.Quiz
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.*

import org.junit.Test
@RelaxedMockK
class QuizRepositoryTest() {
    val dao = mockk<QuizDao>(relaxed = true)
    val underTest = QuizRepository(
        dao = dao,
    )

    @Test
    fun getFavoriteQuizzes(){
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.getFavoriteQuizzes(1) } returnsMany listOf()


    }

    @Test
    fun getQuizQuestion() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.getQuizQuestion(1) } just Runs


    }

    @Test
    fun insertQuiz() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
       every { underTest.insertQuiz(quiz = Quiz(1,1,"test1",
           "test to insert quiz","regular")) } just Runs
    }

    @Test
    fun insertFavoriteQuiz() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )

    }

    @Test
    fun insertQuestions() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { dao.getFavoriteQuizzes(more(1)) }

    }
}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
