package com.example.riddler

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

@RelaxedMockK
class TriviaRepoTest{

    val inter = mockk<RetroApiInterface>()
    val underTest = TriviaRepo(
        inter = inter,
    )

    @Test
    fun toGetAllTriviaQuestionsFromRepo() {
        coEvery { underTest.getAllTriviaQuestions(6, 1, "Hard") } just Runs
    }
    @Test
     fun testGetAllTriviaQuestionsFromApiInterface(){
        coEvery { inter.getAllTriviaQuestions(6,1,"Hard") } just Runs

    }

    @Test
    fun testGetAllTriviaQuestionsFromRepoWithType(){
        coEvery { underTest.getAllTriviaQuestions(6,1,"Hard","Coding") } just Runs
    }

    @Test
    fun testGetAllTriviaQuestionsFromApiInterfaceWithType(){
        coEvery { underTest.getAllTriviaQuestions(6,1,"Hard","Coding") } just Runs
    }

}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
