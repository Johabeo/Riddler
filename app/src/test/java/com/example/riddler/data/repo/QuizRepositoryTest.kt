package com.example.riddler.data.repo

import androidx.lifecycle.LiveData
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.model.FavoriteQuiz
import com.example.riddler.data.model.Questions
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
        coEvery {  underTest.getFavoriteQuizzes(1) } returnsMany listOf()

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
       every { underTest.insertQuiz(quiz = Quiz("G1","Our Team","test1",
           "test to insert quiz",1)) } just Runs
    }

    @Test
    fun insertFavoriteQuiz() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.insertFavoriteQuiz(favoriteQuiz = FavoriteQuiz(
            1,1,1))}just Runs

    }

    @Test
    fun insertQuestions() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.insertQuestions(questions = Questions(
            1,"whats your group","G1"
            ,"G2","G3","Dont know"
            ,"G1",1)) } just Runs

    }
}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
