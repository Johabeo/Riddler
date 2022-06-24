package com.example.riddler.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.riddler.data.model.FavoriteQuiz
import com.example.riddler.data.model.Questions
import com.example.riddler.data.model.Quiz
import io.reactivex.rxjava3.core.Observable

@Dao
interface QuizDao {

    @Query("select * from Quiz where id = (select quizId from FavoriteQuiz where userId = :user)")
    fun getFavoriteQuizzes(user: Int): Observable<List<Quiz>>

    @Query("select * from Quiz where owner = :user")
    fun getMyQuizzes(user: Int): Observable<List<Quiz>>

    @Query("select * from Questions where quizId = :quizId")
    fun getQuizQuestions(quizId: Int): Observable<List<Questions>>

    @Insert
    fun insertQuiz(quiz: Quiz)

    @Insert
    fun insertFavoriteQuiz(favoriteQuiz: FavoriteQuiz)

    @Insert
    fun insertQuestions(questions: Questions)
}