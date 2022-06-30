package com.example.riddler.data.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Quiz(
    val owner: String? = null, //user token from authentication
    val name: String? = null,
    val description: String? = null,
    val type: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)


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
data class FavoriteQuiz(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val quizId: Int,
    val userId: Int //user token from authentication
)
