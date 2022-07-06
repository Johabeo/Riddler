package com.example.riddler.data.model

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    val playerId = "player_id"
    val playerName = "player_name"
    val score = 3522
    val submittedAnswer = false
    val underTest = Player(
        playerId = playerId,
        playerName = playerName,
        score = score,
        submittedAnswer = submittedAnswer,
    )

    @Test
    fun `make sure the player getPlayer() returns the correct player`() {
        assertEquals(playerId, underTest.playerId)
        assertEquals(playerName, underTest.playerName)
        assertEquals(score, underTest.score)
        assertEquals(submittedAnswer, underTest.submittedAnswer)

    }
}