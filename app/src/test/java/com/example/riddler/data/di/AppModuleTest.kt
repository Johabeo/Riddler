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
    //getAppDB(context:Application):AppDatabase
    fun test_function_getAppDB() {
        val dao = mockk<QuizDao>()
        val api = mockk<RetroApiInterface>()
        val database = mockk<AppDatabase>()
        val application = mockk<Application>()

        coEvery { appModule.getAppDB(application) } returns database
        val result = appModule.getAppDB(application)
        assertEquals(database, result)

    }

    @Test
    //getQuizDao(appDB:AppDatabase):QuizDao
    fun test_function_getQuizDao() {
        val database = mockk<AppDatabase>()
        val dao = mockk<QuizDao>()
        coEvery { appModule.getQuizDao(database) } returns dao
        val result = appModule.getQuizDao(database)
        assertEquals(dao, result)
    }

    @Test
    //getRetroApi():RetroApiInterface
    fun test_for_getRetroApi() {
        val api = mockk<RetroApiInterface>()
        coEvery { appModule.getRetroApi() } returns api
        val result = appModule.getRetroApi()
        assertEquals(api, result)
    }
    @Test
    //getFirestoreRepository():FirestoreRepository
    fun test_for_getFirestoreRepository(){
        val repo = mockk<FirestoreRepository>()
        coEvery { appModule.getFirestoreRepository() } returns repo
        val result = appModule.getFirestoreRepository()
        assertEquals(repo, result)

    }
}
