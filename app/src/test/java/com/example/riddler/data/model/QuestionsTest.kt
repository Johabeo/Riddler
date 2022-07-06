package com.example.riddler.data.model

import com.example.riddler.TriviaQuestions
import org.junit.Assert.*
import org.junit.Test

class QuestionsTest{
    @Test
    fun `check if questions called are the same as received from the api`() {
        val quizId = 2599
        val question = "question"
        val firstAnswer = "first_answer"
        val secondAnswer = "second_answer"
        val thirdAnswer = "third_answer"
        val fourthAnswer = "fourth_answer"
        val correctAnswer = "correct_answer"
        val id = 3279
        val underTest = Questions(
            quizId = quizId,
            question = question,
            firstAnswer = firstAnswer,
            secondAnswer = secondAnswer,
            thirdAnswer = thirdAnswer,
            fourthAnswer = fourthAnswer,
            correctAnswer = correctAnswer,
            id = id,
        )

        assertEquals(quizId, underTest.quizId)
        assertEquals(question, underTest.question)
        assertEquals(firstAnswer, underTest.firstAnswer)
        assertEquals(secondAnswer, underTest.secondAnswer)
        assertEquals(thirdAnswer, underTest.thirdAnswer)
        assertEquals(fourthAnswer, underTest.fourthAnswer)
        assertEquals(correctAnswer, underTest.correctAnswer)
        assertEquals(id, underTest.id)

    }


}