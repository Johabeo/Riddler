package com.example.riddler.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import com.example.riddler.data.model.QuizFTS

@Database(entities = [Quiz::class, Questions::class, QuizFTS::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun quizDao(): QuizDao
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "riddler.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
        fun clearTable() {
            INSTANCE!!.clearAllTables()
        }
    }
}