package com.example.riddler.data.model

data class Lobby(
    val host: String,
    val pin: String,
    val size: Int,
    val gameStarted: Boolean,
    val players: List<LobbyPlayers>
)


data class LobbyPlayers(
    val playerId: Long,
    val playerName: String
)
