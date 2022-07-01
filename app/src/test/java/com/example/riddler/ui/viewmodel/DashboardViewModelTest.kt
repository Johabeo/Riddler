package com.example.riddler.ui.viewmodel

import android.service.autofill.UserData
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.repo.FirestoreRepository
import io.mockk.*
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith


class DashboardViewModelTest {

    val repo = mockk<FirestoreRepository>()
    val testQuize = mockk<Quiz>()
    val testUserData = mockk<UserData>()
    val underTest = mockk<DashboardViewModel>(

    )

    @Test
    fun testGetRepo() {
        coEvery { underTest.fetchUserProfileInfo() } returns Unit

    }

    @Test
    fun getQuizList() {
        coEvery { repo.getQuiz() }returns Unit
    }


    @Test
    fun fetchUserProfileInfo() {
        coEvery { underTest.fetchUserProfileInfo() } returns Unit
    }
}