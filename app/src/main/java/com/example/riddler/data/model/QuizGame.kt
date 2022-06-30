package com.example.riddler.data.model

data class QuizGame(
    val createdTime: Long?,
    val finished: Boolean?,
    val pin: String?,
    val currentQuestion: Int?,
    val numAnswered: Int?,
    val question: Questions?,
    val displayingLeaderboard: Boolean?,
    val displayingResult: Boolean?,
    val players: List<Player>?
)

data class Player(
    val playerName: String?,
    val score: Int?,
    val submittedAnswer: Boolean?
)