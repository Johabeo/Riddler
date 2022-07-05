package com.example.riddler.data.model

import org.junit.Assert.*

class FavoriteQuizTest {
    @org.junit.Test
    fun getId() {
        val favoriteQuiz = FavoriteQuiz(1, 1, 1)
        assertEquals(1, favoriteQuiz.id)
    }

    @org.junit.Test
    fun getDescription() {
        val favoriteQuiz = FavoriteQuiz(1, 1, 1)
        assertEquals(1, favoriteQuiz.userId)

    }

    @org.junit.Test
    fun getImageUrl() {
        val favoriteQuiz = FavoriteQuiz(1, 1,1)
        assertEquals(1, favoriteQuiz.userId)

    }
}