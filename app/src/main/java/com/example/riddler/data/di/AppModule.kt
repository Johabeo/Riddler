package com.example.riddler.data.di

import android.app.Application
import com.example.riddler.RetroApiInterface
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.repo.FirestoreRepository
import com.example.riddler.data.repo.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun getAppDB(context: Application): AppDatabase {
        return AppDatabase.getInstance(context)!!
    }

    @Singleton
    @Provides
    fun getQuizDao(appDB: AppDatabase): QuizDao {
        return appDB.quizDao()
    }

//    @Singleton
//    @Provides
//    fun getQuizRepo(quizDao: QuizDao): QuizRepository {
//        return QuizRepository(quizDao)
//    }

    @Singleton
    @Provides
    fun getRetroApi(): RetroApiInterface{
        return RetroApiInterface.create()
    }

    @Singleton
    @Provides
    fun getFirestoreRepository() : FirestoreRepository{
        return FirestoreRepository()
    }
}