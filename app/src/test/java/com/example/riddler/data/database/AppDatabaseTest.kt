package com.example.riddler.data.database

import android.app.Application
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.di.AppModule
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.*

import org.junit.Test

@RelaxedMockK
class AppDatabaseTest {
    val underTest = mockk<AppDatabase>()
    val dao = mockk<QuizDao>()
    val appModule = mockk<AppModule>()
    val application = mockk<Application>()
    val questions = mockk<Questions>()
    val quiz = mockk<Quiz>()
    val quizList = mockk<List<Quiz>>()
    val fakeList : List<Quiz> = listOf<Quiz>(Quiz("G1", "fromTest","quiq for testing","general","Easy"))

    val fakeQuestions : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))

    var observableFakeList = Observable.fromArray(fakeList)
    //List of questions
    val listOfQuestions1 : List<Questions> = listOf<Questions>(Questions(1, "fromTest","G3","G2","G1","G4","G1",1))




    @Test
    fun `test that quizDao function returns dao`() {
        val database = mockk<AppDatabase>()
        val dao = mockk<QuizDao>()
        val application = mockk<Application>()
        val appModule = mockk<AppModule>()
    //quiz
        val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")
    //Questions
        val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")


        every { database.quizDao() } returns dao
        assertEquals(dao, database.quizDao())
    }

       @Test
       fun `test that insertQuiz function inserts quiz and returns Long`() {
           val database = mockk<AppDatabase>()
           val dao = mockk<QuizDao>()
           val application = mockk<Application>()
           val appModule = mockk<AppModule>()

         //quiz
              val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")
            //Questions
                val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")
           every { dao.insertQuiz(quiz1) }returns 0 //inserts quiz
              every { database.quizDao() } returns dao
                assertEquals(0, database.quizDao().insertQuiz(quiz1))
         }


            @Test
            fun `test that getQuizzes function returns quiz`() {
                val database = mockk<AppDatabase>()
                val dao = mockk<QuizDao>()
                val application = mockk<Application>()
                val appModule = mockk<AppModule>()
              //quiz
                 val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")
               //Questions
                //Observable<List<Quiz>>

                   val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")
                every { dao.getQuizzes(1,0) } returns observableFakeList

            }

                @Test
                fun `test that getQuizById function returns quiz`() {
                    val database = mockk<AppDatabase>()
                    val dao = mockk<QuizDao>()
                    val application = mockk<Application>()
                    val appModule = mockk<AppModule>()
                  //quiz
                     val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")
                   //Questions
                       val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")

                    every { dao.getQuizQuestions(1) }returns listOfQuestions1
                    assertEquals(listOfQuestions1, dao.getQuizQuestions(1))

                }
         @Test
            fun `test that getQuizBy user id function returns quiz`() {
                val database = mockk<AppDatabase>()
                val dao = mockk<QuizDao>()
                val application = mockk<Application>()
                val appModule = mockk<AppModule>()
            //quiz
                val quiz1 = Quiz("Joe","Best Quize","people Around the World","General","Easy")
                //Questions
                    val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")

                every { dao.getMyQuizzes(1) }returns observableFakeList
                assertEquals(observableFakeList, dao.getMyQuizzes(1))

            }

        @Test
            fun `test that getQuizBy Id function returns the right quiz`() {
            val database = mockk<AppDatabase>()
            val dao = mockk<QuizDao>()
            val application = mockk<Application>()
            val appModule = mockk<AppModule>()
            val questions1: Questions =
                Questions(1, "What's your group", "G1", "G2", "G3", "G4", "G1")

            every { dao.getQuizQuestions(1) } returns listOf(questions1)
            assertEquals(listOf(questions1), dao.getQuizQuestions(1))
        }

        @Test
        fun `test that calling dao returns dao`() {
            val database = mockk<AppDatabase>()
            every { database.quizDao() } returns dao
            val application = mockk<Application>()
            every { application.applicationContext } returns application
        assertEquals(dao, database.quizDao())
        verify { database.quizDao() }
        verify(exactly = 1) { database.quizDao() }

    }
}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
