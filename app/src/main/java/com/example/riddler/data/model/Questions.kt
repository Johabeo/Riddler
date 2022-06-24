package com.example.riddler.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("quizId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("quizId")
    ]
)
data class Questions(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val quizId: Int,
    val question: String? = null,
    val firstAnswer: String? = null,
    val secondAnswer: String? = null,
    val thirdAnswer: String? = null,
    val fourthAnswer: String? = null,
    val correctAnswer: String? = null
)
