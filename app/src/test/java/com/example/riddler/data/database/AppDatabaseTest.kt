package com.example.riddler.data.database

import android.app.Application
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.di.AppModule
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*

import org.junit.Test

@RelaxedMockK
class AppDatabaseTest {
    val underTest = mockk<AppDatabase>()
    val dao = mockk<QuizDao>()


    @Test
    fun quizDao() {
        val database = mockk<AppDatabase>()
        val dao = mockk<QuizDao>()
        val application = mockk<Application>()
        val appModule = mockk<AppModule>()
    //quiz
        val quiz1 = Quiz("Joe","Best Quize","people Around the World","General",23)
    //Questions
        val questions1 : Questions = Questions(1,"What's your group", "G1","G2","G3","G4","G1")


        every { dao.getQuizQuestions(23) } returns listOf(questions1)

        assertEquals(listOf(questions1),dao.getQuizQuestions(23))


    }

}