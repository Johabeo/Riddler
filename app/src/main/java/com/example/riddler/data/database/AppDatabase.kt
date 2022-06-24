package com.example.riddler.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.riddler.data.dao.QuizDao
import com.example.riddler.data.model.FavoriteQuiz
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz

//1 -  annotation       //2 - entities
@Database(entities = [Quiz::class, Questions::class, FavoriteQuiz::class], version = 3, exportSchema = false)
//3 - abstract and extend
abstract class AppDatabase : RoomDatabase(){
    //4 abstract and return Dao
    abstract fun quizDao(): QuizDao
    //5 - Singleton
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                //6 - we are acquiring an instance of RoomDB builder
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