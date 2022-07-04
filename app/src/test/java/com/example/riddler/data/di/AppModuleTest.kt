package com.example.riddler.data.di

import android.app.Application
import com.example.riddler.RetroApiInterface
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.ui.viewmodel.just
import io.mockk.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class AppModuleTest {
    val appModule = mockk<AppModule>()


    @Before
    fun setUp() {
        val dao = mockk<QuizDao>()
        val api = mockk<RetroApiInterface>()
        val database = mockk<AppDatabase>()


    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    //getAppDB()
    fun `function to test if getAppDB returrns database`() {
        val dao = mockk<QuizDao>()
        val api = mockk<RetroApiInterface>()
        val database = mockk<AppDatabase>()
        val application = mockk<Application>()

        coEvery { appModule.getAppDB(application) } returns database
        val result = appModule.getAppDB(application)
        assertEquals(database, result)
    }

    @Test
    //getQuizDao()
    fun `Funtions to test is getQuizDao returns dao`() {
        val database = mockk<AppDatabase>()
        val dao = mockk<QuizDao>()
        coEvery { appModule.getQuizDao(database) } returns dao
        val result = appModule.getQuizDao(database)
        assertEquals(dao, result)


    }

    @Test
    //getRetroApi()
    fun `function to test if getRetroApi() will return retro api`() {
        val api = mockk<RetroApiInterface>()
        //verify { appModule.getRetroApi() }
        coEvery { appModule.getRetroApi() } returns api
        val result = appModule.getRetroApi()
        assertEquals(api, result)

    }
    @Test
    //getFirestoreRepository()
    fun `function to test if getFirestoreRepository() will return firestore repository`() {
        val repository = mockk<FirestoreRepository>()
        coEvery { appModule.getFirestoreRepository() } returns repository
        val result = appModule.getFirestoreRepository()
        assertEquals(repository, result)
    }
}