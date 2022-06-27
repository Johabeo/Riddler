package com.example.riddler.data.model

data class QuizGame(
    val createdTime: Long?,
    val finished: Boolean?,
    val pin: String?,
//    val currentQuestion: Int?,
    val question: Questions?
)
