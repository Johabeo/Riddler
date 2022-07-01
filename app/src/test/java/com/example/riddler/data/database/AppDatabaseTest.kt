package com.example.riddler.data.database

import com.example.riddler.data.dao.QuizDao
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
        every { dao.getQuizQuestions(1) } just Runs

    }
}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
