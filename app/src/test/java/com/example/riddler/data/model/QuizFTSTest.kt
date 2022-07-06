package com.example.riddler.data.model

import org.junit.Assert.*

class QuizFTSTest {
    val owner = "owner"
    val name = "name"
    val description = "description"
    val type = "type"
    val difficulty = "difficulty"
    val id = 2119
    val underTest = QuizFTS(
        owner = owner,
        name = name,
        description = description,
        type = type,
        difficulty = difficulty,
        id = id,
    )
    val other = QuizFTS(
        owner = owner,
        name = name,
        description = description,
        type = type,
        difficulty = difficulty,
        id = id,
    )

    @org.junit.Test
    fun getOwner() {
        assertEquals(owner, underTest.owner)
        assertEquals(name, underTest.name)
        assertEquals(description, underTest.description)
        assertEquals(type, underTest.type)
        assertEquals(difficulty, underTest.difficulty)
        assertEquals(id, underTest.id)


    }

}