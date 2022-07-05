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
    fun `test that testGetRepo() gets call from underTest`() {
        coEvery { underTest.fetchUserProfileInfo() } returns Unit
        underTest.fetchUserProfileInfo()
        coVerify { underTest.fetchUserProfileInfo() }

    }

    @Test
    fun `test that getQuizList() gets call from underTest`() {
        coEvery { repo.getQuiz() }returns Unit
        repo.getQuiz()
        coVerify { repo.getQuiz() }

    }


    @Test
    fun `fetchUserProfileInfo() gets called from underTest`() {
        coEvery { underTest.fetchUserProfileInfo() } returns Unit
        underTest.fetchUserProfileInfo()
        coVerify { underTest.fetchUserProfileInfo() }

    }

}