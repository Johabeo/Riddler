package com.example.riddler

import io.mockk.mockk
import org.junit.Assert.*

class TriviaRepoTest{
    val inter = mockk<RetroApiInterface>()
    val underTest = TriviaRepo(
        inter = inter,
    )

}