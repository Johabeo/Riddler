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
    val question1: TriviaQuestions.Question = TriviaQuestions.Question("Science:Computers"
        ,"Multiple Choice","Easy","what is JVM"
        ,"Java Virtual Machine", listOf("Joint Mechanical Machine", "Java Volatile Memory", "JSON Volume Memory"))
    val question2: TriviaQuestions.Question = TriviaQuestions.Question("Science:Computers",
        "Multiple Choice","Easy","what is JVM"
        ,"Java Virtual Machine", listOf("Joint Mechanical Machine", "Java Volatile Memory", "JSON Volume Memory"))
    val question3: TriviaQuestions.Question = TriviaQuestions.Question("Science:Computers", "Multiple Choice","Easy","what is JVM"
        ,"Java Virtual Machine", listOf("Joint Mechanical Machine", "Java Volatile Memory", "JSON Volume Memory"))

    val triviaQuestions: List<TriviaQuestions> = listOf(TriviaQuestions(1,listOf(question2,question3)))

    val triviaList = listOf(TriviaQuestions(1,listOf(question1)))

    @Test
    fun `test to return all trivia questions as a list`(){

        coEvery { underTest.getAllTriviaQuestions(1,1,"Easy") } returns triviaQuestions
        val result = runBlocking { underTest.getAllTriviaQuestions(1,1,"Easy") }
        assertEquals(triviaQuestions, result)


    }
    @Test
     fun `test to Get All TriviaQuestions From Api Interface`(){
        coEvery { inter.getAllTriviaQuestions(1,1,"Hard") } just Runs

    }

    @Test
    fun testGetAllTriviaQuestionsFromRepoWithType(){
        coEvery { underTest.getAllTriviaQuestions(1,1,"Hard","Coding") } just Runs
    }

    @Test
    fun testGetAllTriviaQuestionsFromApiInterfaceWithType(){
        coEvery { underTest.getAllTriviaQuestions(1,1,"Hard","Coding") } just Runs
    }

}

private infix fun <T, B> MockKStubScope<T, B>.just(runs: Runs) {

}
