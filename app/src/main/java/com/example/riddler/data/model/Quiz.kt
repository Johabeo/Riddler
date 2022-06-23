package com.example.riddler.data.model

data class Quiz(
    val name: String? = null,
    val owner: String? = null,
    val description: String? = null,
    val type: String? = null,
    val questions: List<Questions>? = null
)
