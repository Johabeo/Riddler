package com.example.riddler.data.model

import com.example.riddler.TriviaQuestions
import com.google.android.datatransport.cct.internal.LogResponse.fromJson
import io.mockk.Answer
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class QuizGameTest {
    val createdTime = 67943L
    val finished = true
    val pin = "pin"
    val currentQuestion = 1034
    val numAnswered = 901
    val question = mockk<Questions>()
    val hostQuit = true
    val displayingLeaderboard = true
    val displayingResult = false
    val player = mockk<Player>()
    val players = listOf(player)
    val underTest = QuizGame(
        createdTime = createdTime,
        finished = finished,
        pin = pin,
        currentQuestion = currentQuestion,
        numAnswered = numAnswered,
        question = question,
        hostQuit = hostQuit,
        displayingLeaderboard = displayingLeaderboard,
        displayingResult = displayingResult,
        players = players,
    )
    val json = """
        {
            "createdTime": $createdTime,
            "finished": $finished,
            "pin": "$pin",
            "currentQuestion": $currentQuestion,
            "numAnswered": $numAnswered,
            "question": $question,
            "hostQuit": $hostQuit,
            "displayingLeaderboard": $displayingLeaderboard,
            "displayingResult": $displayingResult,
            "players": $players
        }
    """.trimIndent()
    @Test
    fun `should return correct quiz game`() {
        val expected = QuizGame(
            createdTime = createdTime,
            finished = finished,
            pin = pin,
            currentQuestion = currentQuestion,
            numAnswered = numAnswered,
            question = question,
            hostQuit = hostQuit,
            displayingLeaderboard = displayingLeaderboard,
            displayingResult = displayingResult,
            players = players,
        )
        assertEquals(expected, underTest)



    }
}