package com.example.riddler.data.model

import org.junit.Assert.*

class QuizTest {
    val owner = "owner"
    val name = "name"
    val description = "description"
    val type = "type"
    val difficulty = "difficulty"
    val id = 4168
    val underTest = Quiz(
        owner = owner,
        name = name,
        description = description,
        type = type,
        difficulty = difficulty,
        id = id,
    )

@org.junit.Test
fun `test that the owner of the quize returns the right owner getOwner()`() {

    assertEquals(owner, underTest.owner)
}
}