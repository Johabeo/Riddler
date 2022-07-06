package com.example.riddler.data.repo

import androidx.lifecycle.LiveData
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
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

    val fakeList : List<Quiz> = listOf<Quiz>(Quiz("G1", "fromTest","quiq for testing","general","Easy"))

    val fakeQuestions : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))

    var observableFakeList = Observable.fromArray(fakeList)
    val questions = Questions(
        1,"whats your group","G1"
        ,"G2","G3","Dont know"
        ,"G1",1)

    //List of questions
    val listOfQuestions1 : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))
    val quiz = Quiz("Jay","From test","Atest Question from test","General","Easy")
    val quiz2 = Quiz("G1","Our Team","test1",
        "test to insert quiz","Easy")
    val quiz3 = Quiz("G1","Our Team","test1",
        "test to insert quiz","Easy")



    @Test
    fun `test if function getMyQuizzes() will return a list of quizzes`() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        val observableFakeList =
            Observable.fromArray(fakeList)

        coEvery {  underTest.getQuizzes(1,0) } returns observableFakeList
        val result = observableFakeList.blockingFirst()
        assertEquals(fakeList[0].id, result[0].id)

    }

    @Test
    fun `test to see if getQuizQuestion() will return a list of questions`() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.getQuizQuestion(1) } returns listOfQuestions1
        val result = underTest.getQuizQuestion(1)
        assertEquals(listOfQuestions1[0].id, result[0].id)

    }

    @Test
    fun `test if the function insertQuiz() will insert a quiz`() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
       every { underTest.insertQuiz(quiz2) } returns 0
        val result = underTest.insertQuiz(quiz2)
        assertEquals(0, result)

    }

    @Test
    fun `test to see if insertQuiz() will inserts a quiz`() {
        val appDatabase = mockk<AppDatabase>()

        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.insertQuiz(quiz)}returns 0
           val result = underTest.insertQuiz(quiz)
        assertEquals(0, result)

    }

    @Test
    fun `test to see if insertQuestions() will insert a questions`() {
        val dao = mockk<QuizDao>()
        val underTest = QuizRepository(
            dao = dao,
        )
        every { underTest.insertQuestions(questions) } returns Unit
        underTest.insertQuestions(questions)
        verify { dao.insertQuestions(questions) }

    }
}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
