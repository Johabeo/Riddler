package com.example.riddler

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TriviaQuestions(
    @field:Json(name = "response_code") val response_code: Int?,
    @field:Json(name = "results") val results: List<Question>?
) {
    @JsonClass(generateAdapter = true)
    data class Question(
        @field:Json(name = "category") val category: String?,
        @field:Json(name = "type") val type: String?,
        @field:Json(name = "difficulty") val difficulty: String?,
        @field:Json(name = "question") val question: String?,
        @field:Json(name = "correct_answer") val correct_answer: String?,
        @field:Json(name = "incorrect_answers") val incorrect_answers: List<String>?
    )
}

