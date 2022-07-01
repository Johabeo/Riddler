package com.example.riddler.data.di

import android.app.Application
import com.example.riddler.RetroApiInterface
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.database.AppDatabase
import com.example.riddler.data.repo.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
        return  Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build()
            .create(RetroApiInterface::class.java)
    }
}