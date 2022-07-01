package com.example.riddler.data.di

import android.app.Application
import com.example.riddler.RetroApiInterface
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
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
    fun getAppDB() {
        val dao = mockk<QuizDao>()
        val api = mockk<RetroApiInterface>()
        val database = mockk<AppDatabase>()
        val application = mockk<Application>()

        coEvery { appModule.getAppDB(application) } returns database
    }

    @Test
    //getQuizDao()
    fun getQuizDao() {
        val database = mockk<AppDatabase>()
        val dao = mockk<QuizDao>()
        coEvery { appModule.getQuizDao(database) } returns dao

    }

    @Test
    //getRetroApi()
    fun getRetroApi() {
        val api = mockk<RetroApiInterface>()
        //verify { appModule.getRetroApi() }
        coEvery { appModule.getRetroApi() } returns api

    }
}