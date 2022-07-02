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
    var fakeList :List<Quiz> = (listOf<Quiz>(
        Quiz("G1","fromtest","","",1)

    ))
    val fakelist2 : List<Questions> = (listOf<Questions>(
        Questions(1,"What's your group", "G1"
            ,"G2","G3","G4","G1")
    ))

    var observableFakelist = Observable.fromArray((fakeList))

    val myQuiz:Quiz = Quiz("G1","Our Team","test1",
        "test to insert quiz",1)
    val myFavQuiz = FavoriteQuiz(
        1,1,1)
    val questions = Questions(
        1,"whats your group","G1"
        ,"G2","G3","Dont know"
        ,"G1",1)

    @Test
    /*getFavoriteQuizzes(user: Int): Observable<List<Quiz>> {
        return dao.getFavoriteQuizzes(user)*/
    fun getFavoriteQuizzes(){
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        coEvery {  underTest.getFavoriteQuizzes(1) } returns observableFakelist
        assertEquals(observableFakelist,underTest.getFavoriteQuizzes(1))

    }
    @Test
    /*getMyQuizzes(user: Int): Observable<List<Quiz>> {
        return dao.getMyQuizzes(user)*/
    fun getMyQuizzes(){
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        coEvery {  underTest.getMyQuizzes(1) }returns observableFakelist
        assertEquals(observableFakelist,underTest.getMyQuizzes(1))

    }

    @Test
    fun getQuizQuestion() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        val fakelist2 : List<Questions> = (listOf<Questions>(Questions(1,"What's your group", "G1","G2","G3","G4","G1")
        ))

        every { underTest.getQuizQuestion(1) } returns fakelist2

        assertEquals(fakelist2,underTest.getQuizQuestion(1) )
    }

    @Test
    /*insertQuiz(quiz: Quiz): Long {
        return dao.insertQuiz(quiz)*/
    fun test_for_insertQuiz() {
        val dao = mockk<QuizDao>()

        val underTest = QuizRepository(
            dao = dao,
        )

        every { underTest.insertQuiz(myQuiz) } returns 0
        assertEquals(0,underTest.insertQuiz(myQuiz))

    }
    @Test
    //insertFavoriteQuiz(myFavQuiz)
    fun test_for_insertFavoriteQuiz() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.insertFavoriteQuiz(myFavQuiz) } returns Unit
        dao.insertFavoriteQuiz(myFavQuiz)
        assertEquals(Unit,underTest.insertFavoriteQuiz(myFavQuiz))

    }
    @Test
    //insertQuestions(questions: Questions)
    fun test_for_insertQuestions() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        val questions = Questions(
            1,"whats your group","G1"
            ,"G2","G3","Dont know"
            ,"G1",1)

        every { underTest.insertQuestions(questions) } returns 0
        assertEquals(0,underTest.insertQuestions(questions))

    }
}

private infix fun <T, B> MockKStubScope<T, B>.returns(myFunc: suspend (FavoriteQuiz) -> B) {}

private infix fun <T, B> MockKStubScope<T, B>.returns(i: Int) {}

private infix fun <T, B> MockKStubScope<T, B>.returns(unit: Unit) {}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {}
