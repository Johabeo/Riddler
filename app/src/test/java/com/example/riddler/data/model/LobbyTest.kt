package com.example.riddler.data.model

import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class LobbyTest{
    @Test
    fun testLobby(){
        val host = "host"
        val pin = "pin"
        val size = 2979
        val gameStarted = false
        val player = mockk<LobbyPlayers>()
        val players = listOf(player)
        val underTest = Lobby(
            host = host,
            pin = pin,
            size = size,
            gameStarted = gameStarted,
            players = players,
        )
        val lobby = Lobby("test", "test", 4, false, listOf(player))
        assertEquals("test", lobby.host)
        assertEquals("test", lobby.pin)
        assertEquals("test", lobby.host)
    }

    @Test
    fun testLobbyPlayers(){
        val name = "name"
        val underTest = LobbyPlayers("12", name)
        assertEquals("name", underTest.playerName)

    }


}